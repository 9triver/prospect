/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import PlanstrategyUpdate from './planstrategy-update.vue';
import PlanstrategyService from './planstrategy.service';
import AlertService from '@/shared/alert/alert.service';

import DepartmentService from '@/entities/department/department.service';
import OfficersService from '@/entities/officers/officers.service';

type PlanstrategyUpdateComponentType = InstanceType<typeof PlanstrategyUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const planstrategySample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<PlanstrategyUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Planstrategy Management Update Component', () => {
    let comp: PlanstrategyUpdateComponentType;
    let planstrategyServiceStub: SinonStubbedInstance<PlanstrategyService>;

    beforeEach(() => {
      route = {};
      planstrategyServiceStub = sinon.createStubInstance<PlanstrategyService>(PlanstrategyService);
      planstrategyServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          planstrategyService: () => planstrategyServiceStub,
          departmentService: () =>
            sinon.createStubInstance<DepartmentService>(DepartmentService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          officersService: () =>
            sinon.createStubInstance<OfficersService>(OfficersService, {
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
        const wrapper = shallowMount(PlanstrategyUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.planstrategy = planstrategySample;
        planstrategyServiceStub.update.resolves(planstrategySample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(planstrategyServiceStub.update.calledWith(planstrategySample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        planstrategyServiceStub.create.resolves(entity);
        const wrapper = shallowMount(PlanstrategyUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.planstrategy = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(planstrategyServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        planstrategyServiceStub.find.resolves(planstrategySample);
        planstrategyServiceStub.retrieve.resolves([planstrategySample]);

        // WHEN
        route = {
          params: {
            planstrategyId: '' + planstrategySample.id,
          },
        };
        const wrapper = shallowMount(PlanstrategyUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.planstrategy).toMatchObject(planstrategySample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        planstrategyServiceStub.find.resolves(planstrategySample);
        const wrapper = shallowMount(PlanstrategyUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
