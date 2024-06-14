/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RiskreportDetails from './riskreport-details.vue';
import RiskreportService from './riskreport.service';
import AlertService from '@/shared/alert/alert.service';

type RiskreportDetailsComponentType = InstanceType<typeof RiskreportDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const riskreportSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Riskreport Management Detail Component', () => {
    let riskreportServiceStub: SinonStubbedInstance<RiskreportService>;
    let mountOptions: MountingOptions<RiskreportDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      riskreportServiceStub = sinon.createStubInstance<RiskreportService>(RiskreportService);

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
          riskreportService: () => riskreportServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        riskreportServiceStub.find.resolves(riskreportSample);
        route = {
          params: {
            riskreportId: '' + 123,
          },
        };
        const wrapper = shallowMount(RiskreportDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.riskreport).toMatchObject(riskreportSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        riskreportServiceStub.find.resolves(riskreportSample);
        const wrapper = shallowMount(RiskreportDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
