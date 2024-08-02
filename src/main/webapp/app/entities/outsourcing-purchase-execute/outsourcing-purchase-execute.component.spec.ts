/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import OutsourcingPurchaseExecute from './outsourcing-purchase-execute.vue';
import OutsourcingPurchaseExecuteService from './outsourcing-purchase-execute.service';
import AlertService from '@/shared/alert/alert.service';

type OutsourcingPurchaseExecuteComponentType = InstanceType<typeof OutsourcingPurchaseExecute>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('OutsourcingPurchaseExecute Management Component', () => {
    let outsourcingPurchaseExecuteServiceStub: SinonStubbedInstance<OutsourcingPurchaseExecuteService>;
    let mountOptions: MountingOptions<OutsourcingPurchaseExecuteComponentType>['global'];

    beforeEach(() => {
      outsourcingPurchaseExecuteServiceStub =
        sinon.createStubInstance<OutsourcingPurchaseExecuteService>(OutsourcingPurchaseExecuteService);
      outsourcingPurchaseExecuteServiceStub.retrieve.resolves({ headers: {} });

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
          outsourcingPurchaseExecuteService: () => outsourcingPurchaseExecuteServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        outsourcingPurchaseExecuteServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(OutsourcingPurchaseExecute, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(outsourcingPurchaseExecuteServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.outsourcingPurchaseExecutes[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: OutsourcingPurchaseExecuteComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(OutsourcingPurchaseExecute, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        outsourcingPurchaseExecuteServiceStub.retrieve.reset();
        outsourcingPurchaseExecuteServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        outsourcingPurchaseExecuteServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeOutsourcingPurchaseExecute();
        await comp.$nextTick(); // clear components

        // THEN
        expect(outsourcingPurchaseExecuteServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(outsourcingPurchaseExecuteServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
