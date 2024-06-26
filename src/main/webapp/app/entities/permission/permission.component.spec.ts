/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Permission from './permission.vue';
import PermissionService from './permission.service';
import AlertService from '@/shared/alert/alert.service';

type PermissionComponentType = InstanceType<typeof Permission>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Permission Management Component', () => {
    let permissionServiceStub: SinonStubbedInstance<PermissionService>;
    let mountOptions: MountingOptions<PermissionComponentType>['global'];

    beforeEach(() => {
      permissionServiceStub = sinon.createStubInstance<PermissionService>(PermissionService);
      permissionServiceStub.retrieve.resolves({ headers: {} });

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          bModal: bModalStub as any,
          'font-awesome-icon': true,
          'b-badge': true,
          'b-button': true,
          'router-link': true,
        },
        directives: {
          'b-modal': {},
        },
        provide: {
          alertService,
          permissionService: () => permissionServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        permissionServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Permission, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(permissionServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.permissions[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: PermissionComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Permission, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        permissionServiceStub.retrieve.reset();
        permissionServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        permissionServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removePermission();
        await comp.$nextTick(); // clear components

        // THEN
        expect(permissionServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(permissionServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});