/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import OutsourcingContract from './outsourcing-contract.vue';
import OutsourcingContractService from './outsourcing-contract.service';
import AlertService from '@/shared/alert/alert.service';

type OutsourcingContractComponentType = InstanceType<typeof OutsourcingContract>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('OutsourcingContract Management Component', () => {
    let outsourcingContractServiceStub: SinonStubbedInstance<OutsourcingContractService>;
    let mountOptions: MountingOptions<OutsourcingContractComponentType>['global'];

    beforeEach(() => {
      outsourcingContractServiceStub = sinon.createStubInstance<OutsourcingContractService>(OutsourcingContractService);
      outsourcingContractServiceStub.retrieve.resolves({ headers: {} });

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
          outsourcingContractService: () => outsourcingContractServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        outsourcingContractServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(OutsourcingContract, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(outsourcingContractServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.outsourcingContracts[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: OutsourcingContractComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(OutsourcingContract, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        outsourcingContractServiceStub.retrieve.reset();
        outsourcingContractServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        outsourcingContractServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeOutsourcingContract();
        await comp.$nextTick(); // clear components

        // THEN
        expect(outsourcingContractServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(outsourcingContractServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
