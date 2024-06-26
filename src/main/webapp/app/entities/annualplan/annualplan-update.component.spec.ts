/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import AnnualplanUpdate from './annualplan-update.vue';
import AnnualplanService from './annualplan.service';
import AlertService from '@/shared/alert/alert.service';

import DocumentService from '@/entities/document/document.service';
import MonthplanService from '@/entities/monthplan/monthplan.service';
import ProjectchargeService from '@/entities/projectcharge/projectcharge.service';
import OfficersService from '@/entities/officers/officers.service';

type AnnualplanUpdateComponentType = InstanceType<typeof AnnualplanUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const annualplanSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<AnnualplanUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Annualplan Management Update Component', () => {
    let comp: AnnualplanUpdateComponentType;
    let annualplanServiceStub: SinonStubbedInstance<AnnualplanService>;

    beforeEach(() => {
      route = {};
      annualplanServiceStub = sinon.createStubInstance<AnnualplanService>(AnnualplanService);
      annualplanServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          annualplanService: () => annualplanServiceStub,
          documentService: () =>
            sinon.createStubInstance<DocumentService>(DocumentService, {
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
        const wrapper = shallowMount(AnnualplanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.annualplan = annualplanSample;
        annualplanServiceStub.update.resolves(annualplanSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(annualplanServiceStub.update.calledWith(annualplanSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        annualplanServiceStub.create.resolves(entity);
        const wrapper = shallowMount(AnnualplanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.annualplan = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(annualplanServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        annualplanServiceStub.find.resolves(annualplanSample);
        annualplanServiceStub.retrieve.resolves([annualplanSample]);

        // WHEN
        route = {
          params: {
            annualplanId: '' + annualplanSample.id,
          },
        };
        const wrapper = shallowMount(AnnualplanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.annualplan).toMatchObject(annualplanSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        annualplanServiceStub.find.resolves(annualplanSample);
        const wrapper = shallowMount(AnnualplanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
