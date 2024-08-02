/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import QualityReturnsDetails from './quality-returns-details.vue';
import QualityReturnsService from './quality-returns.service';
import AlertService from '@/shared/alert/alert.service';

type QualityReturnsDetailsComponentType = InstanceType<typeof QualityReturnsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const qualityReturnsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('QualityReturns Management Detail Component', () => {
    let qualityReturnsServiceStub: SinonStubbedInstance<QualityReturnsService>;
    let mountOptions: MountingOptions<QualityReturnsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      qualityReturnsServiceStub = sinon.createStubInstance<QualityReturnsService>(QualityReturnsService);

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
          qualityReturnsService: () => qualityReturnsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        qualityReturnsServiceStub.find.resolves(qualityReturnsSample);
        route = {
          params: {
            qualityReturnsId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(QualityReturnsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.qualityReturns).toMatchObject(qualityReturnsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        qualityReturnsServiceStub.find.resolves(qualityReturnsSample);
        const wrapper = shallowMount(QualityReturnsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
