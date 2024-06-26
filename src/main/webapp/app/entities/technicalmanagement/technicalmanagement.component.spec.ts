/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Technicalmanagement from './technicalmanagement.vue';
import TechnicalmanagementService from './technicalmanagement.service';
import AlertService from '@/shared/alert/alert.service';

type TechnicalmanagementComponentType = InstanceType<typeof Technicalmanagement>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Technicalmanagement Management Component', () => {
    let technicalmanagementServiceStub: SinonStubbedInstance<TechnicalmanagementService>;
    let mountOptions: MountingOptions<TechnicalmanagementComponentType>['global'];

    beforeEach(() => {
      technicalmanagementServiceStub = sinon.createStubInstance<TechnicalmanagementService>(TechnicalmanagementService);
      technicalmanagementServiceStub.retrieve.resolves({ headers: {} });

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
          technicalmanagementService: () => technicalmanagementServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        technicalmanagementServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Technicalmanagement, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(technicalmanagementServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.technicalmanagements[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: TechnicalmanagementComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Technicalmanagement, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        technicalmanagementServiceStub.retrieve.reset();
        technicalmanagementServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        technicalmanagementServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeTechnicalmanagement();
        await comp.$nextTick(); // clear components

        // THEN
        expect(technicalmanagementServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(technicalmanagementServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
