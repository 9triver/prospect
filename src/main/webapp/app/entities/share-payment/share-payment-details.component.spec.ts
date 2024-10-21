/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SharePaymentDetails from './share-payment-details.vue';
import SharePaymentService from './share-payment.service';
import AlertService from '@/shared/alert/alert.service';

type SharePaymentDetailsComponentType = InstanceType<typeof SharePaymentDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const sharePaymentSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('SharePayment Management Detail Component', () => {
    let sharePaymentServiceStub: SinonStubbedInstance<SharePaymentService>;
    let mountOptions: MountingOptions<SharePaymentDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      sharePaymentServiceStub = sinon.createStubInstance<SharePaymentService>(SharePaymentService);

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
          sharePaymentService: () => sharePaymentServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        sharePaymentServiceStub.find.resolves(sharePaymentSample);
        route = {
          params: {
            sharePaymentId: '' + 123,
          },
        };
        const wrapper = shallowMount(SharePaymentDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.sharePayment).toMatchObject(sharePaymentSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        sharePaymentServiceStub.find.resolves(sharePaymentSample);
        const wrapper = shallowMount(SharePaymentDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
