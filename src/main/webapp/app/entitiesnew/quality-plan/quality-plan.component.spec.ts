/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import QualityPlan from './quality-plan.vue';
import QualityPlanService from './quality-plan.service';
import AlertService from '@/shared/alert/alert.service';

type QualityPlanComponentType = InstanceType<typeof QualityPlan>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('QualityPlan Management Component', () => {
    let qualityPlanServiceStub: SinonStubbedInstance<QualityPlanService>;
    let mountOptions: MountingOptions<QualityPlanComponentType>['global'];

    beforeEach(() => {
      qualityPlanServiceStub = sinon.createStubInstance<QualityPlanService>(QualityPlanService);
      qualityPlanServiceStub.retrieve.resolves({ headers: {} });

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
          qualityPlanService: () => qualityPlanServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        qualityPlanServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(QualityPlan, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(qualityPlanServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.qualityPlans[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: QualityPlanComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(QualityPlan, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        qualityPlanServiceStub.retrieve.reset();
        qualityPlanServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        qualityPlanServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeQualityPlan();
        await comp.$nextTick(); // clear components

        // THEN
        expect(qualityPlanServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(qualityPlanServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
