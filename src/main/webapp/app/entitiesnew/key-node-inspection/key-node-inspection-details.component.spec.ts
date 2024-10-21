/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import KeyNodeInspectionDetails from './key-node-inspection-details.vue';
import KeyNodeInspectionService from './key-node-inspection.service';
import AlertService from '@/shared/alert/alert.service';

type KeyNodeInspectionDetailsComponentType = InstanceType<typeof KeyNodeInspectionDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const keyNodeInspectionSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('KeyNodeInspection Management Detail Component', () => {
    let keyNodeInspectionServiceStub: SinonStubbedInstance<KeyNodeInspectionService>;
    let mountOptions: MountingOptions<KeyNodeInspectionDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      keyNodeInspectionServiceStub = sinon.createStubInstance<KeyNodeInspectionService>(KeyNodeInspectionService);

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
          keyNodeInspectionService: () => keyNodeInspectionServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        keyNodeInspectionServiceStub.find.resolves(keyNodeInspectionSample);
        route = {
          params: {
            keyNodeInspectionId: '' + 123,
          },
        };
        const wrapper = shallowMount(KeyNodeInspectionDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.keyNodeInspection).toMatchObject(keyNodeInspectionSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        keyNodeInspectionServiceStub.find.resolves(keyNodeInspectionSample);
        const wrapper = shallowMount(KeyNodeInspectionDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
