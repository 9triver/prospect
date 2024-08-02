/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import QualityObjectivesDetails from './quality-objectives-details.vue';
import QualityObjectivesService from './quality-objectives.service';
import AlertService from '@/shared/alert/alert.service';

type QualityObjectivesDetailsComponentType = InstanceType<typeof QualityObjectivesDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const qualityObjectivesSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('QualityObjectives Management Detail Component', () => {
    let qualityObjectivesServiceStub: SinonStubbedInstance<QualityObjectivesService>;
    let mountOptions: MountingOptions<QualityObjectivesDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      qualityObjectivesServiceStub = sinon.createStubInstance<QualityObjectivesService>(QualityObjectivesService);

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
          qualityObjectivesService: () => qualityObjectivesServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        qualityObjectivesServiceStub.find.resolves(qualityObjectivesSample);
        route = {
          params: {
            qualityObjectivesId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(QualityObjectivesDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.qualityObjectives).toMatchObject(qualityObjectivesSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        qualityObjectivesServiceStub.find.resolves(qualityObjectivesSample);
        const wrapper = shallowMount(QualityObjectivesDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
