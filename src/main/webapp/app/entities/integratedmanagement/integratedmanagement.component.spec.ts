/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Integratedmanagement from './integratedmanagement.vue';
import IntegratedmanagementService from './integratedmanagement.service';
import AlertService from '@/shared/alert/alert.service';

type IntegratedmanagementComponentType = InstanceType<typeof Integratedmanagement>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Integratedmanagement Management Component', () => {
    let integratedmanagementServiceStub: SinonStubbedInstance<IntegratedmanagementService>;
    let mountOptions: MountingOptions<IntegratedmanagementComponentType>['global'];

    beforeEach(() => {
      integratedmanagementServiceStub = sinon.createStubInstance<IntegratedmanagementService>(IntegratedmanagementService);
      integratedmanagementServiceStub.retrieve.resolves({ headers: {} });

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
          integratedmanagementService: () => integratedmanagementServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        integratedmanagementServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Integratedmanagement, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(integratedmanagementServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.integratedmanagements[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: IntegratedmanagementComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Integratedmanagement, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        integratedmanagementServiceStub.retrieve.reset();
        integratedmanagementServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        integratedmanagementServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeIntegratedmanagement();
        await comp.$nextTick(); // clear components

        // THEN
        expect(integratedmanagementServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(integratedmanagementServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
