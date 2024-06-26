/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import FundsmanagementDetails from './fundsmanagement-details.vue';
import FundsmanagementService from './fundsmanagement.service';
import AlertService from '@/shared/alert/alert.service';

type FundsmanagementDetailsComponentType = InstanceType<typeof FundsmanagementDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const fundsmanagementSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Fundsmanagement Management Detail Component', () => {
    let fundsmanagementServiceStub: SinonStubbedInstance<FundsmanagementService>;
    let mountOptions: MountingOptions<FundsmanagementDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      fundsmanagementServiceStub = sinon.createStubInstance<FundsmanagementService>(FundsmanagementService);

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
          fundsmanagementService: () => fundsmanagementServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        fundsmanagementServiceStub.find.resolves(fundsmanagementSample);
        route = {
          params: {
            fundsmanagementId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(FundsmanagementDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.fundsmanagement).toMatchObject(fundsmanagementSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        fundsmanagementServiceStub.find.resolves(fundsmanagementSample);
        const wrapper = shallowMount(FundsmanagementDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
