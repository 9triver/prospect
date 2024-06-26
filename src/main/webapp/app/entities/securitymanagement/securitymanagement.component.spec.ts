/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Securitymanagement from './securitymanagement.vue';
import SecuritymanagementService from './securitymanagement.service';
import AlertService from '@/shared/alert/alert.service';

type SecuritymanagementComponentType = InstanceType<typeof Securitymanagement>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Securitymanagement Management Component', () => {
    let securitymanagementServiceStub: SinonStubbedInstance<SecuritymanagementService>;
    let mountOptions: MountingOptions<SecuritymanagementComponentType>['global'];

    beforeEach(() => {
      securitymanagementServiceStub = sinon.createStubInstance<SecuritymanagementService>(SecuritymanagementService);
      securitymanagementServiceStub.retrieve.resolves({ headers: {} });

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
          securitymanagementService: () => securitymanagementServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        securitymanagementServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Securitymanagement, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(securitymanagementServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.securitymanagements[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: SecuritymanagementComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Securitymanagement, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        securitymanagementServiceStub.retrieve.reset();
        securitymanagementServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        securitymanagementServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeSecuritymanagement();
        await comp.$nextTick(); // clear components

        // THEN
        expect(securitymanagementServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(securitymanagementServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
