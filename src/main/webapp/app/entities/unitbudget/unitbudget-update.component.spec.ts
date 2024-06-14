/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import UnitbudgetUpdate from './unitbudget-update.vue';
import UnitbudgetService from './unitbudget.service';
import AlertService from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';

type UnitbudgetUpdateComponentType = InstanceType<typeof UnitbudgetUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const unitbudgetSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<UnitbudgetUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Unitbudget Management Update Component', () => {
    let comp: UnitbudgetUpdateComponentType;
    let unitbudgetServiceStub: SinonStubbedInstance<UnitbudgetService>;

    beforeEach(() => {
      route = {};
      unitbudgetServiceStub = sinon.createStubInstance<UnitbudgetService>(UnitbudgetService);
      unitbudgetServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          unitbudgetService: () => unitbudgetServiceStub,
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
        const wrapper = shallowMount(UnitbudgetUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.unitbudget = unitbudgetSample;
        unitbudgetServiceStub.update.resolves(unitbudgetSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(unitbudgetServiceStub.update.calledWith(unitbudgetSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        unitbudgetServiceStub.create.resolves(entity);
        const wrapper = shallowMount(UnitbudgetUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.unitbudget = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(unitbudgetServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        unitbudgetServiceStub.find.resolves(unitbudgetSample);
        unitbudgetServiceStub.retrieve.resolves([unitbudgetSample]);

        // WHEN
        route = {
          params: {
            unitbudgetId: '' + unitbudgetSample.id,
          },
        };
        const wrapper = shallowMount(UnitbudgetUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.unitbudget).toMatchObject(unitbudgetSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        unitbudgetServiceStub.find.resolves(unitbudgetSample);
        const wrapper = shallowMount(UnitbudgetUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
