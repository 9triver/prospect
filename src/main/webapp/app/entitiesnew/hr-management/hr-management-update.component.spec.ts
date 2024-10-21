/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import HrManagementUpdate from './hr-management-update.vue';
import HrManagementService from './hr-management.service';
import AlertService from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';

type HrManagementUpdateComponentType = InstanceType<typeof HrManagementUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const hrManagementSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<HrManagementUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('HrManagement Management Update Component', () => {
    let comp: HrManagementUpdateComponentType;
    let hrManagementServiceStub: SinonStubbedInstance<HrManagementService>;

    beforeEach(() => {
      route = {};
      hrManagementServiceStub = sinon.createStubInstance<HrManagementService>(HrManagementService);
      hrManagementServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          hrManagementService: () => hrManagementServiceStub,
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
        const wrapper = shallowMount(HrManagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.hrManagement = hrManagementSample;
        hrManagementServiceStub.update.resolves(hrManagementSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(hrManagementServiceStub.update.calledWith(hrManagementSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        hrManagementServiceStub.create.resolves(entity);
        const wrapper = shallowMount(HrManagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.hrManagement = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(hrManagementServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        hrManagementServiceStub.find.resolves(hrManagementSample);
        hrManagementServiceStub.retrieve.resolves([hrManagementSample]);

        // WHEN
        route = {
          params: {
            hrManagementId: '' + hrManagementSample.id,
          },
        };
        const wrapper = shallowMount(HrManagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.hrManagement).toMatchObject(hrManagementSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        hrManagementServiceStub.find.resolves(hrManagementSample);
        const wrapper = shallowMount(HrManagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
