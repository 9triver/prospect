/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ApprovalAgentDetails from './approval-agent-details.vue';
import ApprovalAgentService from './approval-agent.service';
import AlertService from '@/shared/alert/alert.service';

type ApprovalAgentDetailsComponentType = InstanceType<typeof ApprovalAgentDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const approvalAgentSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ApprovalAgent Management Detail Component', () => {
    let approvalAgentServiceStub: SinonStubbedInstance<ApprovalAgentService>;
    let mountOptions: MountingOptions<ApprovalAgentDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      approvalAgentServiceStub = sinon.createStubInstance<ApprovalAgentService>(ApprovalAgentService);

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'router-link': true,
        },
        provide: {
          alertService,
          approvalAgentService: () => approvalAgentServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        approvalAgentServiceStub.find.resolves(approvalAgentSample);
        route = {
          params: {
            approvalAgentId: '' + 123,
          },
        };
        const wrapper = shallowMount(ApprovalAgentDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.approvalAgent).toMatchObject(approvalAgentSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        approvalAgentServiceStub.find.resolves(approvalAgentSample);
        const wrapper = shallowMount(ApprovalAgentDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
