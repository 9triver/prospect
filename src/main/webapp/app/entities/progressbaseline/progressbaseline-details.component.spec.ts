/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProgressbaselineDetails from './progressbaseline-details.vue';
import ProgressbaselineService from './progressbaseline.service';
import AlertService from '@/shared/alert/alert.service';

type ProgressbaselineDetailsComponentType = InstanceType<typeof ProgressbaselineDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const progressbaselineSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Progressbaseline Management Detail Component', () => {
    let progressbaselineServiceStub: SinonStubbedInstance<ProgressbaselineService>;
    let mountOptions: MountingOptions<ProgressbaselineDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      progressbaselineServiceStub = sinon.createStubInstance<ProgressbaselineService>(ProgressbaselineService);

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
          progressbaselineService: () => progressbaselineServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        progressbaselineServiceStub.find.resolves(progressbaselineSample);
        route = {
          params: {
            progressbaselineId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(ProgressbaselineDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.progressbaseline).toMatchObject(progressbaselineSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        progressbaselineServiceStub.find.resolves(progressbaselineSample);
        const wrapper = shallowMount(ProgressbaselineDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
