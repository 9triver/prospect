/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import OutsourcingmPurchasePlanUpdate from './outsourcingm-purchase-plan-update.vue';
import OutsourcingmPurchasePlanService from './outsourcingm-purchase-plan.service';
import AlertService from '@/shared/alert/alert.service';

import ProjectService from '@/entities/project/project.service';
import OfficersService from '@/entities/officers/officers.service';

type OutsourcingmPurchasePlanUpdateComponentType = InstanceType<typeof OutsourcingmPurchasePlanUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const outsourcingmPurchasePlanSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<OutsourcingmPurchasePlanUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('OutsourcingmPurchasePlan Management Update Component', () => {
    let comp: OutsourcingmPurchasePlanUpdateComponentType;
    let outsourcingmPurchasePlanServiceStub: SinonStubbedInstance<OutsourcingmPurchasePlanService>;

    beforeEach(() => {
      route = {};
      outsourcingmPurchasePlanServiceStub = sinon.createStubInstance<OutsourcingmPurchasePlanService>(OutsourcingmPurchasePlanService);
      outsourcingmPurchasePlanServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          outsourcingmPurchasePlanService: () => outsourcingmPurchasePlanServiceStub,
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
        const wrapper = shallowMount(OutsourcingmPurchasePlanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.outsourcingmPurchasePlan = outsourcingmPurchasePlanSample;
        outsourcingmPurchasePlanServiceStub.update.resolves(outsourcingmPurchasePlanSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(outsourcingmPurchasePlanServiceStub.update.calledWith(outsourcingmPurchasePlanSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        outsourcingmPurchasePlanServiceStub.create.resolves(entity);
        const wrapper = shallowMount(OutsourcingmPurchasePlanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.outsourcingmPurchasePlan = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(outsourcingmPurchasePlanServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        outsourcingmPurchasePlanServiceStub.find.resolves(outsourcingmPurchasePlanSample);
        outsourcingmPurchasePlanServiceStub.retrieve.resolves([outsourcingmPurchasePlanSample]);

        // WHEN
        route = {
          params: {
            outsourcingmPurchasePlanId: '' + outsourcingmPurchasePlanSample.id,
          },
        };
        const wrapper = shallowMount(OutsourcingmPurchasePlanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.outsourcingmPurchasePlan).toMatchObject(outsourcingmPurchasePlanSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        outsourcingmPurchasePlanServiceStub.find.resolves(outsourcingmPurchasePlanSample);
        const wrapper = shallowMount(OutsourcingmPurchasePlanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
