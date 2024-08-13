/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProjectTotalwbsUpdate from './projecttotalwbs-update.vue';
import ProjectTotalwbsService from './projecttotalwbs.service';
import AlertService from '@/shared/alert/alert.service';

import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import OfficersService from '@/entities/officers/officers.service';
import DepartmentService from '@/entities/department/department.service';

type ProjectTotalwbsUpdateComponentType = InstanceType<typeof ProjectTotalwbsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const projectTotalwbsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ProjectTotalwbsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ProjectTotalwbs Management Update Component', () => {
    let comp: ProjectTotalwbsUpdateComponentType;
    let projectTotalwbsServiceStub: SinonStubbedInstance<ProjectTotalwbsService>;

    beforeEach(() => {
      route = {};
      projectTotalwbsServiceStub = sinon.createStubInstance<ProjectTotalwbsService>(ProjectTotalwbsService);
      projectTotalwbsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          projectTotalwbsService: () => projectTotalwbsServiceStub,
          projectwbsService: () =>
            sinon.createStubInstance<ProjectwbsService>(ProjectwbsService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          officersService: () =>
            sinon.createStubInstance<OfficersService>(OfficersService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
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
        const wrapper = shallowMount(ProjectTotalwbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.projectTotalwbs = projectTotalwbsSample;
        projectTotalwbsServiceStub.update.resolves(projectTotalwbsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(projectTotalwbsServiceStub.update.calledWith(projectTotalwbsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        projectTotalwbsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ProjectTotalwbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.projectTotalwbs = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(projectTotalwbsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        projectTotalwbsServiceStub.find.resolves(projectTotalwbsSample);
        projectTotalwbsServiceStub.retrieve.resolves([projectTotalwbsSample]);

        // WHEN
        route = {
          params: {
            projectTotalwbsId: '' + projectTotalwbsSample.id,
          },
        };
        const wrapper = shallowMount(ProjectTotalwbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.projectTotalwbs).toMatchObject(projectTotalwbsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        projectTotalwbsServiceStub.find.resolves(projectTotalwbsSample);
        const wrapper = shallowMount(ProjectTotalwbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
