/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RiskmanagementDetails from './riskmanagement-details.vue';
import RiskmanagementService from './riskmanagement.service';
import AlertService from '@/shared/alert/alert.service';

type RiskmanagementDetailsComponentType = InstanceType<typeof RiskmanagementDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const riskmanagementSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Riskmanagement Management Detail Component', () => {
    let riskmanagementServiceStub: SinonStubbedInstance<RiskmanagementService>;
    let mountOptions: MountingOptions<RiskmanagementDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      riskmanagementServiceStub = sinon.createStubInstance<RiskmanagementService>(RiskmanagementService);

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
          riskmanagementService: () => riskmanagementServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        riskmanagementServiceStub.find.resolves(riskmanagementSample);
        route = {
          params: {
            riskmanagementId: '' + 123,
          },
        };
        const wrapper = shallowMount(RiskmanagementDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.riskmanagement).toMatchObject(riskmanagementSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        riskmanagementServiceStub.find.resolves(riskmanagementSample);
        const wrapper = shallowMount(RiskmanagementDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
