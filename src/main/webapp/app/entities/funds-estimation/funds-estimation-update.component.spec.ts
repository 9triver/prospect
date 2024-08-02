/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import FundsEstimationUpdate from './funds-estimation-update.vue';
import FundsEstimationService from './funds-estimation.service';
import AlertService from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';

type FundsEstimationUpdateComponentType = InstanceType<typeof FundsEstimationUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const fundsEstimationSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<FundsEstimationUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('FundsEstimation Management Update Component', () => {
    let comp: FundsEstimationUpdateComponentType;
    let fundsEstimationServiceStub: SinonStubbedInstance<FundsEstimationService>;

    beforeEach(() => {
      route = {};
      fundsEstimationServiceStub = sinon.createStubInstance<FundsEstimationService>(FundsEstimationService);
      fundsEstimationServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          fundsEstimationService: () => fundsEstimationServiceStub,
          officersService: () =>
            sinon.createStubInstance<OfficersService>(OfficersService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          projectwbsService: () =>
            sinon.createStubInstance<ProjectwbsService>(ProjectwbsService, {
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
        const wrapper = shallowMount(FundsEstimationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.fundsEstimation = fundsEstimationSample;
        fundsEstimationServiceStub.update.resolves(fundsEstimationSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(fundsEstimationServiceStub.update.calledWith(fundsEstimationSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        fundsEstimationServiceStub.create.resolves(entity);
        const wrapper = shallowMount(FundsEstimationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.fundsEstimation = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(fundsEstimationServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        fundsEstimationServiceStub.find.resolves(fundsEstimationSample);
        fundsEstimationServiceStub.retrieve.resolves([fundsEstimationSample]);

        // WHEN
        route = {
          params: {
            fundsEstimationId: '' + fundsEstimationSample.id,
          },
        };
        const wrapper = shallowMount(FundsEstimationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.fundsEstimation).toMatchObject(fundsEstimationSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        fundsEstimationServiceStub.find.resolves(fundsEstimationSample);
        const wrapper = shallowMount(FundsEstimationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
