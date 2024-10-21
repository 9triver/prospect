/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import PaymentCostListDetails from './payment-cost-list-details.vue';
import PaymentCostListService from './payment-cost-list.service';
import AlertService from '@/shared/alert/alert.service';

type PaymentCostListDetailsComponentType = InstanceType<typeof PaymentCostListDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const paymentCostListSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('PaymentCostList Management Detail Component', () => {
    let paymentCostListServiceStub: SinonStubbedInstance<PaymentCostListService>;
    let mountOptions: MountingOptions<PaymentCostListDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      paymentCostListServiceStub = sinon.createStubInstance<PaymentCostListService>(PaymentCostListService);

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
          paymentCostListService: () => paymentCostListServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        paymentCostListServiceStub.find.resolves(paymentCostListSample);
        route = {
          params: {
            paymentCostListId: '' + 123,
          },
        };
        const wrapper = shallowMount(PaymentCostListDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.paymentCostList).toMatchObject(paymentCostListSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        paymentCostListServiceStub.find.resolves(paymentCostListSample);
        const wrapper = shallowMount(PaymentCostListDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
