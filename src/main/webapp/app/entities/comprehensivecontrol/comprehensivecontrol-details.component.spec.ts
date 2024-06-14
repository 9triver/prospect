/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ComprehensivecontrolDetails from './comprehensivecontrol-details.vue';
import ComprehensivecontrolService from './comprehensivecontrol.service';
import AlertService from '@/shared/alert/alert.service';

type ComprehensivecontrolDetailsComponentType = InstanceType<typeof ComprehensivecontrolDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const comprehensivecontrolSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Comprehensivecontrol Management Detail Component', () => {
    let comprehensivecontrolServiceStub: SinonStubbedInstance<ComprehensivecontrolService>;
    let mountOptions: MountingOptions<ComprehensivecontrolDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      comprehensivecontrolServiceStub = sinon.createStubInstance<ComprehensivecontrolService>(ComprehensivecontrolService);

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
          comprehensivecontrolService: () => comprehensivecontrolServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        comprehensivecontrolServiceStub.find.resolves(comprehensivecontrolSample);
        route = {
          params: {
            comprehensivecontrolId: '' + 123,
          },
        };
        const wrapper = shallowMount(ComprehensivecontrolDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.comprehensivecontrol).toMatchObject(comprehensivecontrolSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comprehensivecontrolServiceStub.find.resolves(comprehensivecontrolSample);
        const wrapper = shallowMount(ComprehensivecontrolDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
