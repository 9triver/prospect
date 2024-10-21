/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import HrManagement from './hr-management.vue';
import HrManagementService from './hr-management.service';
import AlertService from '@/shared/alert/alert.service';

type HrManagementComponentType = InstanceType<typeof HrManagement>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('HrManagement Management Component', () => {
    let hrManagementServiceStub: SinonStubbedInstance<HrManagementService>;
    let mountOptions: MountingOptions<HrManagementComponentType>['global'];

    beforeEach(() => {
      hrManagementServiceStub = sinon.createStubInstance<HrManagementService>(HrManagementService);
      hrManagementServiceStub.retrieve.resolves({ headers: {} });

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
          hrManagementService: () => hrManagementServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        hrManagementServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(HrManagement, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(hrManagementServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.hrManagements[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: HrManagementComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(HrManagement, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        hrManagementServiceStub.retrieve.reset();
        hrManagementServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        hrManagementServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeHrManagement();
        await comp.$nextTick(); // clear components

        // THEN
        expect(hrManagementServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(hrManagementServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
