/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Role from './role.vue';
import RoleService from './role.service';
import AlertService from '@/shared/alert/alert.service';

type RoleComponentType = InstanceType<typeof Role>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Role Management Component', () => {
    let roleServiceStub: SinonStubbedInstance<RoleService>;
    let mountOptions: MountingOptions<RoleComponentType>['global'];

    beforeEach(() => {
      roleServiceStub = sinon.createStubInstance<RoleService>(RoleService);
      roleServiceStub.retrieve.resolves({ headers: {} });

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
          roleService: () => roleServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        roleServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Role, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(roleServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.roles[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: RoleComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Role, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        roleServiceStub.retrieve.reset();
        roleServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        roleServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeRole();
        await comp.$nextTick(); // clear components

        // THEN
        expect(roleServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(roleServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
