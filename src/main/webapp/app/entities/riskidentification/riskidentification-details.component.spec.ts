/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RiskidentificationDetails from './riskidentification-details.vue';
import RiskidentificationService from './riskidentification.service';
import AlertService from '@/shared/alert/alert.service';

type RiskidentificationDetailsComponentType = InstanceType<typeof RiskidentificationDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const riskidentificationSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Riskidentification Management Detail Component', () => {
    let riskidentificationServiceStub: SinonStubbedInstance<RiskidentificationService>;
    let mountOptions: MountingOptions<RiskidentificationDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      riskidentificationServiceStub = sinon.createStubInstance<RiskidentificationService>(RiskidentificationService);

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
          riskidentificationService: () => riskidentificationServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        riskidentificationServiceStub.find.resolves(riskidentificationSample);
        route = {
          params: {
            riskidentificationId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(RiskidentificationDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.riskidentification).toMatchObject(riskidentificationSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        riskidentificationServiceStub.find.resolves(riskidentificationSample);
        const wrapper = shallowMount(RiskidentificationDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
