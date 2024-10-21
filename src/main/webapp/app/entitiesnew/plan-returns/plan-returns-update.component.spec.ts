/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import PlanReturnsUpdate from './plan-returns-update.vue';
import PlanReturnsService from './plan-returns.service';
import AlertService from '@/shared/alert/alert.service';

import HrManagementService from '@/entities/hr-management/hr-management.service';
import ProgressPlanService from '@/entities/progress-plan/progress-plan.service';

type PlanReturnsUpdateComponentType = InstanceType<typeof PlanReturnsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const planReturnsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<PlanReturnsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('PlanReturns Management Update Component', () => {
    let comp: PlanReturnsUpdateComponentType;
    let planReturnsServiceStub: SinonStubbedInstance<PlanReturnsService>;

    beforeEach(() => {
      route = {};
      planReturnsServiceStub = sinon.createStubInstance<PlanReturnsService>(PlanReturnsService);
      planReturnsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          planReturnsService: () => planReturnsServiceStub,
          hrManagementService: () =>
            sinon.createStubInstance<HrManagementService>(HrManagementService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          progressPlanService: () =>
            sinon.createStubInstance<ProgressPlanService>(ProgressPlanService, {
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
        const wrapper = shallowMount(PlanReturnsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.planReturns = planReturnsSample;
        planReturnsServiceStub.update.resolves(planReturnsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(planReturnsServiceStub.update.calledWith(planReturnsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        planReturnsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(PlanReturnsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.planReturns = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(planReturnsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        planReturnsServiceStub.find.resolves(planReturnsSample);
        planReturnsServiceStub.retrieve.resolves([planReturnsSample]);

        // WHEN
        route = {
          params: {
            planReturnsId: '' + planReturnsSample.id,
          },
        };
        const wrapper = shallowMount(PlanReturnsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.planReturns).toMatchObject(planReturnsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        planReturnsServiceStub.find.resolves(planReturnsSample);
        const wrapper = shallowMount(PlanReturnsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
