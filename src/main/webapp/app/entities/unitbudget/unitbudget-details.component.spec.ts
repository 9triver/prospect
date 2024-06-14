/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import UnitbudgetDetails from './unitbudget-details.vue';
import UnitbudgetService from './unitbudget.service';
import AlertService from '@/shared/alert/alert.service';

type UnitbudgetDetailsComponentType = InstanceType<typeof UnitbudgetDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const unitbudgetSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Unitbudget Management Detail Component', () => {
    let unitbudgetServiceStub: SinonStubbedInstance<UnitbudgetService>;
    let mountOptions: MountingOptions<UnitbudgetDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      unitbudgetServiceStub = sinon.createStubInstance<UnitbudgetService>(UnitbudgetService);

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
          unitbudgetService: () => unitbudgetServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        unitbudgetServiceStub.find.resolves(unitbudgetSample);
        route = {
          params: {
            unitbudgetId: '' + 123,
          },
        };
        const wrapper = shallowMount(UnitbudgetDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.unitbudget).toMatchObject(unitbudgetSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        unitbudgetServiceStub.find.resolves(unitbudgetSample);
        const wrapper = shallowMount(UnitbudgetDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
