/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SubjectCostBudgetDetails from './subject-cost-budget-details.vue';
import SubjectCostBudgetService from './subject-cost-budget.service';
import AlertService from '@/shared/alert/alert.service';

type SubjectCostBudgetDetailsComponentType = InstanceType<typeof SubjectCostBudgetDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const subjectCostBudgetSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('SubjectCostBudget Management Detail Component', () => {
    let subjectCostBudgetServiceStub: SinonStubbedInstance<SubjectCostBudgetService>;
    let mountOptions: MountingOptions<SubjectCostBudgetDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      subjectCostBudgetServiceStub = sinon.createStubInstance<SubjectCostBudgetService>(SubjectCostBudgetService);

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
          subjectCostBudgetService: () => subjectCostBudgetServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        subjectCostBudgetServiceStub.find.resolves(subjectCostBudgetSample);
        route = {
          params: {
            subjectCostBudgetId: '' + 123,
          },
        };
        const wrapper = shallowMount(SubjectCostBudgetDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.subjectCostBudget).toMatchObject(subjectCostBudgetSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        subjectCostBudgetServiceStub.find.resolves(subjectCostBudgetSample);
        const wrapper = shallowMount(SubjectCostBudgetDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
