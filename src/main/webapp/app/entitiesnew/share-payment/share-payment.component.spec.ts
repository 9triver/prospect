/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import SharePayment from './share-payment.vue';
import SharePaymentService from './share-payment.service';
import AlertService from '@/shared/alert/alert.service';

type SharePaymentComponentType = InstanceType<typeof SharePayment>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('SharePayment Management Component', () => {
    let sharePaymentServiceStub: SinonStubbedInstance<SharePaymentService>;
    let mountOptions: MountingOptions<SharePaymentComponentType>['global'];

    beforeEach(() => {
      sharePaymentServiceStub = sinon.createStubInstance<SharePaymentService>(SharePaymentService);
      sharePaymentServiceStub.retrieve.resolves({ headers: {} });

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
          sharePaymentService: () => sharePaymentServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        sharePaymentServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(SharePayment, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(sharePaymentServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.sharePayments[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: SharePaymentComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(SharePayment, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        sharePaymentServiceStub.retrieve.reset();
        sharePaymentServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        sharePaymentServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeSharePayment();
        await comp.$nextTick(); // clear components

        // THEN
        expect(sharePaymentServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(sharePaymentServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
