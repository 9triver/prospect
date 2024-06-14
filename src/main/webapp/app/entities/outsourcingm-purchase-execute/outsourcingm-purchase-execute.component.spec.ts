/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import OutsourcingmPurchaseExecute from './outsourcingm-purchase-execute.vue';
import OutsourcingmPurchaseExecuteService from './outsourcingm-purchase-execute.service';
import AlertService from '@/shared/alert/alert.service';

type OutsourcingmPurchaseExecuteComponentType = InstanceType<typeof OutsourcingmPurchaseExecute>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('OutsourcingmPurchaseExecute Management Component', () => {
    let outsourcingmPurchaseExecuteServiceStub: SinonStubbedInstance<OutsourcingmPurchaseExecuteService>;
    let mountOptions: MountingOptions<OutsourcingmPurchaseExecuteComponentType>['global'];

    beforeEach(() => {
      outsourcingmPurchaseExecuteServiceStub =
        sinon.createStubInstance<OutsourcingmPurchaseExecuteService>(OutsourcingmPurchaseExecuteService);
      outsourcingmPurchaseExecuteServiceStub.retrieve.resolves({ headers: {} });

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
          outsourcingmPurchaseExecuteService: () => outsourcingmPurchaseExecuteServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        outsourcingmPurchaseExecuteServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(OutsourcingmPurchaseExecute, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(outsourcingmPurchaseExecuteServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.outsourcingmPurchaseExecutes[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: OutsourcingmPurchaseExecuteComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(OutsourcingmPurchaseExecute, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        outsourcingmPurchaseExecuteServiceStub.retrieve.reset();
        outsourcingmPurchaseExecuteServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        outsourcingmPurchaseExecuteServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeOutsourcingmPurchaseExecute();
        await comp.$nextTick(); // clear components

        // THEN
        expect(outsourcingmPurchaseExecuteServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(outsourcingmPurchaseExecuteServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
