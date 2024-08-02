/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import CostControlSystemDetails from './cost-control-system-details.vue';
import CostControlSystemService from './cost-control-system.service';
import AlertService from '@/shared/alert/alert.service';

type CostControlSystemDetailsComponentType = InstanceType<typeof CostControlSystemDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const costControlSystemSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('CostControlSystem Management Detail Component', () => {
    let costControlSystemServiceStub: SinonStubbedInstance<CostControlSystemService>;
    let mountOptions: MountingOptions<CostControlSystemDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      costControlSystemServiceStub = sinon.createStubInstance<CostControlSystemService>(CostControlSystemService);

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
          costControlSystemService: () => costControlSystemServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        costControlSystemServiceStub.find.resolves(costControlSystemSample);
        route = {
          params: {
            costControlSystemId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(CostControlSystemDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.costControlSystem).toMatchObject(costControlSystemSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        costControlSystemServiceStub.find.resolves(costControlSystemSample);
        const wrapper = shallowMount(CostControlSystemDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
