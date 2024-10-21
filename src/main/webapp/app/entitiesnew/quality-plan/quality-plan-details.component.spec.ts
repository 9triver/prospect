/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import QualityPlanDetails from './quality-plan-details.vue';
import QualityPlanService from './quality-plan.service';
import AlertService from '@/shared/alert/alert.service';

type QualityPlanDetailsComponentType = InstanceType<typeof QualityPlanDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const qualityPlanSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('QualityPlan Management Detail Component', () => {
    let qualityPlanServiceStub: SinonStubbedInstance<QualityPlanService>;
    let mountOptions: MountingOptions<QualityPlanDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      qualityPlanServiceStub = sinon.createStubInstance<QualityPlanService>(QualityPlanService);

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
          qualityPlanService: () => qualityPlanServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        qualityPlanServiceStub.find.resolves(qualityPlanSample);
        route = {
          params: {
            qualityPlanId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(QualityPlanDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.qualityPlan).toMatchObject(qualityPlanSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        qualityPlanServiceStub.find.resolves(qualityPlanSample);
        const wrapper = shallowMount(QualityPlanDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
