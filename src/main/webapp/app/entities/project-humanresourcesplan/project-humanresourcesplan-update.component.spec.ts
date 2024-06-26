/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProjectHumanresourcesplanUpdate from './project-humanresourcesplan-update.vue';
import ProjectHumanresourcesplanService from './project-humanresourcesplan.service';
import AlertService from '@/shared/alert/alert.service';

import ProjectService from '@/entities/project/project.service';

type ProjectHumanresourcesplanUpdateComponentType = InstanceType<typeof ProjectHumanresourcesplanUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const projectHumanresourcesplanSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ProjectHumanresourcesplanUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ProjectHumanresourcesplan Management Update Component', () => {
    let comp: ProjectHumanresourcesplanUpdateComponentType;
    let projectHumanresourcesplanServiceStub: SinonStubbedInstance<ProjectHumanresourcesplanService>;

    beforeEach(() => {
      route = {};
      projectHumanresourcesplanServiceStub = sinon.createStubInstance<ProjectHumanresourcesplanService>(ProjectHumanresourcesplanService);
      projectHumanresourcesplanServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          projectHumanresourcesplanService: () => projectHumanresourcesplanServiceStub,
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
        const wrapper = shallowMount(ProjectHumanresourcesplanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.projectHumanresourcesplan = projectHumanresourcesplanSample;
        projectHumanresourcesplanServiceStub.update.resolves(projectHumanresourcesplanSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(projectHumanresourcesplanServiceStub.update.calledWith(projectHumanresourcesplanSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        projectHumanresourcesplanServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ProjectHumanresourcesplanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.projectHumanresourcesplan = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(projectHumanresourcesplanServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        projectHumanresourcesplanServiceStub.find.resolves(projectHumanresourcesplanSample);
        projectHumanresourcesplanServiceStub.retrieve.resolves([projectHumanresourcesplanSample]);

        // WHEN
        route = {
          params: {
            projectHumanresourcesplanId: '' + projectHumanresourcesplanSample.id,
          },
        };
        const wrapper = shallowMount(ProjectHumanresourcesplanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.projectHumanresourcesplan).toMatchObject(projectHumanresourcesplanSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        projectHumanresourcesplanServiceStub.find.resolves(projectHumanresourcesplanSample);
        const wrapper = shallowMount(ProjectHumanresourcesplanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
