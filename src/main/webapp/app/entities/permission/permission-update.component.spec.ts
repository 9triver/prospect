/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import PermissionUpdate from './permission-update.vue';
import PermissionService from './permission.service';
import AlertService from '@/shared/alert/alert.service';

import RoleService from '@/entities/role/role.service';

type PermissionUpdateComponentType = InstanceType<typeof PermissionUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const permissionSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<PermissionUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Permission Management Update Component', () => {
    let comp: PermissionUpdateComponentType;
    let permissionServiceStub: SinonStubbedInstance<PermissionService>;

    beforeEach(() => {
      route = {};
      permissionServiceStub = sinon.createStubInstance<PermissionService>(PermissionService);
      permissionServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          permissionService: () => permissionServiceStub,
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
        const wrapper = shallowMount(PermissionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.permission = permissionSample;
        permissionServiceStub.update.resolves(permissionSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(permissionServiceStub.update.calledWith(permissionSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        permissionServiceStub.create.resolves(entity);
        const wrapper = shallowMount(PermissionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.permission = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(permissionServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        permissionServiceStub.find.resolves(permissionSample);
        permissionServiceStub.retrieve.resolves([permissionSample]);

        // WHEN
        route = {
          params: {
            permissionId: '' + permissionSample.id,
          },
        };
        const wrapper = shallowMount(PermissionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.permission).toMatchObject(permissionSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        permissionServiceStub.find.resolves(permissionSample);
        const wrapper = shallowMount(PermissionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
