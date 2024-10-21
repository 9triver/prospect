/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import PaymentCostList from './payment-cost-list.vue';
import PaymentCostListService from './payment-cost-list.service';
import AlertService from '@/shared/alert/alert.service';

type PaymentCostListComponentType = InstanceType<typeof PaymentCostList>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('PaymentCostList Management Component', () => {
    let paymentCostListServiceStub: SinonStubbedInstance<PaymentCostListService>;
    let mountOptions: MountingOptions<PaymentCostListComponentType>['global'];

    beforeEach(() => {
      paymentCostListServiceStub = sinon.createStubInstance<PaymentCostListService>(PaymentCostListService);
      paymentCostListServiceStub.retrieve.resolves({ headers: {} });

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
          paymentCostListService: () => paymentCostListServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        paymentCostListServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(PaymentCostList, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(paymentCostListServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.paymentCostLists[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: PaymentCostListComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(PaymentCostList, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        paymentCostListServiceStub.retrieve.reset();
        paymentCostListServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        paymentCostListServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removePaymentCostList();
        await comp.$nextTick(); // clear components

        // THEN
        expect(paymentCostListServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(paymentCostListServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
