/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import OutsourcingContractual from './outsourcing-contractual.vue';
import OutsourcingContractualService from './outsourcing-contractual.service';
import AlertService from '@/shared/alert/alert.service';

type OutsourcingContractualComponentType = InstanceType<typeof OutsourcingContractual>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('OutsourcingContractual Management Component', () => {
    let outsourcingContractualServiceStub: SinonStubbedInstance<OutsourcingContractualService>;
    let mountOptions: MountingOptions<OutsourcingContractualComponentType>['global'];

    beforeEach(() => {
      outsourcingContractualServiceStub = sinon.createStubInstance<OutsourcingContractualService>(OutsourcingContractualService);
      outsourcingContractualServiceStub.retrieve.resolves({ headers: {} });

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
          outsourcingContractualService: () => outsourcingContractualServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        outsourcingContractualServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(OutsourcingContractual, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(outsourcingContractualServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.outsourcingContractuals[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: OutsourcingContractualComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(OutsourcingContractual, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        outsourcingContractualServiceStub.retrieve.reset();
        outsourcingContractualServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        outsourcingContractualServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeOutsourcingContractual();
        await comp.$nextTick(); // clear components

        // THEN
        expect(outsourcingContractualServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(outsourcingContractualServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
