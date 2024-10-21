/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SystemLevelDetails from './system-level-details.vue';
import SystemLevelService from './system-level.service';
import AlertService from '@/shared/alert/alert.service';

type SystemLevelDetailsComponentType = InstanceType<typeof SystemLevelDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const systemLevelSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('SystemLevel Management Detail Component', () => {
    let systemLevelServiceStub: SinonStubbedInstance<SystemLevelService>;
    let mountOptions: MountingOptions<SystemLevelDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      systemLevelServiceStub = sinon.createStubInstance<SystemLevelService>(SystemLevelService);

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
          systemLevelService: () => systemLevelServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        systemLevelServiceStub.find.resolves(systemLevelSample);
        route = {
          params: {
            systemLevelId: '' + 123,
          },
        };
        const wrapper = shallowMount(SystemLevelDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.systemLevel).toMatchObject(systemLevelSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        systemLevelServiceStub.find.resolves(systemLevelSample);
        const wrapper = shallowMount(SystemLevelDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
