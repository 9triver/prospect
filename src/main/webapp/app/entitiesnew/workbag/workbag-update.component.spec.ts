/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import WorkbagUpdate from './workbag-update.vue';
import WorkbagService from './workbag.service';
import AlertService from '@/shared/alert/alert.service';

import HrManagementService from '@/entities/hr-management/hr-management.service';
import DepartmentService from '@/entities/department/department.service';
import ProjectdeliverablesService from '@/entities/projectdeliverables/projectdeliverables.service';
import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import WorkService from '@/entities/work/work.service';

type WorkbagUpdateComponentType = InstanceType<typeof WorkbagUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const workbagSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<WorkbagUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Workbag Management Update Component', () => {
    let comp: WorkbagUpdateComponentType;
    let workbagServiceStub: SinonStubbedInstance<WorkbagService>;

    beforeEach(() => {
      route = {};
      workbagServiceStub = sinon.createStubInstance<WorkbagService>(WorkbagService);
      workbagServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          workbagService: () => workbagServiceStub,
          hrManagementService: () =>
            sinon.createStubInstance<HrManagementService>(HrManagementService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          departmentService: () =>
            sinon.createStubInstance<DepartmentService>(DepartmentService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          projectdeliverablesService: () =>
            sinon.createStubInstance<ProjectdeliverablesService>(ProjectdeliverablesService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          projectwbsService: () =>
            sinon.createStubInstance<ProjectwbsService>(ProjectwbsService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          workService: () =>
            sinon.createStubInstance<WorkService>(WorkService, {
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
        const wrapper = shallowMount(WorkbagUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.workbag = workbagSample;
        workbagServiceStub.update.resolves(workbagSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(workbagServiceStub.update.calledWith(workbagSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        workbagServiceStub.create.resolves(entity);
        const wrapper = shallowMount(WorkbagUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.workbag = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(workbagServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        workbagServiceStub.find.resolves(workbagSample);
        workbagServiceStub.retrieve.resolves([workbagSample]);

        // WHEN
        route = {
          params: {
            workbagId: '' + workbagSample.id,
          },
        };
        const wrapper = shallowMount(WorkbagUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.workbag).toMatchObject(workbagSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        workbagServiceStub.find.resolves(workbagSample);
        const wrapper = shallowMount(WorkbagUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
