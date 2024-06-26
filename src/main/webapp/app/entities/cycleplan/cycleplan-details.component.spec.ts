/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import CycleplanDetails from './cycleplan-details.vue';
import CycleplanService from './cycleplan.service';
import AlertService from '@/shared/alert/alert.service';

type CycleplanDetailsComponentType = InstanceType<typeof CycleplanDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const cycleplanSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Cycleplan Management Detail Component', () => {
    let cycleplanServiceStub: SinonStubbedInstance<CycleplanService>;
    let mountOptions: MountingOptions<CycleplanDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      cycleplanServiceStub = sinon.createStubInstance<CycleplanService>(CycleplanService);

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
          cycleplanService: () => cycleplanServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        cycleplanServiceStub.find.resolves(cycleplanSample);
        route = {
          params: {
            cycleplanId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(CycleplanDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.cycleplan).toMatchObject(cycleplanSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        cycleplanServiceStub.find.resolves(cycleplanSample);
        const wrapper = shallowMount(CycleplanDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
