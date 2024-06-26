/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import QualityreturnsDetails from './qualityreturns-details.vue';
import QualityreturnsService from './qualityreturns.service';
import AlertService from '@/shared/alert/alert.service';

type QualityreturnsDetailsComponentType = InstanceType<typeof QualityreturnsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const qualityreturnsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Qualityreturns Management Detail Component', () => {
    let qualityreturnsServiceStub: SinonStubbedInstance<QualityreturnsService>;
    let mountOptions: MountingOptions<QualityreturnsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      qualityreturnsServiceStub = sinon.createStubInstance<QualityreturnsService>(QualityreturnsService);

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
          qualityreturnsService: () => qualityreturnsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        qualityreturnsServiceStub.find.resolves(qualityreturnsSample);
        route = {
          params: {
            qualityreturnsId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(QualityreturnsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.qualityreturns).toMatchObject(qualityreturnsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        qualityreturnsServiceStub.find.resolves(qualityreturnsSample);
        const wrapper = shallowMount(QualityreturnsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
