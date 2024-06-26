/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProgressplanDetails from './progressplan-details.vue';
import ProgressplanService from './progressplan.service';
import AlertService from '@/shared/alert/alert.service';

type ProgressplanDetailsComponentType = InstanceType<typeof ProgressplanDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const progressplanSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Progressplan Management Detail Component', () => {
    let progressplanServiceStub: SinonStubbedInstance<ProgressplanService>;
    let mountOptions: MountingOptions<ProgressplanDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      progressplanServiceStub = sinon.createStubInstance<ProgressplanService>(ProgressplanService);

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
          progressplanService: () => progressplanServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        progressplanServiceStub.find.resolves(progressplanSample);
        route = {
          params: {
            progressplanId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(ProgressplanDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.progressplan).toMatchObject(progressplanSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        progressplanServiceStub.find.resolves(progressplanSample);
        const wrapper = shallowMount(ProgressplanDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
