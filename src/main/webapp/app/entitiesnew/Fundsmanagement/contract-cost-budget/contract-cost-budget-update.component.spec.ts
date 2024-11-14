/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ContractCostBudgetUpdate from './contract-cost-budget-update.vue';
import ContractCostBudgetService from './contract-cost-budget.service';
import AlertService from '@/shared/alert/alert.service';

import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';

type ContractCostBudgetUpdateComponentType = InstanceType<typeof ContractCostBudgetUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const contractCostBudgetSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ContractCostBudgetUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ContractCostBudget Management Update Component', () => {
    let comp: ContractCostBudgetUpdateComponentType;
    let contractCostBudgetServiceStub: SinonStubbedInstance<ContractCostBudgetService>;

    beforeEach(() => {
      route = {};
      contractCostBudgetServiceStub = sinon.createStubInstance<ContractCostBudgetService>(ContractCostBudgetService);
      contractCostBudgetServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          contractCostBudgetService: () => contractCostBudgetServiceStub,
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
        const wrapper = shallowMount(ContractCostBudgetUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.contractCostBudget = contractCostBudgetSample;
        contractCostBudgetServiceStub.update.resolves(contractCostBudgetSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(contractCostBudgetServiceStub.update.calledWith(contractCostBudgetSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        contractCostBudgetServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ContractCostBudgetUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.contractCostBudget = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(contractCostBudgetServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        contractCostBudgetServiceStub.find.resolves(contractCostBudgetSample);
        contractCostBudgetServiceStub.retrieve.resolves([contractCostBudgetSample]);

        // WHEN
        route = {
          params: {
            contractCostBudgetId: '' + contractCostBudgetSample.id,
          },
        };
        const wrapper = shallowMount(ContractCostBudgetUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.contractCostBudget).toMatchObject(contractCostBudgetSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        contractCostBudgetServiceStub.find.resolves(contractCostBudgetSample);
        const wrapper = shallowMount(ContractCostBudgetUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
