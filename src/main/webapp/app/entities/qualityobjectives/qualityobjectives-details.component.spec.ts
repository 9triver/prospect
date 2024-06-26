/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import QualityobjectivesDetails from './qualityobjectives-details.vue';
import QualityobjectivesService from './qualityobjectives.service';
import AlertService from '@/shared/alert/alert.service';

type QualityobjectivesDetailsComponentType = InstanceType<typeof QualityobjectivesDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const qualityobjectivesSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Qualityobjectives Management Detail Component', () => {
    let qualityobjectivesServiceStub: SinonStubbedInstance<QualityobjectivesService>;
    let mountOptions: MountingOptions<QualityobjectivesDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      qualityobjectivesServiceStub = sinon.createStubInstance<QualityobjectivesService>(QualityobjectivesService);

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
          qualityobjectivesService: () => qualityobjectivesServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        qualityobjectivesServiceStub.find.resolves(qualityobjectivesSample);
        route = {
          params: {
            qualityobjectivesId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(QualityobjectivesDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.qualityobjectives).toMatchObject(qualityobjectivesSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        qualityobjectivesServiceStub.find.resolves(qualityobjectivesSample);
        const wrapper = shallowMount(QualityobjectivesDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
