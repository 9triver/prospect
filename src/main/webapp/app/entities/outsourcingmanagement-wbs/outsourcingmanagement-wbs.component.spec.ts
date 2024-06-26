/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import OutsourcingmanagementWbs from './outsourcingmanagement-wbs.vue';
import OutsourcingmanagementWbsService from './outsourcingmanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

type OutsourcingmanagementWbsComponentType = InstanceType<typeof OutsourcingmanagementWbs>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('OutsourcingmanagementWbs Management Component', () => {
    let outsourcingmanagementWbsServiceStub: SinonStubbedInstance<OutsourcingmanagementWbsService>;
    let mountOptions: MountingOptions<OutsourcingmanagementWbsComponentType>['global'];

    beforeEach(() => {
      outsourcingmanagementWbsServiceStub = sinon.createStubInstance<OutsourcingmanagementWbsService>(OutsourcingmanagementWbsService);
      outsourcingmanagementWbsServiceStub.retrieve.resolves({ headers: {} });

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
          outsourcingmanagementWbsService: () => outsourcingmanagementWbsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        outsourcingmanagementWbsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(OutsourcingmanagementWbs, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(outsourcingmanagementWbsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.outsourcingmanagementWbs[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: OutsourcingmanagementWbsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(OutsourcingmanagementWbs, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        outsourcingmanagementWbsServiceStub.retrieve.reset();
        outsourcingmanagementWbsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        outsourcingmanagementWbsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeOutsourcingmanagementWbs();
        await comp.$nextTick(); // clear components

        // THEN
        expect(outsourcingmanagementWbsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(outsourcingmanagementWbsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
