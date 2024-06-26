/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import IntegratedmanagementWbs from './integratedmanagement-wbs.vue';
import IntegratedmanagementWbsService from './integratedmanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

type IntegratedmanagementWbsComponentType = InstanceType<typeof IntegratedmanagementWbs>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('IntegratedmanagementWbs Management Component', () => {
    let integratedmanagementWbsServiceStub: SinonStubbedInstance<IntegratedmanagementWbsService>;
    let mountOptions: MountingOptions<IntegratedmanagementWbsComponentType>['global'];

    beforeEach(() => {
      integratedmanagementWbsServiceStub = sinon.createStubInstance<IntegratedmanagementWbsService>(IntegratedmanagementWbsService);
      integratedmanagementWbsServiceStub.retrieve.resolves({ headers: {} });

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
          integratedmanagementWbsService: () => integratedmanagementWbsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        integratedmanagementWbsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(IntegratedmanagementWbs, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(integratedmanagementWbsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.integratedmanagementWbs[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: IntegratedmanagementWbsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(IntegratedmanagementWbs, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        integratedmanagementWbsServiceStub.retrieve.reset();
        integratedmanagementWbsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        integratedmanagementWbsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeIntegratedmanagementWbs();
        await comp.$nextTick(); // clear components

        // THEN
        expect(integratedmanagementWbsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(integratedmanagementWbsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
