/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RiskPossibilityDetails from './risk-possibility-details.vue';
import RiskPossibilityService from './risk-possibility.service';
import AlertService from '@/shared/alert/alert.service';

type RiskPossibilityDetailsComponentType = InstanceType<typeof RiskPossibilityDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const riskPossibilitySample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('RiskPossibility Management Detail Component', () => {
    let riskPossibilityServiceStub: SinonStubbedInstance<RiskPossibilityService>;
    let mountOptions: MountingOptions<RiskPossibilityDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      riskPossibilityServiceStub = sinon.createStubInstance<RiskPossibilityService>(RiskPossibilityService);

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
          riskPossibilityService: () => riskPossibilityServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        riskPossibilityServiceStub.find.resolves(riskPossibilitySample);
        route = {
          params: {
            riskPossibilityId: '' + 123,
          },
        };
        const wrapper = shallowMount(RiskPossibilityDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.riskPossibility).toMatchObject(riskPossibilitySample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        riskPossibilityServiceStub.find.resolves(riskPossibilitySample);
        const wrapper = shallowMount(RiskPossibilityDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
