/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProgressPlanDetails from './progress-plan-details.vue';
import ProgressPlanService from './progress-plan.service';
import AlertService from '@/shared/alert/alert.service';

type ProgressPlanDetailsComponentType = InstanceType<typeof ProgressPlanDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const progressPlanSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ProgressPlan Management Detail Component', () => {
    let progressPlanServiceStub: SinonStubbedInstance<ProgressPlanService>;
    let mountOptions: MountingOptions<ProgressPlanDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      progressPlanServiceStub = sinon.createStubInstance<ProgressPlanService>(ProgressPlanService);

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
          progressPlanService: () => progressPlanServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        progressPlanServiceStub.find.resolves(progressPlanSample);
        route = {
          params: {
            progressPlanId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(ProgressPlanDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.progressPlan).toMatchObject(progressPlanSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        progressPlanServiceStub.find.resolves(progressPlanSample);
        const wrapper = shallowMount(ProgressPlanDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
