/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EvaluationCriteriaDetails from './evaluation-criteria-details.vue';
import EvaluationCriteriaService from './evaluation-criteria.service';
import AlertService from '@/shared/alert/alert.service';

type EvaluationCriteriaDetailsComponentType = InstanceType<typeof EvaluationCriteriaDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const evaluationCriteriaSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('EvaluationCriteria Management Detail Component', () => {
    let evaluationCriteriaServiceStub: SinonStubbedInstance<EvaluationCriteriaService>;
    let mountOptions: MountingOptions<EvaluationCriteriaDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      evaluationCriteriaServiceStub = sinon.createStubInstance<EvaluationCriteriaService>(EvaluationCriteriaService);

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
          evaluationCriteriaService: () => evaluationCriteriaServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        evaluationCriteriaServiceStub.find.resolves(evaluationCriteriaSample);
        route = {
          params: {
            evaluationCriteriaId: '' + 123,
          },
        };
        const wrapper = shallowMount(EvaluationCriteriaDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.evaluationCriteria).toMatchObject(evaluationCriteriaSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        evaluationCriteriaServiceStub.find.resolves(evaluationCriteriaSample);
        const wrapper = shallowMount(EvaluationCriteriaDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
