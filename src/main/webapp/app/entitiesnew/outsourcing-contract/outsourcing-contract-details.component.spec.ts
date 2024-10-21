/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import OutsourcingContractDetails from './outsourcing-contract-details.vue';
import OutsourcingContractService from './outsourcing-contract.service';
import AlertService from '@/shared/alert/alert.service';

type OutsourcingContractDetailsComponentType = InstanceType<typeof OutsourcingContractDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const outsourcingContractSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('OutsourcingContract Management Detail Component', () => {
    let outsourcingContractServiceStub: SinonStubbedInstance<OutsourcingContractService>;
    let mountOptions: MountingOptions<OutsourcingContractDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      outsourcingContractServiceStub = sinon.createStubInstance<OutsourcingContractService>(OutsourcingContractService);

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
          outsourcingContractService: () => outsourcingContractServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        outsourcingContractServiceStub.find.resolves(outsourcingContractSample);
        route = {
          params: {
            outsourcingContractId: '' + 123,
          },
        };
        const wrapper = shallowMount(OutsourcingContractDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.outsourcingContract).toMatchObject(outsourcingContractSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        outsourcingContractServiceStub.find.resolves(outsourcingContractSample);
        const wrapper = shallowMount(OutsourcingContractDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
