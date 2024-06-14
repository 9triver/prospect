/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import OutsourcingmPurchasePlanDetails from './outsourcingm-purchase-plan-details.vue';
import OutsourcingmPurchasePlanService from './outsourcingm-purchase-plan.service';
import AlertService from '@/shared/alert/alert.service';

type OutsourcingmPurchasePlanDetailsComponentType = InstanceType<typeof OutsourcingmPurchasePlanDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const outsourcingmPurchasePlanSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('OutsourcingmPurchasePlan Management Detail Component', () => {
    let outsourcingmPurchasePlanServiceStub: SinonStubbedInstance<OutsourcingmPurchasePlanService>;
    let mountOptions: MountingOptions<OutsourcingmPurchasePlanDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      outsourcingmPurchasePlanServiceStub = sinon.createStubInstance<OutsourcingmPurchasePlanService>(OutsourcingmPurchasePlanService);

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
          outsourcingmPurchasePlanService: () => outsourcingmPurchasePlanServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        outsourcingmPurchasePlanServiceStub.find.resolves(outsourcingmPurchasePlanSample);
        route = {
          params: {
            outsourcingmPurchasePlanId: '' + 123,
          },
        };
        const wrapper = shallowMount(OutsourcingmPurchasePlanDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.outsourcingmPurchasePlan).toMatchObject(outsourcingmPurchasePlanSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        outsourcingmPurchasePlanServiceStub.find.resolves(outsourcingmPurchasePlanSample);
        const wrapper = shallowMount(OutsourcingmPurchasePlanDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
