/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import PlanstrategyDetails from './planstrategy-details.vue';
import PlanstrategyService from './planstrategy.service';
import AlertService from '@/shared/alert/alert.service';

type PlanstrategyDetailsComponentType = InstanceType<typeof PlanstrategyDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const planstrategySample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Planstrategy Management Detail Component', () => {
    let planstrategyServiceStub: SinonStubbedInstance<PlanstrategyService>;
    let mountOptions: MountingOptions<PlanstrategyDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      planstrategyServiceStub = sinon.createStubInstance<PlanstrategyService>(PlanstrategyService);

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
          planstrategyService: () => planstrategyServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        planstrategyServiceStub.find.resolves(planstrategySample);
        route = {
          params: {
            planstrategyId: '' + 123,
          },
        };
        const wrapper = shallowMount(PlanstrategyDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.planstrategy).toMatchObject(planstrategySample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        planstrategyServiceStub.find.resolves(planstrategySample);
        const wrapper = shallowMount(PlanstrategyDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
