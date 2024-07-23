/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProjectUpdate from './project-update.vue';
import ProjectService from './project.service';
import AlertService from '@/shared/alert/alert.service';

import ProjectpbsService from '@/entities/projectpbs/projectpbs.service';
import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';

type ProjectUpdateComponentType = InstanceType<typeof ProjectUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const projectSample = { id: 'ABC' };

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
          projectpbsService: () =>
            sinon.createStubInstance<ProjectpbsService>(ProjectpbsService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          projectwbsService: () =>
            sinon.createStubInstance<ProjectwbsService>(ProjectwbsService, {
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
