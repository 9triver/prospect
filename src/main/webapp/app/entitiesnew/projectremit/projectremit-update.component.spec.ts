/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProjectremitUpdate from './projectremit-update.vue';
import ProjectremitService from './projectremit.service';
import AlertService from '@/shared/alert/alert.service';

type ProjectremitUpdateComponentType = InstanceType<typeof ProjectremitUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const projectremitSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ProjectremitUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Projectremit Management Update Component', () => {
    let comp: ProjectremitUpdateComponentType;
    let projectremitServiceStub: SinonStubbedInstance<ProjectremitService>;

    beforeEach(() => {
      route = {};
      projectremitServiceStub = sinon.createStubInstance<ProjectremitService>(ProjectremitService);
      projectremitServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          projectremitService: () => projectremitServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(ProjectremitUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.projectremit = projectremitSample;
        projectremitServiceStub.update.resolves(projectremitSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(projectremitServiceStub.update.calledWith(projectremitSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        projectremitServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ProjectremitUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.projectremit = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(projectremitServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        projectremitServiceStub.find.resolves(projectremitSample);
        projectremitServiceStub.retrieve.resolves([projectremitSample]);

        // WHEN
        route = {
          params: {
            projectremitId: '' + projectremitSample.id,
          },
        };
        const wrapper = shallowMount(ProjectremitUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.projectremit).toMatchObject(projectremitSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        projectremitServiceStub.find.resolves(projectremitSample);
        const wrapper = shallowMount(ProjectremitUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
