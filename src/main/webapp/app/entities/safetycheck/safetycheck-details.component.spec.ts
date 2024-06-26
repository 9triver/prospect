/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SafetycheckDetails from './safetycheck-details.vue';
import SafetycheckService from './safetycheck.service';
import AlertService from '@/shared/alert/alert.service';

type SafetycheckDetailsComponentType = InstanceType<typeof SafetycheckDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const safetycheckSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Safetycheck Management Detail Component', () => {
    let safetycheckServiceStub: SinonStubbedInstance<SafetycheckService>;
    let mountOptions: MountingOptions<SafetycheckDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      safetycheckServiceStub = sinon.createStubInstance<SafetycheckService>(SafetycheckService);

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
          safetycheckService: () => safetycheckServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        safetycheckServiceStub.find.resolves(safetycheckSample);
        route = {
          params: {
            safetycheckId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(SafetycheckDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.safetycheck).toMatchObject(safetycheckSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        safetycheckServiceStub.find.resolves(safetycheckSample);
        const wrapper = shallowMount(SafetycheckDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
