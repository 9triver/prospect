/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ComprehensivecontrolUpdate from './comprehensivecontrol-update.vue';
import ComprehensivecontrolService from './comprehensivecontrol.service';
import AlertService from '@/shared/alert/alert.service';

import ProgressmanagementService from '@/entities/progressmanagement/progressmanagement.service';
import ProjectService from '@/entities/project/project.service';
import FundsmanagementService from '@/entities/fundsmanagement/fundsmanagement.service';
import TotalbudgetService from '@/entities/totalbudget/totalbudget.service';
import UnitbudgetService from '@/entities/unitbudget/unitbudget.service';
import OfficersService from '@/entities/officers/officers.service';
import DepartmentService from '@/entities/department/department.service';

type ComprehensivecontrolUpdateComponentType = InstanceType<typeof ComprehensivecontrolUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const comprehensivecontrolSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ComprehensivecontrolUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Comprehensivecontrol Management Update Component', () => {
    let comp: ComprehensivecontrolUpdateComponentType;
    let comprehensivecontrolServiceStub: SinonStubbedInstance<ComprehensivecontrolService>;

    beforeEach(() => {
      route = {};
      comprehensivecontrolServiceStub = sinon.createStubInstance<ComprehensivecontrolService>(ComprehensivecontrolService);
      comprehensivecontrolServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          comprehensivecontrolService: () => comprehensivecontrolServiceStub,
          progressmanagementService: () =>
            sinon.createStubInstance<ProgressmanagementService>(ProgressmanagementService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          projectService: () =>
            sinon.createStubInstance<ProjectService>(ProjectService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          fundsmanagementService: () =>
            sinon.createStubInstance<FundsmanagementService>(FundsmanagementService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          totalbudgetService: () =>
            sinon.createStubInstance<TotalbudgetService>(TotalbudgetService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          unitbudgetService: () =>
            sinon.createStubInstance<UnitbudgetService>(UnitbudgetService, {
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
        const wrapper = shallowMount(ComprehensivecontrolUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.comprehensivecontrol = comprehensivecontrolSample;
        comprehensivecontrolServiceStub.update.resolves(comprehensivecontrolSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(comprehensivecontrolServiceStub.update.calledWith(comprehensivecontrolSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comprehensivecontrolServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ComprehensivecontrolUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.comprehensivecontrol = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(comprehensivecontrolServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        comprehensivecontrolServiceStub.find.resolves(comprehensivecontrolSample);
        comprehensivecontrolServiceStub.retrieve.resolves([comprehensivecontrolSample]);

        // WHEN
        route = {
          params: {
            comprehensivecontrolId: '' + comprehensivecontrolSample.id,
          },
        };
        const wrapper = shallowMount(ComprehensivecontrolUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.comprehensivecontrol).toMatchObject(comprehensivecontrolSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comprehensivecontrolServiceStub.find.resolves(comprehensivecontrolSample);
        const wrapper = shallowMount(ComprehensivecontrolUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
