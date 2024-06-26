/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ManagementCapacityEvaluationUpdate from './management-capacity-evaluation-update.vue';
import ManagementCapacityEvaluationService from './management-capacity-evaluation.service';
import AlertService from '@/shared/alert/alert.service';

import EvaluationCriteriaService from '@/entities/evaluation-criteria/evaluation-criteria.service';
import ProjectService from '@/entities/project/project.service';
import OfficersService from '@/entities/officers/officers.service';

type ManagementCapacityEvaluationUpdateComponentType = InstanceType<typeof ManagementCapacityEvaluationUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const managementCapacityEvaluationSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ManagementCapacityEvaluationUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ManagementCapacityEvaluation Management Update Component', () => {
    let comp: ManagementCapacityEvaluationUpdateComponentType;
    let managementCapacityEvaluationServiceStub: SinonStubbedInstance<ManagementCapacityEvaluationService>;

    beforeEach(() => {
      route = {};
      managementCapacityEvaluationServiceStub =
        sinon.createStubInstance<ManagementCapacityEvaluationService>(ManagementCapacityEvaluationService);
      managementCapacityEvaluationServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          managementCapacityEvaluationService: () => managementCapacityEvaluationServiceStub,
          evaluationCriteriaService: () =>
            sinon.createStubInstance<EvaluationCriteriaService>(EvaluationCriteriaService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          projectService: () =>
            sinon.createStubInstance<ProjectService>(ProjectService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          officersService: () =>
            sinon.createStubInstance<OfficersService>(OfficersService, {
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
        const wrapper = shallowMount(ManagementCapacityEvaluationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.managementCapacityEvaluation = managementCapacityEvaluationSample;
        managementCapacityEvaluationServiceStub.update.resolves(managementCapacityEvaluationSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(managementCapacityEvaluationServiceStub.update.calledWith(managementCapacityEvaluationSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        managementCapacityEvaluationServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ManagementCapacityEvaluationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.managementCapacityEvaluation = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(managementCapacityEvaluationServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        managementCapacityEvaluationServiceStub.find.resolves(managementCapacityEvaluationSample);
        managementCapacityEvaluationServiceStub.retrieve.resolves([managementCapacityEvaluationSample]);

        // WHEN
        route = {
          params: {
            managementCapacityEvaluationId: '' + managementCapacityEvaluationSample.id,
          },
        };
        const wrapper = shallowMount(ManagementCapacityEvaluationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.managementCapacityEvaluation).toMatchObject(managementCapacityEvaluationSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        managementCapacityEvaluationServiceStub.find.resolves(managementCapacityEvaluationSample);
        const wrapper = shallowMount(ManagementCapacityEvaluationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
