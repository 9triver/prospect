/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import OutsourcingPurchasePlanUpdate from './outsourcing-purchase-plan-update.vue';
import OutsourcingPurchasePlanService from './outsourcing-purchase-plan.service';
import AlertService from '@/shared/alert/alert.service';

import ProjectService from '@/entities/project/project.service';
import OfficersService from '@/entities/officers/officers.service';

type OutsourcingPurchasePlanUpdateComponentType = InstanceType<typeof OutsourcingPurchasePlanUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const outsourcingPurchasePlanSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<OutsourcingPurchasePlanUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('OutsourcingPurchasePlan Management Update Component', () => {
    let comp: OutsourcingPurchasePlanUpdateComponentType;
    let outsourcingPurchasePlanServiceStub: SinonStubbedInstance<OutsourcingPurchasePlanService>;

    beforeEach(() => {
      route = {};
      outsourcingPurchasePlanServiceStub = sinon.createStubInstance<OutsourcingPurchasePlanService>(OutsourcingPurchasePlanService);
      outsourcingPurchasePlanServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          outsourcingPurchasePlanService: () => outsourcingPurchasePlanServiceStub,
          projectService: () =>
            sinon.createStubInstance<ProjectService>(ProjectService, {
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
        const wrapper = shallowMount(OutsourcingPurchasePlanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.outsourcingPurchasePlan = outsourcingPurchasePlanSample;
        outsourcingPurchasePlanServiceStub.update.resolves(outsourcingPurchasePlanSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(outsourcingPurchasePlanServiceStub.update.calledWith(outsourcingPurchasePlanSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        outsourcingPurchasePlanServiceStub.create.resolves(entity);
        const wrapper = shallowMount(OutsourcingPurchasePlanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.outsourcingPurchasePlan = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(outsourcingPurchasePlanServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        outsourcingPurchasePlanServiceStub.find.resolves(outsourcingPurchasePlanSample);
        outsourcingPurchasePlanServiceStub.retrieve.resolves([outsourcingPurchasePlanSample]);

        // WHEN
        route = {
          params: {
            outsourcingPurchasePlanId: '' + outsourcingPurchasePlanSample.id,
          },
        };
        const wrapper = shallowMount(OutsourcingPurchasePlanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.outsourcingPurchasePlan).toMatchObject(outsourcingPurchasePlanSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        outsourcingPurchasePlanServiceStub.find.resolves(outsourcingPurchasePlanSample);
        const wrapper = shallowMount(OutsourcingPurchasePlanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
