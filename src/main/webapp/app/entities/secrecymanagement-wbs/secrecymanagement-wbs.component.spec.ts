/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import SecrecymanagementWbs from './secrecymanagement-wbs.vue';
import SecrecymanagementWbsService from './secrecymanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

type SecrecymanagementWbsComponentType = InstanceType<typeof SecrecymanagementWbs>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('SecrecymanagementWbs Management Component', () => {
    let secrecymanagementWbsServiceStub: SinonStubbedInstance<SecrecymanagementWbsService>;
    let mountOptions: MountingOptions<SecrecymanagementWbsComponentType>['global'];

    beforeEach(() => {
      secrecymanagementWbsServiceStub = sinon.createStubInstance<SecrecymanagementWbsService>(SecrecymanagementWbsService);
      secrecymanagementWbsServiceStub.retrieve.resolves({ headers: {} });

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
          secrecymanagementWbsService: () => secrecymanagementWbsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        secrecymanagementWbsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(SecrecymanagementWbs, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(secrecymanagementWbsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.secrecymanagementWbs[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: SecrecymanagementWbsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(SecrecymanagementWbs, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        secrecymanagementWbsServiceStub.retrieve.reset();
        secrecymanagementWbsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        secrecymanagementWbsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeSecrecymanagementWbs();
        await comp.$nextTick(); // clear components

        // THEN
        expect(secrecymanagementWbsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(secrecymanagementWbsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
