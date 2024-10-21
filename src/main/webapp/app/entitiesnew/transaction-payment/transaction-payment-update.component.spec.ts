/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import TransactionPaymentUpdate from './transaction-payment-update.vue';
import TransactionPaymentService from './transaction-payment.service';
import AlertService from '@/shared/alert/alert.service';

type TransactionPaymentUpdateComponentType = InstanceType<typeof TransactionPaymentUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const transactionPaymentSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<TransactionPaymentUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('TransactionPayment Management Update Component', () => {
    let comp: TransactionPaymentUpdateComponentType;
    let transactionPaymentServiceStub: SinonStubbedInstance<TransactionPaymentService>;

    beforeEach(() => {
      route = {};
      transactionPaymentServiceStub = sinon.createStubInstance<TransactionPaymentService>(TransactionPaymentService);
      transactionPaymentServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          transactionPaymentService: () => transactionPaymentServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(TransactionPaymentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.transactionPayment = transactionPaymentSample;
        transactionPaymentServiceStub.update.resolves(transactionPaymentSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(transactionPaymentServiceStub.update.calledWith(transactionPaymentSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        transactionPaymentServiceStub.create.resolves(entity);
        const wrapper = shallowMount(TransactionPaymentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.transactionPayment = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(transactionPaymentServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        transactionPaymentServiceStub.find.resolves(transactionPaymentSample);
        transactionPaymentServiceStub.retrieve.resolves([transactionPaymentSample]);

        // WHEN
        route = {
          params: {
            transactionPaymentId: '' + transactionPaymentSample.id,
          },
        };
        const wrapper = shallowMount(TransactionPaymentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.transactionPayment).toMatchObject(transactionPaymentSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        transactionPaymentServiceStub.find.resolves(transactionPaymentSample);
        const wrapper = shallowMount(TransactionPaymentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
