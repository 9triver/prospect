/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SubjectCostBudgetUpdate from './subject-cost-budget-update.vue';
import SubjectCostBudgetService from './subject-cost-budget.service';
import AlertService from '@/shared/alert/alert.service';

import ProjectBudgetService from '@/entities/project-budget/project-budget.service';
import HrManagementService from '@/entities/hr-management/hr-management.service';

type SubjectCostBudgetUpdateComponentType = InstanceType<typeof SubjectCostBudgetUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const subjectCostBudgetSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<SubjectCostBudgetUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('SubjectCostBudget Management Update Component', () => {
    let comp: SubjectCostBudgetUpdateComponentType;
    let subjectCostBudgetServiceStub: SinonStubbedInstance<SubjectCostBudgetService>;

    beforeEach(() => {
      route = {};
      subjectCostBudgetServiceStub = sinon.createStubInstance<SubjectCostBudgetService>(SubjectCostBudgetService);
      subjectCostBudgetServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          subjectCostBudgetService: () => subjectCostBudgetServiceStub,
          projectBudgetService: () =>
            sinon.createStubInstance<ProjectBudgetService>(ProjectBudgetService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          hrManagementService: () =>
            sinon.createStubInstance<HrManagementService>(HrManagementService, {
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
        const wrapper = shallowMount(SubjectCostBudgetUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.subjectCostBudget = subjectCostBudgetSample;
        subjectCostBudgetServiceStub.update.resolves(subjectCostBudgetSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(subjectCostBudgetServiceStub.update.calledWith(subjectCostBudgetSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        subjectCostBudgetServiceStub.create.resolves(entity);
        const wrapper = shallowMount(SubjectCostBudgetUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.subjectCostBudget = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(subjectCostBudgetServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        subjectCostBudgetServiceStub.find.resolves(subjectCostBudgetSample);
        subjectCostBudgetServiceStub.retrieve.resolves([subjectCostBudgetSample]);

        // WHEN
        route = {
          params: {
            subjectCostBudgetId: '' + subjectCostBudgetSample.id,
          },
        };
        const wrapper = shallowMount(SubjectCostBudgetUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.subjectCostBudget).toMatchObject(subjectCostBudgetSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        subjectCostBudgetServiceStub.find.resolves(subjectCostBudgetSample);
        const wrapper = shallowMount(SubjectCostBudgetUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
