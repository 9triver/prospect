/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import FundsmanagementWbsDetails from './fundsmanagement-wbs-details.vue';
import FundsmanagementWbsService from './fundsmanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

type FundsmanagementWbsDetailsComponentType = InstanceType<typeof FundsmanagementWbsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const fundsmanagementWbsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('FundsmanagementWbs Management Detail Component', () => {
    let fundsmanagementWbsServiceStub: SinonStubbedInstance<FundsmanagementWbsService>;
    let mountOptions: MountingOptions<FundsmanagementWbsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      fundsmanagementWbsServiceStub = sinon.createStubInstance<FundsmanagementWbsService>(FundsmanagementWbsService);

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
          fundsmanagementWbsService: () => fundsmanagementWbsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        fundsmanagementWbsServiceStub.find.resolves(fundsmanagementWbsSample);
        route = {
          params: {
            fundsmanagementWbsId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(FundsmanagementWbsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.fundsmanagementWbs).toMatchObject(fundsmanagementWbsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        fundsmanagementWbsServiceStub.find.resolves(fundsmanagementWbsSample);
        const wrapper = shallowMount(FundsmanagementWbsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
