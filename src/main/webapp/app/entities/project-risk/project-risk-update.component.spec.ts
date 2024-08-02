/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProjectRiskUpdate from './project-risk-update.vue';
import ProjectRiskService from './project-risk.service';
import AlertService from '@/shared/alert/alert.service';

import RiskReportService from '@/entities/risk-report/risk-report.service';
import OfficersService from '@/entities/officers/officers.service';
import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import ProgressPlanService from '@/entities/progress-plan/progress-plan.service';

type ProjectRiskUpdateComponentType = InstanceType<typeof ProjectRiskUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const projectRiskSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ProjectRiskUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ProjectRisk Management Update Component', () => {
    let comp: ProjectRiskUpdateComponentType;
    let projectRiskServiceStub: SinonStubbedInstance<ProjectRiskService>;

    beforeEach(() => {
      route = {};
      projectRiskServiceStub = sinon.createStubInstance<ProjectRiskService>(ProjectRiskService);
      projectRiskServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          projectRiskService: () => projectRiskServiceStub,
          riskReportService: () =>
            sinon.createStubInstance<RiskReportService>(RiskReportService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          officersService: () =>
            sinon.createStubInstance<OfficersService>(OfficersService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          projectwbsService: () =>
            sinon.createStubInstance<ProjectwbsService>(ProjectwbsService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          progressPlanService: () =>
            sinon.createStubInstance<ProgressPlanService>(ProgressPlanService, {
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
        const wrapper = shallowMount(ProjectRiskUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.projectRisk = projectRiskSample;
        projectRiskServiceStub.update.resolves(projectRiskSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(projectRiskServiceStub.update.calledWith(projectRiskSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        projectRiskServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ProjectRiskUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.projectRisk = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(projectRiskServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        projectRiskServiceStub.find.resolves(projectRiskSample);
        projectRiskServiceStub.retrieve.resolves([projectRiskSample]);

        // WHEN
        route = {
          params: {
            projectRiskId: '' + projectRiskSample.id,
          },
        };
        const wrapper = shallowMount(ProjectRiskUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.projectRisk).toMatchObject(projectRiskSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        projectRiskServiceStub.find.resolves(projectRiskSample);
        const wrapper = shallowMount(ProjectRiskUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
