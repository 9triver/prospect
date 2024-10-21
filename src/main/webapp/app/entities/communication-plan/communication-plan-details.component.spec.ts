/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import CommunicationPlanDetails from './communication-plan-details.vue';
import CommunicationPlanService from './communication-plan.service';
import AlertService from '@/shared/alert/alert.service';

type CommunicationPlanDetailsComponentType = InstanceType<typeof CommunicationPlanDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const communicationPlanSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('CommunicationPlan Management Detail Component', () => {
    let communicationPlanServiceStub: SinonStubbedInstance<CommunicationPlanService>;
    let mountOptions: MountingOptions<CommunicationPlanDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      communicationPlanServiceStub = sinon.createStubInstance<CommunicationPlanService>(CommunicationPlanService);

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
          communicationPlanService: () => communicationPlanServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        communicationPlanServiceStub.find.resolves(communicationPlanSample);
        route = {
          params: {
            communicationPlanId: '' + 123,
          },
        };
        const wrapper = shallowMount(CommunicationPlanDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.communicationPlan).toMatchObject(communicationPlanSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        communicationPlanServiceStub.find.resolves(communicationPlanSample);
        const wrapper = shallowMount(CommunicationPlanDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
