/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import MonthplanDetails from './monthplan-details.vue';
import MonthplanService from './monthplan.service';
import AlertService from '@/shared/alert/alert.service';

type MonthplanDetailsComponentType = InstanceType<typeof MonthplanDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const monthplanSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Monthplan Management Detail Component', () => {
    let monthplanServiceStub: SinonStubbedInstance<MonthplanService>;
    let mountOptions: MountingOptions<MonthplanDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      monthplanServiceStub = sinon.createStubInstance<MonthplanService>(MonthplanService);

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
          monthplanService: () => monthplanServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        monthplanServiceStub.find.resolves(monthplanSample);
        route = {
          params: {
            monthplanId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(MonthplanDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.monthplan).toMatchObject(monthplanSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        monthplanServiceStub.find.resolves(monthplanSample);
        const wrapper = shallowMount(MonthplanDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
