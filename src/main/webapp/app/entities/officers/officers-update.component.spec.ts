/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import OfficersUpdate from './officers-update.vue';
import OfficersService from './officers.service';
import AlertService from '@/shared/alert/alert.service';

import DepartmentService from '@/entities/department/department.service';
import RoleService from '@/entities/role/role.service';

type OfficersUpdateComponentType = InstanceType<typeof OfficersUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const officersSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<OfficersUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Officers Management Update Component', () => {
    let comp: OfficersUpdateComponentType;
    let officersServiceStub: SinonStubbedInstance<OfficersService>;

    beforeEach(() => {
      route = {};
      officersServiceStub = sinon.createStubInstance<OfficersService>(OfficersService);
      officersServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          officersService: () => officersServiceStub,
          departmentService: () =>
            sinon.createStubInstance<DepartmentService>(DepartmentService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          roleService: () =>
            sinon.createStubInstance<RoleService>(RoleService, {
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
        const wrapper = shallowMount(OfficersUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.officers = officersSample;
        officersServiceStub.update.resolves(officersSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(officersServiceStub.update.calledWith(officersSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        officersServiceStub.create.resolves(entity);
        const wrapper = shallowMount(OfficersUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.officers = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(officersServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        officersServiceStub.find.resolves(officersSample);
        officersServiceStub.retrieve.resolves([officersSample]);

        // WHEN
        route = {
          params: {
            officersId: '' + officersSample.id,
          },
        };
        const wrapper = shallowMount(OfficersUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.officers).toMatchObject(officersSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        officersServiceStub.find.resolves(officersSample);
        const wrapper = shallowMount(OfficersUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
