/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ContractualfundsDetails from './contractualfunds-details.vue';
import ContractualfundsService from './contractualfunds.service';
import AlertService from '@/shared/alert/alert.service';

type ContractualfundsDetailsComponentType = InstanceType<typeof ContractualfundsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const contractualfundsSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Contractualfunds Management Detail Component', () => {
    let contractualfundsServiceStub: SinonStubbedInstance<ContractualfundsService>;
    let mountOptions: MountingOptions<ContractualfundsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      contractualfundsServiceStub = sinon.createStubInstance<ContractualfundsService>(ContractualfundsService);

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
          contractualfundsService: () => contractualfundsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        contractualfundsServiceStub.find.resolves(contractualfundsSample);
        route = {
          params: {
            contractualfundsId: '' + 123,
          },
        };
        const wrapper = shallowMount(ContractualfundsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.contractualfunds).toMatchObject(contractualfundsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        contractualfundsServiceStub.find.resolves(contractualfundsSample);
        const wrapper = shallowMount(ContractualfundsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
