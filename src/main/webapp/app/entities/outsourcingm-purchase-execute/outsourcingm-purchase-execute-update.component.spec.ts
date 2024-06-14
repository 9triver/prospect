/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import OutsourcingmPurchaseExecuteUpdate from './outsourcingm-purchase-execute-update.vue';
import OutsourcingmPurchaseExecuteService from './outsourcingm-purchase-execute.service';
import AlertService from '@/shared/alert/alert.service';

import OutsourcingmPurchasePlanService from '@/entities/outsourcingm-purchase-plan/outsourcingm-purchase-plan.service';
import OfficersService from '@/entities/officers/officers.service';

type OutsourcingmPurchaseExecuteUpdateComponentType = InstanceType<typeof OutsourcingmPurchaseExecuteUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const outsourcingmPurchaseExecuteSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<OutsourcingmPurchaseExecuteUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('OutsourcingmPurchaseExecute Management Update Component', () => {
    let comp: OutsourcingmPurchaseExecuteUpdateComponentType;
    let outsourcingmPurchaseExecuteServiceStub: SinonStubbedInstance<OutsourcingmPurchaseExecuteService>;

    beforeEach(() => {
      route = {};
      outsourcingmPurchaseExecuteServiceStub =
        sinon.createStubInstance<OutsourcingmPurchaseExecuteService>(OutsourcingmPurchaseExecuteService);
      outsourcingmPurchaseExecuteServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          outsourcingmPurchaseExecuteService: () => outsourcingmPurchaseExecuteServiceStub,
          outsourcingmPurchasePlanService: () =>
            sinon.createStubInstance<OutsourcingmPurchasePlanService>(OutsourcingmPurchasePlanService, {
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
        const wrapper = shallowMount(OutsourcingmPurchaseExecuteUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.outsourcingmPurchaseExecute = outsourcingmPurchaseExecuteSample;
        outsourcingmPurchaseExecuteServiceStub.update.resolves(outsourcingmPurchaseExecuteSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(outsourcingmPurchaseExecuteServiceStub.update.calledWith(outsourcingmPurchaseExecuteSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        outsourcingmPurchaseExecuteServiceStub.create.resolves(entity);
        const wrapper = shallowMount(OutsourcingmPurchaseExecuteUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.outsourcingmPurchaseExecute = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(outsourcingmPurchaseExecuteServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        outsourcingmPurchaseExecuteServiceStub.find.resolves(outsourcingmPurchaseExecuteSample);
        outsourcingmPurchaseExecuteServiceStub.retrieve.resolves([outsourcingmPurchaseExecuteSample]);

        // WHEN
        route = {
          params: {
            outsourcingmPurchaseExecuteId: '' + outsourcingmPurchaseExecuteSample.id,
          },
        };
        const wrapper = shallowMount(OutsourcingmPurchaseExecuteUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.outsourcingmPurchaseExecute).toMatchObject(outsourcingmPurchaseExecuteSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        outsourcingmPurchaseExecuteServiceStub.find.resolves(outsourcingmPurchaseExecuteSample);
        const wrapper = shallowMount(OutsourcingmPurchaseExecuteUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
