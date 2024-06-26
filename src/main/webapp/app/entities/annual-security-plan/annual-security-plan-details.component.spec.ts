/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import AnnualSecurityPlanDetails from './annual-security-plan-details.vue';
import AnnualSecurityPlanService from './annual-security-plan.service';
import AlertService from '@/shared/alert/alert.service';

type AnnualSecurityPlanDetailsComponentType = InstanceType<typeof AnnualSecurityPlanDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const annualSecurityPlanSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('AnnualSecurityPlan Management Detail Component', () => {
    let annualSecurityPlanServiceStub: SinonStubbedInstance<AnnualSecurityPlanService>;
    let mountOptions: MountingOptions<AnnualSecurityPlanDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      annualSecurityPlanServiceStub = sinon.createStubInstance<AnnualSecurityPlanService>(AnnualSecurityPlanService);

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
          annualSecurityPlanService: () => annualSecurityPlanServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        annualSecurityPlanServiceStub.find.resolves(annualSecurityPlanSample);
        route = {
          params: {
            annualSecurityPlanId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(AnnualSecurityPlanDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.annualSecurityPlan).toMatchObject(annualSecurityPlanSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        annualSecurityPlanServiceStub.find.resolves(annualSecurityPlanSample);
        const wrapper = shallowMount(AnnualSecurityPlanDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
