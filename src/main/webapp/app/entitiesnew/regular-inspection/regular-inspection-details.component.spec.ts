/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RegularInspectionDetails from './regular-inspection-details.vue';
import RegularInspectionService from './regular-inspection.service';
import AlertService from '@/shared/alert/alert.service';

type RegularInspectionDetailsComponentType = InstanceType<typeof RegularInspectionDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const regularInspectionSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('RegularInspection Management Detail Component', () => {
    let regularInspectionServiceStub: SinonStubbedInstance<RegularInspectionService>;
    let mountOptions: MountingOptions<RegularInspectionDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      regularInspectionServiceStub = sinon.createStubInstance<RegularInspectionService>(RegularInspectionService);

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
          regularInspectionService: () => regularInspectionServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        regularInspectionServiceStub.find.resolves(regularInspectionSample);
        route = {
          params: {
            regularInspectionId: '' + 123,
          },
        };
        const wrapper = shallowMount(RegularInspectionDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.regularInspection).toMatchObject(regularInspectionSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        regularInspectionServiceStub.find.resolves(regularInspectionSample);
        const wrapper = shallowMount(RegularInspectionDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
