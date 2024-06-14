/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProjectUpdate from './project-update.vue';
import ProjectService from './project.service';
import AlertService from '@/shared/alert/alert.service';

import CycleplanService from '@/entities/cycleplan/cycleplan.service';
import ProgressmanagementService from '@/entities/progressmanagement/progressmanagement.service';
import QualitymanagementService from '@/entities/qualitymanagement/qualitymanagement.service';
import FundsmanagementService from '@/entities/fundsmanagement/fundsmanagement.service';
import TechnicalConditionService from '@/entities/technical-condition/technical-condition.service';
import ContractualfundsService from '@/entities/contractualfunds/contractualfunds.service';
import OutsourcingmPurchaseExecuteService from '@/entities/outsourcingm-purchase-execute/outsourcingm-purchase-execute.service';
import ResourcemanagementService from '@/entities/resourcemanagement/resourcemanagement.service';
import RiskmanagementService from '@/entities/riskmanagement/riskmanagement.service';
import DocumentService from '@/entities/document/document.service';
import SafetycheckService from '@/entities/safetycheck/safetycheck.service';
import DepartmentService from '@/entities/department/department.service';
import EvaluationCriteriaService from '@/entities/evaluation-criteria/evaluation-criteria.service';
import OfficersService from '@/entities/officers/officers.service';
import ProjectSecrecyService from '@/entities/project-secrecy/project-secrecy.service';

type ProjectUpdateComponentType = InstanceType<typeof ProjectUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const projectSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ProjectUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Project Management Update Component', () => {
    let comp: ProjectUpdateComponentType;
    let projectServiceStub: SinonStubbedInstance<ProjectService>;

    beforeEach(() => {
      route = {};
      projectServiceStub = sinon.createStubInstance<ProjectService>(ProjectService);
      projectServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          projectService: () => projectServiceStub,
          cycleplanService: () =>
            sinon.createStubInstance<CycleplanService>(CycleplanService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          progressmanagementService: () =>
            sinon.createStubInstance<ProgressmanagementService>(ProgressmanagementService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          qualitymanagementService: () =>
            sinon.createStubInstance<QualitymanagementService>(QualitymanagementService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          fundsmanagementService: () =>
            sinon.createStubInstance<FundsmanagementService>(FundsmanagementService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          technicalConditionService: () =>
            sinon.createStubInstance<TechnicalConditionService>(TechnicalConditionService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          contractualfundsService: () =>
            sinon.createStubInstance<ContractualfundsService>(ContractualfundsService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          outsourcingmPurchaseExecuteService: () =>
            sinon.createStubInstance<OutsourcingmPurchaseExecuteService>(OutsourcingmPurchaseExecuteService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          resourcemanagementService: () =>
            sinon.createStubInstance<ResourcemanagementService>(ResourcemanagementService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          riskmanagementService: () =>
            sinon.createStubInstance<RiskmanagementService>(RiskmanagementService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          documentService: () =>
            sinon.createStubInstance<DocumentService>(DocumentService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          safetycheckService: () =>
            sinon.createStubInstance<SafetycheckService>(SafetycheckService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          departmentService: () =>
            sinon.createStubInstance<DepartmentService>(DepartmentService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          evaluationCriteriaService: () =>
            sinon.createStubInstance<EvaluationCriteriaService>(EvaluationCriteriaService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          officersService: () =>
            sinon.createStubInstance<OfficersService>(OfficersService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          projectSecrecyService: () =>
            sinon.createStubInstance<ProjectSecrecyService>(ProjectSecrecyService, {
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
        const wrapper = shallowMount(ProjectUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.project = projectSample;
        projectServiceStub.update.resolves(projectSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(projectServiceStub.update.calledWith(projectSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        projectServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ProjectUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.project = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(projectServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        projectServiceStub.find.resolves(projectSample);
        projectServiceStub.retrieve.resolves([projectSample]);

        // WHEN
        route = {
          params: {
            projectId: '' + projectSample.id,
          },
        };
        const wrapper = shallowMount(ProjectUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.project).toMatchObject(projectSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        projectServiceStub.find.resolves(projectSample);
        const wrapper = shallowMount(ProjectUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
