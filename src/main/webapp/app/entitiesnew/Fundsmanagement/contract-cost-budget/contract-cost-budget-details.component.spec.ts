/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ContractCostBudgetDetails from './contract-cost-budget-details.vue';
import ContractCostBudgetService from './contract-cost-budget.service';
import AlertService from '@/shared/alert/alert.service';

type ContractCostBudgetDetailsComponentType = InstanceType<typeof ContractCostBudgetDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const contractCostBudgetSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ContractCostBudget Management Detail Component', () => {
    let contractCostBudgetServiceStub: SinonStubbedInstance<ContractCostBudgetService>;
    let mountOptions: MountingOptions<ContractCostBudgetDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      contractCostBudgetServiceStub = sinon.createStubInstance<ContractCostBudgetService>(ContractCostBudgetService);

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
          contractCostBudgetService: () => contractCostBudgetServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        contractCostBudgetServiceStub.find.resolves(contractCostBudgetSample);
        route = {
          params: {
            contractCostBudgetId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(ContractCostBudgetDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.contractCostBudget).toMatchObject(contractCostBudgetSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        contractCostBudgetServiceStub.find.resolves(contractCostBudgetSample);
        const wrapper = shallowMount(ContractCostBudgetDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
