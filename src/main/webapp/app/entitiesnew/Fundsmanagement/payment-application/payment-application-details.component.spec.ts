/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import PaymentApplicationDetails from './payment-application-details.vue';
import PaymentApplicationService from './payment-application.service';
import AlertService from '@/shared/alert/alert.service';

type PaymentApplicationDetailsComponentType = InstanceType<typeof PaymentApplicationDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const paymentApplicationSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('PaymentApplication Management Detail Component', () => {
    let paymentApplicationServiceStub: SinonStubbedInstance<PaymentApplicationService>;
    let mountOptions: MountingOptions<PaymentApplicationDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      paymentApplicationServiceStub = sinon.createStubInstance<PaymentApplicationService>(PaymentApplicationService);

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
          paymentApplicationService: () => paymentApplicationServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        paymentApplicationServiceStub.find.resolves(paymentApplicationSample);
        route = {
          params: {
            paymentApplicationId: '' + 123,
          },
        };
        const wrapper = shallowMount(PaymentApplicationDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.paymentApplication).toMatchObject(paymentApplicationSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        paymentApplicationServiceStub.find.resolves(paymentApplicationSample);
        const wrapper = shallowMount(PaymentApplicationDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
