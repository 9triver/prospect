/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProjectwbsUpdate from './projectwbs-update.vue';
import ProjectwbsService from './projectwbs.service';
import AlertService from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import DepartmentService from '@/entities/department/department.service';
import ProjectService from '@/entities/project/project.service';

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
          officersService: () =>
            sinon.createStubInstance<OfficersService>(OfficersService, {
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
