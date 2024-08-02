/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProgressPlanUpdate from './progress-plan-update.vue';
import ProgressPlanService from './progress-plan.service';
import AlertService from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import DepartmentService from '@/entities/department/department.service';
import PlanReturnsService from '@/entities/plan-returns/plan-returns.service';
import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import ProjectRiskService from '@/entities/project-risk/project-risk.service';

type ProgressPlanUpdateComponentType = InstanceType<typeof ProgressPlanUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const progressPlanSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ProgressPlanUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ProgressPlan Management Update Component', () => {
    let comp: ProgressPlanUpdateComponentType;
    let progressPlanServiceStub: SinonStubbedInstance<ProgressPlanService>;

    beforeEach(() => {
      route = {};
      progressPlanServiceStub = sinon.createStubInstance<ProgressPlanService>(ProgressPlanService);
      progressPlanServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          progressPlanService: () => progressPlanServiceStub,
          officersService: () =>
            sinon.createStubInstance<OfficersService>(OfficersService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          departmentService: () =>
            sinon.createStubInstance<DepartmentService>(DepartmentService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          planReturnsService: () =>
            sinon.createStubInstance<PlanReturnsService>(PlanReturnsService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          projectwbsService: () =>
            sinon.createStubInstance<ProjectwbsService>(ProjectwbsService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          projectRiskService: () =>
            sinon.createStubInstance<ProjectRiskService>(ProjectRiskService, {
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
        const wrapper = shallowMount(ProgressPlanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.progressPlan = progressPlanSample;
        progressPlanServiceStub.update.resolves(progressPlanSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(progressPlanServiceStub.update.calledWith(progressPlanSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        progressPlanServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ProgressPlanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.progressPlan = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(progressPlanServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        progressPlanServiceStub.find.resolves(progressPlanSample);
        progressPlanServiceStub.retrieve.resolves([progressPlanSample]);

        // WHEN
        route = {
          params: {
            progressPlanId: '' + progressPlanSample.id,
          },
        };
        const wrapper = shallowMount(ProgressPlanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.progressPlan).toMatchObject(progressPlanSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        progressPlanServiceStub.find.resolves(progressPlanSample);
        const wrapper = shallowMount(ProgressPlanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
