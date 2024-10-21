/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import ContractPayment from './contract-payment.vue';
import ContractPaymentService from './contract-payment.service';
import AlertService from '@/shared/alert/alert.service';

type ContractPaymentComponentType = InstanceType<typeof ContractPayment>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('ContractPayment Management Component', () => {
    let contractPaymentServiceStub: SinonStubbedInstance<ContractPaymentService>;
    let mountOptions: MountingOptions<ContractPaymentComponentType>['global'];

    beforeEach(() => {
      contractPaymentServiceStub = sinon.createStubInstance<ContractPaymentService>(ContractPaymentService);
      contractPaymentServiceStub.retrieve.resolves({ headers: {} });

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
          contractPaymentService: () => contractPaymentServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        contractPaymentServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(ContractPayment, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(contractPaymentServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.contractPayments[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ContractPaymentComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(ContractPayment, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        contractPaymentServiceStub.retrieve.reset();
        contractPaymentServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        contractPaymentServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeContractPayment();
        await comp.$nextTick(); // clear components

        // THEN
        expect(contractPaymentServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(contractPaymentServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
