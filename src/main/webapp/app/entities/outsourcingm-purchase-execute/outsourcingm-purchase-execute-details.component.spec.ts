/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import OutsourcingmPurchaseExecuteDetails from './outsourcingm-purchase-execute-details.vue';
import OutsourcingmPurchaseExecuteService from './outsourcingm-purchase-execute.service';
import AlertService from '@/shared/alert/alert.service';

type OutsourcingmPurchaseExecuteDetailsComponentType = InstanceType<typeof OutsourcingmPurchaseExecuteDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const outsourcingmPurchaseExecuteSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('OutsourcingmPurchaseExecute Management Detail Component', () => {
    let outsourcingmPurchaseExecuteServiceStub: SinonStubbedInstance<OutsourcingmPurchaseExecuteService>;
    let mountOptions: MountingOptions<OutsourcingmPurchaseExecuteDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      outsourcingmPurchaseExecuteServiceStub =
        sinon.createStubInstance<OutsourcingmPurchaseExecuteService>(OutsourcingmPurchaseExecuteService);

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
          outsourcingmPurchaseExecuteService: () => outsourcingmPurchaseExecuteServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        outsourcingmPurchaseExecuteServiceStub.find.resolves(outsourcingmPurchaseExecuteSample);
        route = {
          params: {
            outsourcingmPurchaseExecuteId: '' + 123,
          },
        };
        const wrapper = shallowMount(OutsourcingmPurchaseExecuteDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.outsourcingmPurchaseExecute).toMatchObject(outsourcingmPurchaseExecuteSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        outsourcingmPurchaseExecuteServiceStub.find.resolves(outsourcingmPurchaseExecuteSample);
        const wrapper = shallowMount(OutsourcingmPurchaseExecuteDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
