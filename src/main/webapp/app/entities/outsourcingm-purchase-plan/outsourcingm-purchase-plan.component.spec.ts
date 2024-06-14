/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import OutsourcingmPurchasePlan from './outsourcingm-purchase-plan.vue';
import OutsourcingmPurchasePlanService from './outsourcingm-purchase-plan.service';
import AlertService from '@/shared/alert/alert.service';

type OutsourcingmPurchasePlanComponentType = InstanceType<typeof OutsourcingmPurchasePlan>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('OutsourcingmPurchasePlan Management Component', () => {
    let outsourcingmPurchasePlanServiceStub: SinonStubbedInstance<OutsourcingmPurchasePlanService>;
    let mountOptions: MountingOptions<OutsourcingmPurchasePlanComponentType>['global'];

    beforeEach(() => {
      outsourcingmPurchasePlanServiceStub = sinon.createStubInstance<OutsourcingmPurchasePlanService>(OutsourcingmPurchasePlanService);
      outsourcingmPurchasePlanServiceStub.retrieve.resolves({ headers: {} });

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
          outsourcingmPurchasePlanService: () => outsourcingmPurchasePlanServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        outsourcingmPurchasePlanServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(OutsourcingmPurchasePlan, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(outsourcingmPurchasePlanServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.outsourcingmPurchasePlans[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: OutsourcingmPurchasePlanComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(OutsourcingmPurchasePlan, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        outsourcingmPurchasePlanServiceStub.retrieve.reset();
        outsourcingmPurchasePlanServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        outsourcingmPurchasePlanServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeOutsourcingmPurchasePlan();
        await comp.$nextTick(); // clear components

        // THEN
        expect(outsourcingmPurchasePlanServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(outsourcingmPurchasePlanServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
