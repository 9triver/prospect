/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import SecuritymanagementWbs from './securitymanagement-wbs.vue';
import SecuritymanagementWbsService from './securitymanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

type SecuritymanagementWbsComponentType = InstanceType<typeof SecuritymanagementWbs>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('SecuritymanagementWbs Management Component', () => {
    let securitymanagementWbsServiceStub: SinonStubbedInstance<SecuritymanagementWbsService>;
    let mountOptions: MountingOptions<SecuritymanagementWbsComponentType>['global'];

    beforeEach(() => {
      securitymanagementWbsServiceStub = sinon.createStubInstance<SecuritymanagementWbsService>(SecuritymanagementWbsService);
      securitymanagementWbsServiceStub.retrieve.resolves({ headers: {} });

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
          securitymanagementWbsService: () => securitymanagementWbsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        securitymanagementWbsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(SecuritymanagementWbs, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(securitymanagementWbsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.securitymanagementWbs[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: SecuritymanagementWbsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(SecuritymanagementWbs, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        securitymanagementWbsServiceStub.retrieve.reset();
        securitymanagementWbsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        securitymanagementWbsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeSecuritymanagementWbs();
        await comp.$nextTick(); // clear components

        // THEN
        expect(securitymanagementWbsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(securitymanagementWbsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
