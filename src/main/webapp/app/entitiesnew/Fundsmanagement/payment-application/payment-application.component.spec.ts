/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import PaymentApplication from './payment-application.vue';
import PaymentApplicationService from './payment-application.service';
import AlertService from '@/shared/alert/alert.service';

type PaymentApplicationComponentType = InstanceType<typeof PaymentApplication>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('PaymentApplication Management Component', () => {
    let paymentApplicationServiceStub: SinonStubbedInstance<PaymentApplicationService>;
    let mountOptions: MountingOptions<PaymentApplicationComponentType>['global'];

    beforeEach(() => {
      paymentApplicationServiceStub = sinon.createStubInstance<PaymentApplicationService>(PaymentApplicationService);
      paymentApplicationServiceStub.retrieve.resolves({ headers: {} });

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
          paymentApplicationService: () => paymentApplicationServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        paymentApplicationServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(PaymentApplication, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(paymentApplicationServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.paymentApplications[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: PaymentApplicationComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(PaymentApplication, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        paymentApplicationServiceStub.retrieve.reset();
        paymentApplicationServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        paymentApplicationServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removePaymentApplication();
        await comp.$nextTick(); // clear components

        // THEN
        expect(paymentApplicationServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(paymentApplicationServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
