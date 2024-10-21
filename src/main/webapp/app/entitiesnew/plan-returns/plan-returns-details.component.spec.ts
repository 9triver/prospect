/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import PlanReturnsDetails from './plan-returns-details.vue';
import PlanReturnsService from './plan-returns.service';
import AlertService from '@/shared/alert/alert.service';

type PlanReturnsDetailsComponentType = InstanceType<typeof PlanReturnsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const planReturnsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('PlanReturns Management Detail Component', () => {
    let planReturnsServiceStub: SinonStubbedInstance<PlanReturnsService>;
    let mountOptions: MountingOptions<PlanReturnsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      planReturnsServiceStub = sinon.createStubInstance<PlanReturnsService>(PlanReturnsService);

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
          planReturnsService: () => planReturnsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        planReturnsServiceStub.find.resolves(planReturnsSample);
        route = {
          params: {
            planReturnsId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(PlanReturnsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.planReturns).toMatchObject(planReturnsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        planReturnsServiceStub.find.resolves(planReturnsSample);
        const wrapper = shallowMount(PlanReturnsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
