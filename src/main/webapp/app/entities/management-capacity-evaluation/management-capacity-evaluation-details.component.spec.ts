/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ManagementCapacityEvaluationDetails from './management-capacity-evaluation-details.vue';
import ManagementCapacityEvaluationService from './management-capacity-evaluation.service';
import AlertService from '@/shared/alert/alert.service';

type ManagementCapacityEvaluationDetailsComponentType = InstanceType<typeof ManagementCapacityEvaluationDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const managementCapacityEvaluationSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ManagementCapacityEvaluation Management Detail Component', () => {
    let managementCapacityEvaluationServiceStub: SinonStubbedInstance<ManagementCapacityEvaluationService>;
    let mountOptions: MountingOptions<ManagementCapacityEvaluationDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      managementCapacityEvaluationServiceStub =
        sinon.createStubInstance<ManagementCapacityEvaluationService>(ManagementCapacityEvaluationService);

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
          managementCapacityEvaluationService: () => managementCapacityEvaluationServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        managementCapacityEvaluationServiceStub.find.resolves(managementCapacityEvaluationSample);
        route = {
          params: {
            managementCapacityEvaluationId: '' + 123,
          },
        };
        const wrapper = shallowMount(ManagementCapacityEvaluationDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.managementCapacityEvaluation).toMatchObject(managementCapacityEvaluationSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        managementCapacityEvaluationServiceStub.find.resolves(managementCapacityEvaluationSample);
        const wrapper = shallowMount(ManagementCapacityEvaluationDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
