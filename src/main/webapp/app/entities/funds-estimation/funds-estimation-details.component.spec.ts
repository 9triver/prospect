/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import FundsEstimationDetails from './funds-estimation-details.vue';
import FundsEstimationService from './funds-estimation.service';
import AlertService from '@/shared/alert/alert.service';

type FundsEstimationDetailsComponentType = InstanceType<typeof FundsEstimationDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const fundsEstimationSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('FundsEstimation Management Detail Component', () => {
    let fundsEstimationServiceStub: SinonStubbedInstance<FundsEstimationService>;
    let mountOptions: MountingOptions<FundsEstimationDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      fundsEstimationServiceStub = sinon.createStubInstance<FundsEstimationService>(FundsEstimationService);

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
          fundsEstimationService: () => fundsEstimationServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        fundsEstimationServiceStub.find.resolves(fundsEstimationSample);
        route = {
          params: {
            fundsEstimationId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(FundsEstimationDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.fundsEstimation).toMatchObject(fundsEstimationSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        fundsEstimationServiceStub.find.resolves(fundsEstimationSample);
        const wrapper = shallowMount(FundsEstimationDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
