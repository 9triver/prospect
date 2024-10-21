/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import QualitytozeroDetails from './qualitytozero-details.vue';
import QualitytozeroService from './qualitytozero.service';
import AlertService from '@/shared/alert/alert.service';

type QualitytozeroDetailsComponentType = InstanceType<typeof QualitytozeroDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const qualitytozeroSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Qualitytozero Management Detail Component', () => {
    let qualitytozeroServiceStub: SinonStubbedInstance<QualitytozeroService>;
    let mountOptions: MountingOptions<QualitytozeroDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      qualitytozeroServiceStub = sinon.createStubInstance<QualitytozeroService>(QualitytozeroService);

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
          qualitytozeroService: () => qualitytozeroServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        qualitytozeroServiceStub.find.resolves(qualitytozeroSample);
        route = {
          params: {
            qualitytozeroId: '' + 123,
          },
        };
        const wrapper = shallowMount(QualitytozeroDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.qualitytozero).toMatchObject(qualitytozeroSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        qualitytozeroServiceStub.find.resolves(qualitytozeroSample);
        const wrapper = shallowMount(QualitytozeroDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
