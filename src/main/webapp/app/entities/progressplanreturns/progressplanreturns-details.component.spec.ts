/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProgressplanreturnsDetails from './progressplanreturns-details.vue';
import ProgressplanreturnsService from './progressplanreturns.service';
import AlertService from '@/shared/alert/alert.service';

type ProgressplanreturnsDetailsComponentType = InstanceType<typeof ProgressplanreturnsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const progressplanreturnsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Progressplanreturns Management Detail Component', () => {
    let progressplanreturnsServiceStub: SinonStubbedInstance<ProgressplanreturnsService>;
    let mountOptions: MountingOptions<ProgressplanreturnsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      progressplanreturnsServiceStub = sinon.createStubInstance<ProgressplanreturnsService>(ProgressplanreturnsService);

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
          progressplanreturnsService: () => progressplanreturnsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        progressplanreturnsServiceStub.find.resolves(progressplanreturnsSample);
        route = {
          params: {
            progressplanreturnsId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(ProgressplanreturnsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.progressplanreturns).toMatchObject(progressplanreturnsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        progressplanreturnsServiceStub.find.resolves(progressplanreturnsSample);
        const wrapper = shallowMount(ProgressplanreturnsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
