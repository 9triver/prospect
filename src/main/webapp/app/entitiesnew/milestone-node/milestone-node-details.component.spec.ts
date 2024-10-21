/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import MilestoneNodeDetails from './milestone-node-details.vue';
import MilestoneNodeService from './milestone-node.service';
import AlertService from '@/shared/alert/alert.service';

type MilestoneNodeDetailsComponentType = InstanceType<typeof MilestoneNodeDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const milestoneNodeSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('MilestoneNode Management Detail Component', () => {
    let milestoneNodeServiceStub: SinonStubbedInstance<MilestoneNodeService>;
    let mountOptions: MountingOptions<MilestoneNodeDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      milestoneNodeServiceStub = sinon.createStubInstance<MilestoneNodeService>(MilestoneNodeService);

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
          milestoneNodeService: () => milestoneNodeServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        milestoneNodeServiceStub.find.resolves(milestoneNodeSample);
        route = {
          params: {
            milestoneNodeId: '' + 123,
          },
        };
        const wrapper = shallowMount(MilestoneNodeDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.milestoneNode).toMatchObject(milestoneNodeSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        milestoneNodeServiceStub.find.resolves(milestoneNodeSample);
        const wrapper = shallowMount(MilestoneNodeDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
