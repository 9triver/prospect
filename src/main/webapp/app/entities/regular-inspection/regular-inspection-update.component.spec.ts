/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RegularInspectionUpdate from './regular-inspection-update.vue';
import RegularInspectionService from './regular-inspection.service';
import AlertService from '@/shared/alert/alert.service';

import HrManagementService from '@/entities/hr-management/hr-management.service';
import WorkbagService from '@/entities/workbag/workbag.service';

type RegularInspectionUpdateComponentType = InstanceType<typeof RegularInspectionUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const regularInspectionSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<RegularInspectionUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('RegularInspection Management Update Component', () => {
    let comp: RegularInspectionUpdateComponentType;
    let regularInspectionServiceStub: SinonStubbedInstance<RegularInspectionService>;

    beforeEach(() => {
      route = {};
      regularInspectionServiceStub = sinon.createStubInstance<RegularInspectionService>(RegularInspectionService);
      regularInspectionServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          regularInspectionService: () => regularInspectionServiceStub,
          hrManagementService: () =>
            sinon.createStubInstance<HrManagementService>(HrManagementService, {
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
        const wrapper = shallowMount(RegularInspectionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.regularInspection = regularInspectionSample;
        regularInspectionServiceStub.update.resolves(regularInspectionSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(regularInspectionServiceStub.update.calledWith(regularInspectionSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        regularInspectionServiceStub.create.resolves(entity);
        const wrapper = shallowMount(RegularInspectionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.regularInspection = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(regularInspectionServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        regularInspectionServiceStub.find.resolves(regularInspectionSample);
        regularInspectionServiceStub.retrieve.resolves([regularInspectionSample]);

        // WHEN
        route = {
          params: {
            regularInspectionId: '' + regularInspectionSample.id,
          },
        };
        const wrapper = shallowMount(RegularInspectionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.regularInspection).toMatchObject(regularInspectionSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        regularInspectionServiceStub.find.resolves(regularInspectionSample);
        const wrapper = shallowMount(RegularInspectionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
