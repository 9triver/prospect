/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Contract from './contract.vue';
import ContractService from './contract.service';
import AlertService from '@/shared/alert/alert.service';

type ContractComponentType = InstanceType<typeof Contract>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Contract Management Component', () => {
    let contractServiceStub: SinonStubbedInstance<ContractService>;
    let mountOptions: MountingOptions<ContractComponentType>['global'];

    beforeEach(() => {
      contractServiceStub = sinon.createStubInstance<ContractService>(ContractService);
      contractServiceStub.retrieve.resolves({ headers: {} });

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
          contractService: () => contractServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        contractServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Contract, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(contractServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.contracts[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ContractComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Contract, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        contractServiceStub.retrieve.reset();
        contractServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        contractServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeContract();
        await comp.$nextTick(); // clear components

        // THEN
        expect(contractServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(contractServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
