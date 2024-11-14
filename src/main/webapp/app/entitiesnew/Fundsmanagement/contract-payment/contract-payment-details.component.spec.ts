/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ContractPaymentDetails from './contract-payment-details.vue';
import ContractPaymentService from './contract-payment.service';
import AlertService from '@/shared/alert/alert.service';

type ContractPaymentDetailsComponentType = InstanceType<typeof ContractPaymentDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const contractPaymentSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ContractPayment Management Detail Component', () => {
    let contractPaymentServiceStub: SinonStubbedInstance<ContractPaymentService>;
    let mountOptions: MountingOptions<ContractPaymentDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      contractPaymentServiceStub = sinon.createStubInstance<ContractPaymentService>(ContractPaymentService);

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
          contractPaymentService: () => contractPaymentServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        contractPaymentServiceStub.find.resolves(contractPaymentSample);
        route = {
          params: {
            contractPaymentId: '' + 123,
          },
        };
        const wrapper = shallowMount(ContractPaymentDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.contractPayment).toMatchObject(contractPaymentSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        contractPaymentServiceStub.find.resolves(contractPaymentSample);
        const wrapper = shallowMount(ContractPaymentDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
