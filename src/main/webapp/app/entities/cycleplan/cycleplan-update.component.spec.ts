/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import CycleplanUpdate from './cycleplan-update.vue';
import CycleplanService from './cycleplan.service';
import AlertService from '@/shared/alert/alert.service';

import DocumentService from '@/entities/document/document.service';
import AnnualplanService from '@/entities/annualplan/annualplan.service';
import MonthplanService from '@/entities/monthplan/monthplan.service';
import ProjectchargeService from '@/entities/projectcharge/projectcharge.service';
import OfficersService from '@/entities/officers/officers.service';

type CycleplanUpdateComponentType = InstanceType<typeof CycleplanUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const cycleplanSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<CycleplanUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Cycleplan Management Update Component', () => {
    let comp: CycleplanUpdateComponentType;
    let cycleplanServiceStub: SinonStubbedInstance<CycleplanService>;

    beforeEach(() => {
      route = {};
      cycleplanServiceStub = sinon.createStubInstance<CycleplanService>(CycleplanService);
      cycleplanServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'b-input-group': true,
          'b-input-group-prepend': true,
          'b-form-datepicker': true,
          'b-form-input': true,
        },
        provide: {
          alertService,
          cycleplanService: () => cycleplanServiceStub,
          documentService: () =>
            sinon.createStubInstance<DocumentService>(DocumentService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          annualplanService: () =>
            sinon.createStubInstance<AnnualplanService>(AnnualplanService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          monthplanService: () =>
            sinon.createStubInstance<MonthplanService>(MonthplanService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          projectchargeService: () =>
            sinon.createStubInstance<ProjectchargeService>(ProjectchargeService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          officersService: () =>
            sinon.createStubInstance<OfficersService>(OfficersService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(CycleplanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.cycleplan = cycleplanSample;
        cycleplanServiceStub.update.resolves(cycleplanSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(cycleplanServiceStub.update.calledWith(cycleplanSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        cycleplanServiceStub.create.resolves(entity);
        const wrapper = shallowMount(CycleplanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.cycleplan = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(cycleplanServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        cycleplanServiceStub.find.resolves(cycleplanSample);
        cycleplanServiceStub.retrieve.resolves([cycleplanSample]);

        // WHEN
        route = {
          params: {
            cycleplanId: '' + cycleplanSample.id,
          },
        };
        const wrapper = shallowMount(CycleplanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.cycleplan).toMatchObject(cycleplanSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        cycleplanServiceStub.find.resolves(cycleplanSample);
        const wrapper = shallowMount(CycleplanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
