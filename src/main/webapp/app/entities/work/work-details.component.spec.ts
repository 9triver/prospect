/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import WorkDetails from './work-details.vue';
import WorkService from './work.service';
import AlertService from '@/shared/alert/alert.service';

type WorkDetailsComponentType = InstanceType<typeof WorkDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const workSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Work Management Detail Component', () => {
    let workServiceStub: SinonStubbedInstance<WorkService>;
    let mountOptions: MountingOptions<WorkDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      workServiceStub = sinon.createStubInstance<WorkService>(WorkService);

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
          workService: () => workServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        workServiceStub.find.resolves(workSample);
        route = {
          params: {
            workId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(WorkDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.work).toMatchObject(workSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        workServiceStub.find.resolves(workSample);
        const wrapper = shallowMount(WorkDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
