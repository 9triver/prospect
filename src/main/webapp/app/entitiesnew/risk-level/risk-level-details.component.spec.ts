/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RiskLevelDetails from './risk-level-details.vue';
import RiskLevelService from './risk-level.service';
import AlertService from '@/shared/alert/alert.service';

type RiskLevelDetailsComponentType = InstanceType<typeof RiskLevelDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const riskLevelSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('RiskLevel Management Detail Component', () => {
    let riskLevelServiceStub: SinonStubbedInstance<RiskLevelService>;
    let mountOptions: MountingOptions<RiskLevelDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      riskLevelServiceStub = sinon.createStubInstance<RiskLevelService>(RiskLevelService);

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
          riskLevelService: () => riskLevelServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        riskLevelServiceStub.find.resolves(riskLevelSample);
        route = {
          params: {
            riskLevelId: '' + 123,
          },
        };
        const wrapper = shallowMount(RiskLevelDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.riskLevel).toMatchObject(riskLevelSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        riskLevelServiceStub.find.resolves(riskLevelSample);
        const wrapper = shallowMount(RiskLevelDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
