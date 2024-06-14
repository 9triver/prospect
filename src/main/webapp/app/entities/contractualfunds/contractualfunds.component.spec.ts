/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Contractualfunds from './contractualfunds.vue';
import ContractualfundsService from './contractualfunds.service';
import AlertService from '@/shared/alert/alert.service';

type ContractualfundsComponentType = InstanceType<typeof Contractualfunds>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Contractualfunds Management Component', () => {
    let contractualfundsServiceStub: SinonStubbedInstance<ContractualfundsService>;
    let mountOptions: MountingOptions<ContractualfundsComponentType>['global'];

    beforeEach(() => {
      contractualfundsServiceStub = sinon.createStubInstance<ContractualfundsService>(ContractualfundsService);
      contractualfundsServiceStub.retrieve.resolves({ headers: {} });

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
          contractualfundsService: () => contractualfundsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        contractualfundsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Contractualfunds, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(contractualfundsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.contractualfunds[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ContractualfundsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Contractualfunds, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        contractualfundsServiceStub.retrieve.reset();
        contractualfundsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        contractualfundsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeContractualfunds();
        await comp.$nextTick(); // clear components

        // THEN
        expect(contractualfundsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(contractualfundsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
