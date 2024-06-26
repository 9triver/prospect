/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Outsourcingmanagement from './outsourcingmanagement.vue';
import OutsourcingmanagementService from './outsourcingmanagement.service';
import AlertService from '@/shared/alert/alert.service';

type OutsourcingmanagementComponentType = InstanceType<typeof Outsourcingmanagement>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Outsourcingmanagement Management Component', () => {
    let outsourcingmanagementServiceStub: SinonStubbedInstance<OutsourcingmanagementService>;
    let mountOptions: MountingOptions<OutsourcingmanagementComponentType>['global'];

    beforeEach(() => {
      outsourcingmanagementServiceStub = sinon.createStubInstance<OutsourcingmanagementService>(OutsourcingmanagementService);
      outsourcingmanagementServiceStub.retrieve.resolves({ headers: {} });

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
          outsourcingmanagementService: () => outsourcingmanagementServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        outsourcingmanagementServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Outsourcingmanagement, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(outsourcingmanagementServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.outsourcingmanagements[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: OutsourcingmanagementComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Outsourcingmanagement, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        outsourcingmanagementServiceStub.retrieve.reset();
        outsourcingmanagementServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        outsourcingmanagementServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeOutsourcingmanagement();
        await comp.$nextTick(); // clear components

        // THEN
        expect(outsourcingmanagementServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(outsourcingmanagementServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
