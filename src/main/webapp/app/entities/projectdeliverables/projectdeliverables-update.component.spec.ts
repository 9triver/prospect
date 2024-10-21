/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProjectdeliverablesUpdate from './projectdeliverables-update.vue';
import ProjectdeliverablesService from './projectdeliverables.service';
import AlertService from '@/shared/alert/alert.service';

import DeliverablesService from '@/entities/deliverables/deliverables.service';
import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import WorkbagService from '@/entities/workbag/workbag.service';

type ProjectdeliverablesUpdateComponentType = InstanceType<typeof ProjectdeliverablesUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const projectdeliverablesSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ProjectdeliverablesUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Projectdeliverables Management Update Component', () => {
    let comp: ProjectdeliverablesUpdateComponentType;
    let projectdeliverablesServiceStub: SinonStubbedInstance<ProjectdeliverablesService>;

    beforeEach(() => {
      route = {};
      projectdeliverablesServiceStub = sinon.createStubInstance<ProjectdeliverablesService>(ProjectdeliverablesService);
      projectdeliverablesServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          projectdeliverablesService: () => projectdeliverablesServiceStub,
          deliverablesService: () =>
            sinon.createStubInstance<DeliverablesService>(DeliverablesService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          projectwbsService: () =>
            sinon.createStubInstance<ProjectwbsService>(ProjectwbsService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          workbagService: () =>
            sinon.createStubInstance<WorkbagService>(WorkbagService, {
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
        const wrapper = shallowMount(ProjectdeliverablesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.projectdeliverables = projectdeliverablesSample;
        projectdeliverablesServiceStub.update.resolves(projectdeliverablesSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(projectdeliverablesServiceStub.update.calledWith(projectdeliverablesSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        projectdeliverablesServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ProjectdeliverablesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.projectdeliverables = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(projectdeliverablesServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        projectdeliverablesServiceStub.find.resolves(projectdeliverablesSample);
        projectdeliverablesServiceStub.retrieve.resolves([projectdeliverablesSample]);

        // WHEN
        route = {
          params: {
            projectdeliverablesId: '' + projectdeliverablesSample.id,
          },
        };
        const wrapper = shallowMount(ProjectdeliverablesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.projectdeliverables).toMatchObject(projectdeliverablesSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        projectdeliverablesServiceStub.find.resolves(projectdeliverablesSample);
        const wrapper = shallowMount(ProjectdeliverablesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
