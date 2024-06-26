/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProjectSecrecyUpdate from './project-secrecy-update.vue';
import ProjectSecrecyService from './project-secrecy.service';
import AlertService from '@/shared/alert/alert.service';

import SecrecysystemService from '@/entities/secrecysystem/secrecysystem.service';
import OfficersService from '@/entities/officers/officers.service';
import ProjectService from '@/entities/project/project.service';

type ProjectSecrecyUpdateComponentType = InstanceType<typeof ProjectSecrecyUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const projectSecrecySample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ProjectSecrecyUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ProjectSecrecy Management Update Component', () => {
    let comp: ProjectSecrecyUpdateComponentType;
    let projectSecrecyServiceStub: SinonStubbedInstance<ProjectSecrecyService>;

    beforeEach(() => {
      route = {};
      projectSecrecyServiceStub = sinon.createStubInstance<ProjectSecrecyService>(ProjectSecrecyService);
      projectSecrecyServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          projectSecrecyService: () => projectSecrecyServiceStub,
          secrecysystemService: () =>
            sinon.createStubInstance<SecrecysystemService>(SecrecysystemService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          officersService: () =>
            sinon.createStubInstance<OfficersService>(OfficersService, {
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
        const wrapper = shallowMount(ProjectSecrecyUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.projectSecrecy = projectSecrecySample;
        projectSecrecyServiceStub.update.resolves(projectSecrecySample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(projectSecrecyServiceStub.update.calledWith(projectSecrecySample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        projectSecrecyServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ProjectSecrecyUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.projectSecrecy = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(projectSecrecyServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        projectSecrecyServiceStub.find.resolves(projectSecrecySample);
        projectSecrecyServiceStub.retrieve.resolves([projectSecrecySample]);

        // WHEN
        route = {
          params: {
            projectSecrecyId: '' + projectSecrecySample.id,
          },
        };
        const wrapper = shallowMount(ProjectSecrecyUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.projectSecrecy).toMatchObject(projectSecrecySample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        projectSecrecyServiceStub.find.resolves(projectSecrecySample);
        const wrapper = shallowMount(ProjectSecrecyUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
