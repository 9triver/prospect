/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RiskReportDetails from './risk-report-details.vue';
import RiskReportService from './risk-report.service';
import AlertService from '@/shared/alert/alert.service';

type RiskReportDetailsComponentType = InstanceType<typeof RiskReportDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const riskReportSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('RiskReport Management Detail Component', () => {
    let riskReportServiceStub: SinonStubbedInstance<RiskReportService>;
    let mountOptions: MountingOptions<RiskReportDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      riskReportServiceStub = sinon.createStubInstance<RiskReportService>(RiskReportService);

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
          riskReportService: () => riskReportServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        riskReportServiceStub.find.resolves(riskReportSample);
        route = {
          params: {
            riskReportId: '' + 123,
          },
        };
        const wrapper = shallowMount(RiskReportDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.riskReport).toMatchObject(riskReportSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        riskReportServiceStub.find.resolves(riskReportSample);
        const wrapper = shallowMount(RiskReportDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
