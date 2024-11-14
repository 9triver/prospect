/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import OtherPaymentDetails from './other-payment-details.vue';
import OtherPaymentService from './other-payment.service';
import AlertService from '@/shared/alert/alert.service';

type OtherPaymentDetailsComponentType = InstanceType<typeof OtherPaymentDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const otherPaymentSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('OtherPayment Management Detail Component', () => {
    let otherPaymentServiceStub: SinonStubbedInstance<OtherPaymentService>;
    let mountOptions: MountingOptions<OtherPaymentDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      otherPaymentServiceStub = sinon.createStubInstance<OtherPaymentService>(OtherPaymentService);

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
          otherPaymentService: () => otherPaymentServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        otherPaymentServiceStub.find.resolves(otherPaymentSample);
        route = {
          params: {
            otherPaymentId: '' + 123,
          },
        };
        const wrapper = shallowMount(OtherPaymentDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.otherPayment).toMatchObject(otherPaymentSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        otherPaymentServiceStub.find.resolves(otherPaymentSample);
        const wrapper = shallowMount(OtherPaymentDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
