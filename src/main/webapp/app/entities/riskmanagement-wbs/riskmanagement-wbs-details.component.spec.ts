/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RiskmanagementWbsDetails from './riskmanagement-wbs-details.vue';
import RiskmanagementWbsService from './riskmanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

type RiskmanagementWbsDetailsComponentType = InstanceType<typeof RiskmanagementWbsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const riskmanagementWbsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('RiskmanagementWbs Management Detail Component', () => {
    let riskmanagementWbsServiceStub: SinonStubbedInstance<RiskmanagementWbsService>;
    let mountOptions: MountingOptions<RiskmanagementWbsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      riskmanagementWbsServiceStub = sinon.createStubInstance<RiskmanagementWbsService>(RiskmanagementWbsService);

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
          riskmanagementWbsService: () => riskmanagementWbsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        riskmanagementWbsServiceStub.find.resolves(riskmanagementWbsSample);
        route = {
          params: {
            riskmanagementWbsId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(RiskmanagementWbsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.riskmanagementWbs).toMatchObject(riskmanagementWbsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        riskmanagementWbsServiceStub.find.resolves(riskmanagementWbsSample);
        const wrapper = shallowMount(RiskmanagementWbsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
