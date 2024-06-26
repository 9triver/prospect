/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import PlanreturnsDetails from './planreturns-details.vue';
import PlanreturnsService from './planreturns.service';
import AlertService from '@/shared/alert/alert.service';

type PlanreturnsDetailsComponentType = InstanceType<typeof PlanreturnsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const planreturnsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Planreturns Management Detail Component', () => {
    let planreturnsServiceStub: SinonStubbedInstance<PlanreturnsService>;
    let mountOptions: MountingOptions<PlanreturnsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      planreturnsServiceStub = sinon.createStubInstance<PlanreturnsService>(PlanreturnsService);

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
          planreturnsService: () => planreturnsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        planreturnsServiceStub.find.resolves(planreturnsSample);
        route = {
          params: {
            planreturnsId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(PlanreturnsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.planreturns).toMatchObject(planreturnsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        planreturnsServiceStub.find.resolves(planreturnsSample);
        const wrapper = shallowMount(PlanreturnsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
