/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import SporadicPurchasePayment from './sporadic-purchase-payment.vue';
import SporadicPurchasePaymentService from './sporadic-purchase-payment.service';
import AlertService from '@/shared/alert/alert.service';

type SporadicPurchasePaymentComponentType = InstanceType<typeof SporadicPurchasePayment>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('SporadicPurchasePayment Management Component', () => {
    let sporadicPurchasePaymentServiceStub: SinonStubbedInstance<SporadicPurchasePaymentService>;
    let mountOptions: MountingOptions<SporadicPurchasePaymentComponentType>['global'];

    beforeEach(() => {
      sporadicPurchasePaymentServiceStub = sinon.createStubInstance<SporadicPurchasePaymentService>(SporadicPurchasePaymentService);
      sporadicPurchasePaymentServiceStub.retrieve.resolves({ headers: {} });

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
          sporadicPurchasePaymentService: () => sporadicPurchasePaymentServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        sporadicPurchasePaymentServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(SporadicPurchasePayment, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(sporadicPurchasePaymentServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.sporadicPurchasePayments[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: SporadicPurchasePaymentComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(SporadicPurchasePayment, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        sporadicPurchasePaymentServiceStub.retrieve.reset();
        sporadicPurchasePaymentServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        sporadicPurchasePaymentServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeSporadicPurchasePayment();
        await comp.$nextTick(); // clear components

        // THEN
        expect(sporadicPurchasePaymentServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(sporadicPurchasePaymentServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
