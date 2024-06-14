/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EvaluationCriteriaUpdate from './evaluation-criteria-update.vue';
import EvaluationCriteriaService from './evaluation-criteria.service';
import AlertService from '@/shared/alert/alert.service';

import DepartmentService from '@/entities/department/department.service';

type EvaluationCriteriaUpdateComponentType = InstanceType<typeof EvaluationCriteriaUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const evaluationCriteriaSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<EvaluationCriteriaUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('EvaluationCriteria Management Update Component', () => {
    let comp: EvaluationCriteriaUpdateComponentType;
    let evaluationCriteriaServiceStub: SinonStubbedInstance<EvaluationCriteriaService>;

    beforeEach(() => {
      route = {};
      evaluationCriteriaServiceStub = sinon.createStubInstance<EvaluationCriteriaService>(EvaluationCriteriaService);
      evaluationCriteriaServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'b-input-group': true,
          'b-input-group-prepend': true,
          'b-form-datepicker': true,
          'b-form-input': true,
        },
        provide: {
          alertService,
          evaluationCriteriaService: () => evaluationCriteriaServiceStub,
          departmentService: () =>
            sinon.createStubInstance<DepartmentService>(DepartmentService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(EvaluationCriteriaUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.evaluationCriteria = evaluationCriteriaSample;
        evaluationCriteriaServiceStub.update.resolves(evaluationCriteriaSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(evaluationCriteriaServiceStub.update.calledWith(evaluationCriteriaSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        evaluationCriteriaServiceStub.create.resolves(entity);
        const wrapper = shallowMount(EvaluationCriteriaUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.evaluationCriteria = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(evaluationCriteriaServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        evaluationCriteriaServiceStub.find.resolves(evaluationCriteriaSample);
        evaluationCriteriaServiceStub.retrieve.resolves([evaluationCriteriaSample]);

        // WHEN
        route = {
          params: {
            evaluationCriteriaId: '' + evaluationCriteriaSample.id,
          },
        };
        const wrapper = shallowMount(EvaluationCriteriaUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.evaluationCriteria).toMatchObject(evaluationCriteriaSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        evaluationCriteriaServiceStub.find.resolves(evaluationCriteriaSample);
        const wrapper = shallowMount(EvaluationCriteriaUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
