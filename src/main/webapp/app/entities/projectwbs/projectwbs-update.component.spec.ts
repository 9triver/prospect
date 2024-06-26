/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProjectwbsUpdate from './projectwbs-update.vue';
import ProjectwbsService from './projectwbs.service';
import AlertService from '@/shared/alert/alert.service';

import CycleplanService from '@/entities/cycleplan/cycleplan.service';
import ProgressmanagementService from '@/entities/progressmanagement/progressmanagement.service';
import QualitymanagementService from '@/entities/qualitymanagement/qualitymanagement.service';
import FundsmanagementService from '@/entities/fundsmanagement/fundsmanagement.service';
import TechnicalmanagementService from '@/entities/technicalmanagement/technicalmanagement.service';
import ContractualfundsService from '@/entities/contractualfunds/contractualfunds.service';
import OutsourcingmanagementService from '@/entities/outsourcingmanagement/outsourcingmanagement.service';
import ResourcemanagementService from '@/entities/resourcemanagement/resourcemanagement.service';
import RiskmanagementService from '@/entities/riskmanagement/riskmanagement.service';
import SecuritymanagementService from '@/entities/securitymanagement/securitymanagement.service';
import DocumentService from '@/entities/document/document.service';
import SafetycheckService from '@/entities/safetycheck/safetycheck.service';
import DepartmentService from '@/entities/department/department.service';
import EvaluationCriteriaService from '@/entities/evaluation-criteria/evaluation-criteria.service';

type ProjectwbsUpdateComponentType = InstanceType<typeof ProjectwbsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const projectwbsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ProjectwbsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Projectwbs Management Update Component', () => {
    let comp: ProjectwbsUpdateComponentType;
    let projectwbsServiceStub: SinonStubbedInstance<ProjectwbsService>;

    beforeEach(() => {
      route = {};
      projectwbsServiceStub = sinon.createStubInstance<ProjectwbsService>(ProjectwbsService);
      projectwbsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          projectwbsService: () => projectwbsServiceStub,
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
          technicalmanagementService: () =>
            sinon.createStubInstance<TechnicalmanagementService>(TechnicalmanagementService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          contractualfundsService: () =>
            sinon.createStubInstance<ContractualfundsService>(ContractualfundsService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          outsourcingmanagementService: () =>
            sinon.createStubInstance<OutsourcingmanagementService>(OutsourcingmanagementService, {
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
          securitymanagementService: () =>
            sinon.createStubInstance<SecuritymanagementService>(SecuritymanagementService, {
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
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(ProjectwbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.projectwbs = projectwbsSample;
        projectwbsServiceStub.update.resolves(projectwbsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(projectwbsServiceStub.update.calledWith(projectwbsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        projectwbsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ProjectwbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.projectwbs = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(projectwbsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        projectwbsServiceStub.find.resolves(projectwbsSample);
        projectwbsServiceStub.retrieve.resolves([projectwbsSample]);

        // WHEN
        route = {
          params: {
            projectwbsId: '' + projectwbsSample.id,
          },
        };
        const wrapper = shallowMount(ProjectwbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.projectwbs).toMatchObject(projectwbsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        projectwbsServiceStub.find.resolves(projectwbsSample);
        const wrapper = shallowMount(ProjectwbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
