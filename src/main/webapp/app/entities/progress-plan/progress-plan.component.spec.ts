/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import ProgressPlan from './progress-plan.vue';
import ProgressPlanService from './progress-plan.service';
import AlertService from '@/shared/alert/alert.service';

type ProgressPlanComponentType = InstanceType<typeof ProgressPlan>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('ProgressPlan Management Component', () => {
    let progressPlanServiceStub: SinonStubbedInstance<ProgressPlanService>;
    let mountOptions: MountingOptions<ProgressPlanComponentType>['global'];

    beforeEach(() => {
      progressPlanServiceStub = sinon.createStubInstance<ProgressPlanService>(ProgressPlanService);
      progressPlanServiceStub.retrieve.resolves({ headers: {} });

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
          progressPlanService: () => progressPlanServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        progressPlanServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(ProgressPlan, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(progressPlanServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.progressPlans[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: ProgressPlanComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(ProgressPlan, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        progressPlanServiceStub.retrieve.reset();
        progressPlanServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        progressPlanServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeProgressPlan();
        await comp.$nextTick(); // clear components

        // THEN
        expect(progressPlanServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(progressPlanServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
