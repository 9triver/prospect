/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProjectBudgetUpdate from './project-budget-update.vue';
import ProjectBudgetService from './project-budget.service';
import AlertService from '@/shared/alert/alert.service';

import HrManagementService from '@/entities/hr-management/hr-management.service';
import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';

type ProjectBudgetUpdateComponentType = InstanceType<typeof ProjectBudgetUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const projectBudgetSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ProjectBudgetUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ProjectBudget Management Update Component', () => {
    let comp: ProjectBudgetUpdateComponentType;
    let projectBudgetServiceStub: SinonStubbedInstance<ProjectBudgetService>;

    beforeEach(() => {
      route = {};
      projectBudgetServiceStub = sinon.createStubInstance<ProjectBudgetService>(ProjectBudgetService);
      projectBudgetServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          projectBudgetService: () => projectBudgetServiceStub,
          hrManagementService: () =>
            sinon.createStubInstance<HrManagementService>(HrManagementService, {
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
        const wrapper = shallowMount(ProjectBudgetUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.projectBudget = projectBudgetSample;
        projectBudgetServiceStub.update.resolves(projectBudgetSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(projectBudgetServiceStub.update.calledWith(projectBudgetSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        projectBudgetServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ProjectBudgetUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.projectBudget = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(projectBudgetServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        projectBudgetServiceStub.find.resolves(projectBudgetSample);
        projectBudgetServiceStub.retrieve.resolves([projectBudgetSample]);

        // WHEN
        route = {
          params: {
            projectBudgetId: '' + projectBudgetSample.id,
          },
        };
        const wrapper = shallowMount(ProjectBudgetUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.projectBudget).toMatchObject(projectBudgetSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        projectBudgetServiceStub.find.resolves(projectBudgetSample);
        const wrapper = shallowMount(ProjectBudgetUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
