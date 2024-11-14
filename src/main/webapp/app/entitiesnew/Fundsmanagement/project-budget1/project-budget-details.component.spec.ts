/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProjectBudgetDetails from './project-budget-details.vue';
import ProjectBudgetService from './project-budget.service';
import AlertService from '@/shared/alert/alert.service';

type ProjectBudgetDetailsComponentType = InstanceType<typeof ProjectBudgetDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const projectBudgetSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ProjectBudget Management Detail Component', () => {
    let projectBudgetServiceStub: SinonStubbedInstance<ProjectBudgetService>;
    let mountOptions: MountingOptions<ProjectBudgetDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      projectBudgetServiceStub = sinon.createStubInstance<ProjectBudgetService>(ProjectBudgetService);

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'router-link': true,
        },
        provide: {
          alertService,
          projectBudgetService: () => projectBudgetServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        projectBudgetServiceStub.find.resolves(projectBudgetSample);
        route = {
          params: {
            projectBudgetId: '' + 123,
          },
        };
        const wrapper = shallowMount(ProjectBudgetDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.projectBudget).toMatchObject(projectBudgetSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        projectBudgetServiceStub.find.resolves(projectBudgetSample);
        const wrapper = shallowMount(ProjectBudgetDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
