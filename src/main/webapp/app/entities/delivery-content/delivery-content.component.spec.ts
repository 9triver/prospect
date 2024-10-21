/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import DeliveryContent from './delivery-content.vue';
import DeliveryContentService from './delivery-content.service';
import AlertService from '@/shared/alert/alert.service';

type DeliveryContentComponentType = InstanceType<typeof DeliveryContent>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('DeliveryContent Management Component', () => {
    let deliveryContentServiceStub: SinonStubbedInstance<DeliveryContentService>;
    let mountOptions: MountingOptions<DeliveryContentComponentType>['global'];

    beforeEach(() => {
      deliveryContentServiceStub = sinon.createStubInstance<DeliveryContentService>(DeliveryContentService);
      deliveryContentServiceStub.retrieve.resolves({ headers: {} });

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
          deliveryContentService: () => deliveryContentServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        deliveryContentServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(DeliveryContent, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(deliveryContentServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.deliveryContents[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: DeliveryContentComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(DeliveryContent, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        deliveryContentServiceStub.retrieve.reset();
        deliveryContentServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        deliveryContentServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeDeliveryContent();
        await comp.$nextTick(); // clear components

        // THEN
        expect(deliveryContentServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(deliveryContentServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
