/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import OutsourcingPurchasePlanDetails from './outsourcing-purchase-plan-details.vue';
import OutsourcingPurchasePlanService from './outsourcing-purchase-plan.service';
import AlertService from '@/shared/alert/alert.service';

type OutsourcingPurchasePlanDetailsComponentType = InstanceType<typeof OutsourcingPurchasePlanDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const outsourcingPurchasePlanSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('OutsourcingPurchasePlan Management Detail Component', () => {
    let outsourcingPurchasePlanServiceStub: SinonStubbedInstance<OutsourcingPurchasePlanService>;
    let mountOptions: MountingOptions<OutsourcingPurchasePlanDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      outsourcingPurchasePlanServiceStub = sinon.createStubInstance<OutsourcingPurchasePlanService>(OutsourcingPurchasePlanService);

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
          outsourcingPurchasePlanService: () => outsourcingPurchasePlanServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        outsourcingPurchasePlanServiceStub.find.resolves(outsourcingPurchasePlanSample);
        route = {
          params: {
            outsourcingPurchasePlanId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(OutsourcingPurchasePlanDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.outsourcingPurchasePlan).toMatchObject(outsourcingPurchasePlanSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        outsourcingPurchasePlanServiceStub.find.resolves(outsourcingPurchasePlanSample);
        const wrapper = shallowMount(OutsourcingPurchasePlanDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
