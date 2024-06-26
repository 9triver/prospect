/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import AnnualSecurityPlan from './annual-security-plan.vue';
import AnnualSecurityPlanService from './annual-security-plan.service';
import AlertService from '@/shared/alert/alert.service';

type AnnualSecurityPlanComponentType = InstanceType<typeof AnnualSecurityPlan>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('AnnualSecurityPlan Management Component', () => {
    let annualSecurityPlanServiceStub: SinonStubbedInstance<AnnualSecurityPlanService>;
    let mountOptions: MountingOptions<AnnualSecurityPlanComponentType>['global'];

    beforeEach(() => {
      annualSecurityPlanServiceStub = sinon.createStubInstance<AnnualSecurityPlanService>(AnnualSecurityPlanService);
      annualSecurityPlanServiceStub.retrieve.resolves({ headers: {} });

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
          annualSecurityPlanService: () => annualSecurityPlanServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        annualSecurityPlanServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(AnnualSecurityPlan, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(annualSecurityPlanServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.annualSecurityPlans[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: AnnualSecurityPlanComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(AnnualSecurityPlan, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        annualSecurityPlanServiceStub.retrieve.reset();
        annualSecurityPlanServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        annualSecurityPlanServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeAnnualSecurityPlan();
        await comp.$nextTick(); // clear components

        // THEN
        expect(annualSecurityPlanServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(annualSecurityPlanServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
