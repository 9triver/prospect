/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Secrecymanagement from './secrecymanagement.vue';
import SecrecymanagementService from './secrecymanagement.service';
import AlertService from '@/shared/alert/alert.service';

type SecrecymanagementComponentType = InstanceType<typeof Secrecymanagement>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Secrecymanagement Management Component', () => {
    let secrecymanagementServiceStub: SinonStubbedInstance<SecrecymanagementService>;
    let mountOptions: MountingOptions<SecrecymanagementComponentType>['global'];

    beforeEach(() => {
      secrecymanagementServiceStub = sinon.createStubInstance<SecrecymanagementService>(SecrecymanagementService);
      secrecymanagementServiceStub.retrieve.resolves({ headers: {} });

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
          secrecymanagementService: () => secrecymanagementServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        secrecymanagementServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Secrecymanagement, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(secrecymanagementServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.secrecymanagements[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: SecrecymanagementComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Secrecymanagement, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        secrecymanagementServiceStub.retrieve.reset();
        secrecymanagementServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        secrecymanagementServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeSecrecymanagement();
        await comp.$nextTick(); // clear components

        // THEN
        expect(secrecymanagementServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(secrecymanagementServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
