/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import OutsourcingPurchaseExecuteUpdate from './outsourcing-purchase-execute-update.vue';
import OutsourcingPurchaseExecuteService from './outsourcing-purchase-execute.service';
import AlertService from '@/shared/alert/alert.service';

import OutsourcingPurchasePlanService from '@/entities/outsourcing-purchase-plan/outsourcing-purchase-plan.service';
import OfficersService from '@/entities/officers/officers.service';

type OutsourcingPurchaseExecuteUpdateComponentType = InstanceType<typeof OutsourcingPurchaseExecuteUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const outsourcingPurchaseExecuteSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<OutsourcingPurchaseExecuteUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('OutsourcingPurchaseExecute Management Update Component', () => {
    let comp: OutsourcingPurchaseExecuteUpdateComponentType;
    let outsourcingPurchaseExecuteServiceStub: SinonStubbedInstance<OutsourcingPurchaseExecuteService>;

    beforeEach(() => {
      route = {};
      outsourcingPurchaseExecuteServiceStub =
        sinon.createStubInstance<OutsourcingPurchaseExecuteService>(OutsourcingPurchaseExecuteService);
      outsourcingPurchaseExecuteServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          outsourcingPurchaseExecuteService: () => outsourcingPurchaseExecuteServiceStub,
          outsourcingPurchasePlanService: () =>
            sinon.createStubInstance<OutsourcingPurchasePlanService>(OutsourcingPurchasePlanService, {
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
        const wrapper = shallowMount(OutsourcingPurchaseExecuteUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.outsourcingPurchaseExecute = outsourcingPurchaseExecuteSample;
        outsourcingPurchaseExecuteServiceStub.update.resolves(outsourcingPurchaseExecuteSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(outsourcingPurchaseExecuteServiceStub.update.calledWith(outsourcingPurchaseExecuteSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        outsourcingPurchaseExecuteServiceStub.create.resolves(entity);
        const wrapper = shallowMount(OutsourcingPurchaseExecuteUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.outsourcingPurchaseExecute = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(outsourcingPurchaseExecuteServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        outsourcingPurchaseExecuteServiceStub.find.resolves(outsourcingPurchaseExecuteSample);
        outsourcingPurchaseExecuteServiceStub.retrieve.resolves([outsourcingPurchaseExecuteSample]);

        // WHEN
        route = {
          params: {
            outsourcingPurchaseExecuteId: '' + outsourcingPurchaseExecuteSample.id,
          },
        };
        const wrapper = shallowMount(OutsourcingPurchaseExecuteUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.outsourcingPurchaseExecute).toMatchObject(outsourcingPurchaseExecuteSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        outsourcingPurchaseExecuteServiceStub.find.resolves(outsourcingPurchaseExecuteSample);
        const wrapper = shallowMount(OutsourcingPurchaseExecuteUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
