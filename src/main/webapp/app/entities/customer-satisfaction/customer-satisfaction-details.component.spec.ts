/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import CustomerSatisfactionDetails from './customer-satisfaction-details.vue';
import CustomerSatisfactionService from './customer-satisfaction.service';
import AlertService from '@/shared/alert/alert.service';

type CustomerSatisfactionDetailsComponentType = InstanceType<typeof CustomerSatisfactionDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const customerSatisfactionSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('CustomerSatisfaction Management Detail Component', () => {
    let customerSatisfactionServiceStub: SinonStubbedInstance<CustomerSatisfactionService>;
    let mountOptions: MountingOptions<CustomerSatisfactionDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      customerSatisfactionServiceStub = sinon.createStubInstance<CustomerSatisfactionService>(CustomerSatisfactionService);

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
          customerSatisfactionService: () => customerSatisfactionServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        customerSatisfactionServiceStub.find.resolves(customerSatisfactionSample);
        route = {
          params: {
            customerSatisfactionId: '' + 123,
          },
        };
        const wrapper = shallowMount(CustomerSatisfactionDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.customerSatisfaction).toMatchObject(customerSatisfactionSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        customerSatisfactionServiceStub.find.resolves(customerSatisfactionSample);
        const wrapper = shallowMount(CustomerSatisfactionDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
