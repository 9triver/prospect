/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import CommunicationPlan from './communication-plan.vue';
import CommunicationPlanService from './communication-plan.service';
import AlertService from '@/shared/alert/alert.service';

type CommunicationPlanComponentType = InstanceType<typeof CommunicationPlan>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('CommunicationPlan Management Component', () => {
    let communicationPlanServiceStub: SinonStubbedInstance<CommunicationPlanService>;
    let mountOptions: MountingOptions<CommunicationPlanComponentType>['global'];

    beforeEach(() => {
      communicationPlanServiceStub = sinon.createStubInstance<CommunicationPlanService>(CommunicationPlanService);
      communicationPlanServiceStub.retrieve.resolves({ headers: {} });

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
          communicationPlanService: () => communicationPlanServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        communicationPlanServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(CommunicationPlan, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(communicationPlanServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.communicationPlans[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: CommunicationPlanComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(CommunicationPlan, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        communicationPlanServiceStub.retrieve.reset();
        communicationPlanServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        communicationPlanServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeCommunicationPlan();
        await comp.$nextTick(); // clear components

        // THEN
        expect(communicationPlanServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(communicationPlanServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
