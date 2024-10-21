/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import TransactionPayment from './transaction-payment.vue';
import TransactionPaymentService from './transaction-payment.service';
import AlertService from '@/shared/alert/alert.service';

type TransactionPaymentComponentType = InstanceType<typeof TransactionPayment>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('TransactionPayment Management Component', () => {
    let transactionPaymentServiceStub: SinonStubbedInstance<TransactionPaymentService>;
    let mountOptions: MountingOptions<TransactionPaymentComponentType>['global'];

    beforeEach(() => {
      transactionPaymentServiceStub = sinon.createStubInstance<TransactionPaymentService>(TransactionPaymentService);
      transactionPaymentServiceStub.retrieve.resolves({ headers: {} });

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
          transactionPaymentService: () => transactionPaymentServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        transactionPaymentServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(TransactionPayment, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(transactionPaymentServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.transactionPayments[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: TransactionPaymentComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(TransactionPayment, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        transactionPaymentServiceStub.retrieve.reset();
        transactionPaymentServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        transactionPaymentServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeTransactionPayment();
        await comp.$nextTick(); // clear components

        // THEN
        expect(transactionPaymentServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(transactionPaymentServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
