/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import OtherPayment from './other-payment.vue';
import OtherPaymentService from './other-payment.service';
import AlertService from '@/shared/alert/alert.service';

type OtherPaymentComponentType = InstanceType<typeof OtherPayment>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('OtherPayment Management Component', () => {
    let otherPaymentServiceStub: SinonStubbedInstance<OtherPaymentService>;
    let mountOptions: MountingOptions<OtherPaymentComponentType>['global'];

    beforeEach(() => {
      otherPaymentServiceStub = sinon.createStubInstance<OtherPaymentService>(OtherPaymentService);
      otherPaymentServiceStub.retrieve.resolves({ headers: {} });

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
          otherPaymentService: () => otherPaymentServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        otherPaymentServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(OtherPayment, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(otherPaymentServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.otherPayments[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: OtherPaymentComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(OtherPayment, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        otherPaymentServiceStub.retrieve.reset();
        otherPaymentServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        otherPaymentServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeOtherPayment();
        await comp.$nextTick(); // clear components

        // THEN
        expect(otherPaymentServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(otherPaymentServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
