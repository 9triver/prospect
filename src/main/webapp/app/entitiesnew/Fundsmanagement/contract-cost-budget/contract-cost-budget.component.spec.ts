/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import ContractCostBudget from './contract-cost-budget.vue';
import ContractCostBudgetService from './contract-cost-budget.service';
import AlertService from '@/shared/alert/alert.service';

type ContractCostBudgetComponentType = InstanceType<typeof ContractCostBudget>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('ContractCostBudget Management Component', () => {
    let contractCostBudgetServiceStub: SinonStubbedInstance<ContractCostBudgetService>;
    let mountOptions: MountingOptions<ContractCostBudgetComponentType>['global'];

    beforeEach(() => {
      contractCostBudgetServiceStub = sinon.createStubInstance<ContractCostBudgetService>(ContractCostBudgetService);
      contractCostBudgetServiceStub.retrieve.resolves({ headers: {} });

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          bModal: bModalStub as any,
          'font-awesome-icon': true,
          'b-badge': true,
          'b-button': true,
          'router-link': true,
        },
        directives: {
          'b-modal': {},
        },
        provide: {
          alertService,
          contractCostBudgetService: () => contractCostBudgetServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        contractCostBudgetServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(ContractCostBudget, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(contractCostBudgetServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.contractCostBudgets[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: ContractCostBudgetComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(ContractCostBudget, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        contractCostBudgetServiceStub.retrieve.reset();
        contractCostBudgetServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        contractCostBudgetServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeContractCostBudget();
        await comp.$nextTick(); // clear components

        // THEN
        expect(contractCostBudgetServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(contractCostBudgetServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
