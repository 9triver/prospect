/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import PaymentCostListUpdate from './payment-cost-list-update.vue';
import PaymentCostListService from './payment-cost-list.service';
import AlertService from '@/shared/alert/alert.service';

import ContractPaymentService from '@/entities/contract-payment/contract-payment.service';

type PaymentCostListUpdateComponentType = InstanceType<typeof PaymentCostListUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const paymentCostListSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<PaymentCostListUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('PaymentCostList Management Update Component', () => {
    let comp: PaymentCostListUpdateComponentType;
    let paymentCostListServiceStub: SinonStubbedInstance<PaymentCostListService>;

    beforeEach(() => {
      route = {};
      paymentCostListServiceStub = sinon.createStubInstance<PaymentCostListService>(PaymentCostListService);
      paymentCostListServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          paymentCostListService: () => paymentCostListServiceStub,
          contractPaymentService: () =>
            sinon.createStubInstance<ContractPaymentService>(ContractPaymentService, {
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
        const wrapper = shallowMount(PaymentCostListUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.paymentCostList = paymentCostListSample;
        paymentCostListServiceStub.update.resolves(paymentCostListSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(paymentCostListServiceStub.update.calledWith(paymentCostListSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        paymentCostListServiceStub.create.resolves(entity);
        const wrapper = shallowMount(PaymentCostListUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.paymentCostList = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(paymentCostListServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        paymentCostListServiceStub.find.resolves(paymentCostListSample);
        paymentCostListServiceStub.retrieve.resolves([paymentCostListSample]);

        // WHEN
        route = {
          params: {
            paymentCostListId: '' + paymentCostListSample.id,
          },
        };
        const wrapper = shallowMount(PaymentCostListUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.paymentCostList).toMatchObject(paymentCostListSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        paymentCostListServiceStub.find.resolves(paymentCostListSample);
        const wrapper = shallowMount(PaymentCostListUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
