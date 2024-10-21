/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import CustomerSatisfaction from './customer-satisfaction.vue';
import CustomerSatisfactionService from './customer-satisfaction.service';
import AlertService from '@/shared/alert/alert.service';

type CustomerSatisfactionComponentType = InstanceType<typeof CustomerSatisfaction>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('CustomerSatisfaction Management Component', () => {
    let customerSatisfactionServiceStub: SinonStubbedInstance<CustomerSatisfactionService>;
    let mountOptions: MountingOptions<CustomerSatisfactionComponentType>['global'];

    beforeEach(() => {
      customerSatisfactionServiceStub = sinon.createStubInstance<CustomerSatisfactionService>(CustomerSatisfactionService);
      customerSatisfactionServiceStub.retrieve.resolves({ headers: {} });

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
          customerSatisfactionService: () => customerSatisfactionServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        customerSatisfactionServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(CustomerSatisfaction, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(customerSatisfactionServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.customerSatisfactions[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: CustomerSatisfactionComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(CustomerSatisfaction, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        customerSatisfactionServiceStub.retrieve.reset();
        customerSatisfactionServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        customerSatisfactionServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeCustomerSatisfaction();
        await comp.$nextTick(); // clear components

        // THEN
        expect(customerSatisfactionServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(customerSatisfactionServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
