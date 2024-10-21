/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import QualityObjectivesDictionaryDetails from './quality-objectives-dictionary-details.vue';
import QualityObjectivesDictionaryService from './quality-objectives-dictionary.service';
import AlertService from '@/shared/alert/alert.service';

type QualityObjectivesDictionaryDetailsComponentType = InstanceType<typeof QualityObjectivesDictionaryDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const qualityObjectivesDictionarySample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('QualityObjectivesDictionary Management Detail Component', () => {
    let qualityObjectivesDictionaryServiceStub: SinonStubbedInstance<QualityObjectivesDictionaryService>;
    let mountOptions: MountingOptions<QualityObjectivesDictionaryDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      qualityObjectivesDictionaryServiceStub =
        sinon.createStubInstance<QualityObjectivesDictionaryService>(QualityObjectivesDictionaryService);

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
          qualityObjectivesDictionaryService: () => qualityObjectivesDictionaryServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        qualityObjectivesDictionaryServiceStub.find.resolves(qualityObjectivesDictionarySample);
        route = {
          params: {
            qualityObjectivesDictionaryId: '' + 123,
          },
        };
        const wrapper = shallowMount(QualityObjectivesDictionaryDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.qualityObjectivesDictionary).toMatchObject(qualityObjectivesDictionarySample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        qualityObjectivesDictionaryServiceStub.find.resolves(qualityObjectivesDictionarySample);
        const wrapper = shallowMount(QualityObjectivesDictionaryDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
