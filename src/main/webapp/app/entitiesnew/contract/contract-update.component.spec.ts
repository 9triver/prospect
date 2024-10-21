/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ContractUpdate from './contract-update.vue';
import ContractService from './contract.service';
import AlertService from '@/shared/alert/alert.service';

import CostControlSystemService from '@/entities/cost-control-system/cost-control-system.service';

type ContractUpdateComponentType = InstanceType<typeof ContractUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const contractSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ContractUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Contract Management Update Component', () => {
    let comp: ContractUpdateComponentType;
    let contractServiceStub: SinonStubbedInstance<ContractService>;

    beforeEach(() => {
      route = {};
      contractServiceStub = sinon.createStubInstance<ContractService>(ContractService);
      contractServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          contractService: () => contractServiceStub,
          costControlSystemService: () =>
            sinon.createStubInstance<CostControlSystemService>(CostControlSystemService, {
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
        const wrapper = shallowMount(ContractUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.contract = contractSample;
        contractServiceStub.update.resolves(contractSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(contractServiceStub.update.calledWith(contractSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        contractServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ContractUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.contract = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(contractServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        contractServiceStub.find.resolves(contractSample);
        contractServiceStub.retrieve.resolves([contractSample]);

        // WHEN
        route = {
          params: {
            contractId: '' + contractSample.id,
          },
        };
        const wrapper = shallowMount(ContractUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.contract).toMatchObject(contractSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        contractServiceStub.find.resolves(contractSample);
        const wrapper = shallowMount(ContractUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
