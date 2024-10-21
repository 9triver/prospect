/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import OutsourcingPurchasePlan from './outsourcing-purchase-plan.vue';
import OutsourcingPurchasePlanService from './outsourcing-purchase-plan.service';
import AlertService from '@/shared/alert/alert.service';

type OutsourcingPurchasePlanComponentType = InstanceType<typeof OutsourcingPurchasePlan>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('OutsourcingPurchasePlan Management Component', () => {
    let outsourcingPurchasePlanServiceStub: SinonStubbedInstance<OutsourcingPurchasePlanService>;
    let mountOptions: MountingOptions<OutsourcingPurchasePlanComponentType>['global'];

    beforeEach(() => {
      outsourcingPurchasePlanServiceStub = sinon.createStubInstance<OutsourcingPurchasePlanService>(OutsourcingPurchasePlanService);
      outsourcingPurchasePlanServiceStub.retrieve.resolves({ headers: {} });

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
          outsourcingPurchasePlanService: () => outsourcingPurchasePlanServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        outsourcingPurchasePlanServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(OutsourcingPurchasePlan, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(outsourcingPurchasePlanServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.outsourcingPurchasePlans[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: OutsourcingPurchasePlanComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(OutsourcingPurchasePlan, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        outsourcingPurchasePlanServiceStub.retrieve.reset();
        outsourcingPurchasePlanServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        outsourcingPurchasePlanServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeOutsourcingPurchasePlan();
        await comp.$nextTick(); // clear components

        // THEN
        expect(outsourcingPurchasePlanServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(outsourcingPurchasePlanServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
