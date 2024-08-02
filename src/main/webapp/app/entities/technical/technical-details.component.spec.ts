/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import TechnicalDetails from './technical-details.vue';
import TechnicalService from './technical.service';
import AlertService from '@/shared/alert/alert.service';

type TechnicalDetailsComponentType = InstanceType<typeof TechnicalDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const technicalSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Technical Management Detail Component', () => {
    let technicalServiceStub: SinonStubbedInstance<TechnicalService>;
    let mountOptions: MountingOptions<TechnicalDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      technicalServiceStub = sinon.createStubInstance<TechnicalService>(TechnicalService);

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
          technicalService: () => technicalServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        technicalServiceStub.find.resolves(technicalSample);
        route = {
          params: {
            technicalId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(TechnicalDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.technical).toMatchObject(technicalSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        technicalServiceStub.find.resolves(technicalSample);
        const wrapper = shallowMount(TechnicalDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
