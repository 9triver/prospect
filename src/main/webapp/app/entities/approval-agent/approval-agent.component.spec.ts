/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import ApprovalAgent from './approval-agent.vue';
import ApprovalAgentService from './approval-agent.service';
import AlertService from '@/shared/alert/alert.service';

type ApprovalAgentComponentType = InstanceType<typeof ApprovalAgent>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('ApprovalAgent Management Component', () => {
    let approvalAgentServiceStub: SinonStubbedInstance<ApprovalAgentService>;
    let mountOptions: MountingOptions<ApprovalAgentComponentType>['global'];

    beforeEach(() => {
      approvalAgentServiceStub = sinon.createStubInstance<ApprovalAgentService>(ApprovalAgentService);
      approvalAgentServiceStub.retrieve.resolves({ headers: {} });

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
          approvalAgentService: () => approvalAgentServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        approvalAgentServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(ApprovalAgent, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(approvalAgentServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.approvalAgents[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: ApprovalAgentComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(ApprovalAgent, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        approvalAgentServiceStub.retrieve.reset();
        approvalAgentServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        approvalAgentServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeApprovalAgent();
        await comp.$nextTick(); // clear components

        // THEN
        expect(approvalAgentServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(approvalAgentServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
