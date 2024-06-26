/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import OutsourcingPurchaseExecuteDetails from './outsourcing-purchase-execute-details.vue';
import OutsourcingPurchaseExecuteService from './outsourcing-purchase-execute.service';
import AlertService from '@/shared/alert/alert.service';

type OutsourcingPurchaseExecuteDetailsComponentType = InstanceType<typeof OutsourcingPurchaseExecuteDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const outsourcingPurchaseExecuteSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('OutsourcingPurchaseExecute Management Detail Component', () => {
    let outsourcingPurchaseExecuteServiceStub: SinonStubbedInstance<OutsourcingPurchaseExecuteService>;
    let mountOptions: MountingOptions<OutsourcingPurchaseExecuteDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      outsourcingPurchaseExecuteServiceStub =
        sinon.createStubInstance<OutsourcingPurchaseExecuteService>(OutsourcingPurchaseExecuteService);

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
          outsourcingPurchaseExecuteService: () => outsourcingPurchaseExecuteServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        outsourcingPurchaseExecuteServiceStub.find.resolves(outsourcingPurchaseExecuteSample);
        route = {
          params: {
            outsourcingPurchaseExecuteId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(OutsourcingPurchaseExecuteDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.outsourcingPurchaseExecute).toMatchObject(outsourcingPurchaseExecuteSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        outsourcingPurchaseExecuteServiceStub.find.resolves(outsourcingPurchaseExecuteSample);
        const wrapper = shallowMount(OutsourcingPurchaseExecuteDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
