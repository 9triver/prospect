/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import TotalbudgetDetails from './totalbudget-details.vue';
import TotalbudgetService from './totalbudget.service';
import AlertService from '@/shared/alert/alert.service';

type TotalbudgetDetailsComponentType = InstanceType<typeof TotalbudgetDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const totalbudgetSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Totalbudget Management Detail Component', () => {
    let totalbudgetServiceStub: SinonStubbedInstance<TotalbudgetService>;
    let mountOptions: MountingOptions<TotalbudgetDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      totalbudgetServiceStub = sinon.createStubInstance<TotalbudgetService>(TotalbudgetService);

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
          totalbudgetService: () => totalbudgetServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        totalbudgetServiceStub.find.resolves(totalbudgetSample);
        route = {
          params: {
            totalbudgetId: '' + 123,
          },
        };
        const wrapper = shallowMount(TotalbudgetDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.totalbudget).toMatchObject(totalbudgetSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        totalbudgetServiceStub.find.resolves(totalbudgetSample);
        const wrapper = shallowMount(TotalbudgetDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
