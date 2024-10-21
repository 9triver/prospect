/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProjectpbsUpdate from './projectpbs-update.vue';
import ProjectpbsService from './projectpbs.service';
import AlertService from '@/shared/alert/alert.service';

import HrManagementService from '@/entities/hr-management/hr-management.service';
import DepartmentService from '@/entities/department/department.service';
import ProjectService from '@/entities/project/project.service';

type ProjectpbsUpdateComponentType = InstanceType<typeof ProjectpbsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const projectpbsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ProjectpbsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Projectpbs Management Update Component', () => {
    let comp: ProjectpbsUpdateComponentType;
    let projectpbsServiceStub: SinonStubbedInstance<ProjectpbsService>;

    beforeEach(() => {
      route = {};
      projectpbsServiceStub = sinon.createStubInstance<ProjectpbsService>(ProjectpbsService);
      projectpbsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          projectpbsService: () => projectpbsServiceStub,
          hrManagementService: () =>
            sinon.createStubInstance<HrManagementService>(HrManagementService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          departmentService: () =>
            sinon.createStubInstance<DepartmentService>(DepartmentService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          projectService: () =>
            sinon.createStubInstance<ProjectService>(ProjectService, {
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
        const wrapper = shallowMount(ProjectpbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.projectpbs = projectpbsSample;
        projectpbsServiceStub.update.resolves(projectpbsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(projectpbsServiceStub.update.calledWith(projectpbsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        projectpbsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ProjectpbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.projectpbs = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(projectpbsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        projectpbsServiceStub.find.resolves(projectpbsSample);
        projectpbsServiceStub.retrieve.resolves([projectpbsSample]);

        // WHEN
        route = {
          params: {
            projectpbsId: '' + projectpbsSample.id,
          },
        };
        const wrapper = shallowMount(ProjectpbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.projectpbs).toMatchObject(projectpbsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        projectpbsServiceStub.find.resolves(projectpbsSample);
        const wrapper = shallowMount(ProjectpbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
