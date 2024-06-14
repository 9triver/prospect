/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import PlanexecuteDetails from './planexecute-details.vue';
import PlanexecuteService from './planexecute.service';
import AlertService from '@/shared/alert/alert.service';

type PlanexecuteDetailsComponentType = InstanceType<typeof PlanexecuteDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const planexecuteSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Planexecute Management Detail Component', () => {
    let planexecuteServiceStub: SinonStubbedInstance<PlanexecuteService>;
    let mountOptions: MountingOptions<PlanexecuteDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      planexecuteServiceStub = sinon.createStubInstance<PlanexecuteService>(PlanexecuteService);

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
          planexecuteService: () => planexecuteServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        planexecuteServiceStub.find.resolves(planexecuteSample);
        route = {
          params: {
            planexecuteId: '' + 123,
          },
        };
        const wrapper = shallowMount(PlanexecuteDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.planexecute).toMatchObject(planexecuteSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        planexecuteServiceStub.find.resolves(planexecuteSample);
        const wrapper = shallowMount(PlanexecuteDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
