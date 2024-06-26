/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import MonthplanUpdate from './monthplan-update.vue';
import MonthplanService from './monthplan.service';
import AlertService from '@/shared/alert/alert.service';

import DocumentService from '@/entities/document/document.service';
import PlanexecuteService from '@/entities/planexecute/planexecute.service';
import ProjectchargeService from '@/entities/projectcharge/projectcharge.service';
import OfficersService from '@/entities/officers/officers.service';

type MonthplanUpdateComponentType = InstanceType<typeof MonthplanUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const monthplanSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<MonthplanUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Monthplan Management Update Component', () => {
    let comp: MonthplanUpdateComponentType;
    let monthplanServiceStub: SinonStubbedInstance<MonthplanService>;

    beforeEach(() => {
      route = {};
      monthplanServiceStub = sinon.createStubInstance<MonthplanService>(MonthplanService);
      monthplanServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          monthplanService: () => monthplanServiceStub,
          documentService: () =>
            sinon.createStubInstance<DocumentService>(DocumentService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          planexecuteService: () =>
            sinon.createStubInstance<PlanexecuteService>(PlanexecuteService, {
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
        const wrapper = shallowMount(MonthplanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.monthplan = monthplanSample;
        monthplanServiceStub.update.resolves(monthplanSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(monthplanServiceStub.update.calledWith(monthplanSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        monthplanServiceStub.create.resolves(entity);
        const wrapper = shallowMount(MonthplanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.monthplan = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(monthplanServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        monthplanServiceStub.find.resolves(monthplanSample);
        monthplanServiceStub.retrieve.resolves([monthplanSample]);

        // WHEN
        route = {
          params: {
            monthplanId: '' + monthplanSample.id,
          },
        };
        const wrapper = shallowMount(MonthplanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.monthplan).toMatchObject(monthplanSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        monthplanServiceStub.find.resolves(monthplanSample);
        const wrapper = shallowMount(MonthplanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
