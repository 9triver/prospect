/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import PlanmonitorDetails from './planmonitor-details.vue';
import PlanmonitorService from './planmonitor.service';
import AlertService from '@/shared/alert/alert.service';

type PlanmonitorDetailsComponentType = InstanceType<typeof PlanmonitorDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const planmonitorSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Planmonitor Management Detail Component', () => {
    let planmonitorServiceStub: SinonStubbedInstance<PlanmonitorService>;
    let mountOptions: MountingOptions<PlanmonitorDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      planmonitorServiceStub = sinon.createStubInstance<PlanmonitorService>(PlanmonitorService);

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
          planmonitorService: () => planmonitorServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        planmonitorServiceStub.find.resolves(planmonitorSample);
        route = {
          params: {
            planmonitorId: '' + 123,
          },
        };
        const wrapper = shallowMount(PlanmonitorDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.planmonitor).toMatchObject(planmonitorSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        planmonitorServiceStub.find.resolves(planmonitorSample);
        const wrapper = shallowMount(PlanmonitorDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
