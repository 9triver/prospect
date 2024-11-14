/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ContractPaymentUpdate from './contract-payment-update.vue';
import ContractPaymentService from './contract-payment.service';
import AlertService from '@/shared/alert/alert.service';

import WorkbagService from '@/entities/workbag/workbag.service';
import PaymentApplicationService from '@/entities/payment-application/payment-application.service';
import PaymentCostListService from '@/entities/payment-cost-list/payment-cost-list.service';
import FundSourceListService from '@/entities/fund-source-list/fund-source-list.service';

type ContractPaymentUpdateComponentType = InstanceType<typeof ContractPaymentUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const contractPaymentSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ContractPaymentUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ContractPayment Management Update Component', () => {
    let comp: ContractPaymentUpdateComponentType;
    let contractPaymentServiceStub: SinonStubbedInstance<ContractPaymentService>;

    beforeEach(() => {
      route = {};
      contractPaymentServiceStub = sinon.createStubInstance<ContractPaymentService>(ContractPaymentService);
      contractPaymentServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          contractPaymentService: () => contractPaymentServiceStub,
          workbagService: () =>
            sinon.createStubInstance<WorkbagService>(WorkbagService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          paymentApplicationService: () =>
            sinon.createStubInstance<PaymentApplicationService>(PaymentApplicationService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          paymentCostListService: () =>
            sinon.createStubInstance<PaymentCostListService>(PaymentCostListService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          fundSourceListService: () =>
            sinon.createStubInstance<FundSourceListService>(FundSourceListService, {
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
        const wrapper = shallowMount(ContractPaymentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.contractPayment = contractPaymentSample;
        contractPaymentServiceStub.update.resolves(contractPaymentSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(contractPaymentServiceStub.update.calledWith(contractPaymentSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        contractPaymentServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ContractPaymentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.contractPayment = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(contractPaymentServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        contractPaymentServiceStub.find.resolves(contractPaymentSample);
        contractPaymentServiceStub.retrieve.resolves([contractPaymentSample]);

        // WHEN
        route = {
          params: {
            contractPaymentId: '' + contractPaymentSample.id,
          },
        };
        const wrapper = shallowMount(ContractPaymentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.contractPayment).toMatchObject(contractPaymentSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        contractPaymentServiceStub.find.resolves(contractPaymentSample);
        const wrapper = shallowMount(ContractPaymentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
