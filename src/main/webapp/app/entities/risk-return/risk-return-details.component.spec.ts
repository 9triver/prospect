/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RiskReturnDetails from './risk-return-details.vue';
import RiskReturnService from './risk-return.service';
import AlertService from '@/shared/alert/alert.service';

type RiskReturnDetailsComponentType = InstanceType<typeof RiskReturnDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const riskReturnSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('RiskReturn Management Detail Component', () => {
    let riskReturnServiceStub: SinonStubbedInstance<RiskReturnService>;
    let mountOptions: MountingOptions<RiskReturnDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      riskReturnServiceStub = sinon.createStubInstance<RiskReturnService>(RiskReturnService);

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
          riskReturnService: () => riskReturnServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        riskReturnServiceStub.find.resolves(riskReturnSample);
        route = {
          params: {
            riskReturnId: '' + 123,
          },
        };
        const wrapper = shallowMount(RiskReturnDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.riskReturn).toMatchObject(riskReturnSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        riskReturnServiceStub.find.resolves(riskReturnSample);
        const wrapper = shallowMount(RiskReturnDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
