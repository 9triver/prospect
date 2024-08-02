/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RoleUpdate from './role-update.vue';
import RoleService from './role.service';
import AlertService from '@/shared/alert/alert.service';

import PermissionService from '@/entities/permission/permission.service';
import OfficersService from '@/entities/officers/officers.service';

type RoleUpdateComponentType = InstanceType<typeof RoleUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const roleSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<RoleUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Role Management Update Component', () => {
    let comp: RoleUpdateComponentType;
    let roleServiceStub: SinonStubbedInstance<RoleService>;

    beforeEach(() => {
      route = {};
      roleServiceStub = sinon.createStubInstance<RoleService>(RoleService);
      roleServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          roleService: () => roleServiceStub,
          permissionService: () =>
            sinon.createStubInstance<PermissionService>(PermissionService, {
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
        const wrapper = shallowMount(RoleUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.role = roleSample;
        roleServiceStub.update.resolves(roleSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(roleServiceStub.update.calledWith(roleSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        roleServiceStub.create.resolves(entity);
        const wrapper = shallowMount(RoleUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.role = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(roleServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        roleServiceStub.find.resolves(roleSample);
        roleServiceStub.retrieve.resolves([roleSample]);

        // WHEN
        route = {
          params: {
            roleId: '' + roleSample.id,
          },
        };
        const wrapper = shallowMount(RoleUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.role).toMatchObject(roleSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        roleServiceStub.find.resolves(roleSample);
        const wrapper = shallowMount(RoleUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
