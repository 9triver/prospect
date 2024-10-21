/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SporadicPurchasePaymentDetails from './sporadic-purchase-payment-details.vue';
import SporadicPurchasePaymentService from './sporadic-purchase-payment.service';
import AlertService from '@/shared/alert/alert.service';

type SporadicPurchasePaymentDetailsComponentType = InstanceType<typeof SporadicPurchasePaymentDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const sporadicPurchasePaymentSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('SporadicPurchasePayment Management Detail Component', () => {
    let sporadicPurchasePaymentServiceStub: SinonStubbedInstance<SporadicPurchasePaymentService>;
    let mountOptions: MountingOptions<SporadicPurchasePaymentDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      sporadicPurchasePaymentServiceStub = sinon.createStubInstance<SporadicPurchasePaymentService>(SporadicPurchasePaymentService);

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
          sporadicPurchasePaymentService: () => sporadicPurchasePaymentServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        sporadicPurchasePaymentServiceStub.find.resolves(sporadicPurchasePaymentSample);
        route = {
          params: {
            sporadicPurchasePaymentId: '' + 123,
          },
        };
        const wrapper = shallowMount(SporadicPurchasePaymentDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.sporadicPurchasePayment).toMatchObject(sporadicPurchasePaymentSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        sporadicPurchasePaymentServiceStub.find.resolves(sporadicPurchasePaymentSample);
        const wrapper = shallowMount(SporadicPurchasePaymentDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
