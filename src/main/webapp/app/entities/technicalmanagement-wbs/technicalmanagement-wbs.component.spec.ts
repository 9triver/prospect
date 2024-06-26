/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import TechnicalmanagementWbs from './technicalmanagement-wbs.vue';
import TechnicalmanagementWbsService from './technicalmanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

type TechnicalmanagementWbsComponentType = InstanceType<typeof TechnicalmanagementWbs>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('TechnicalmanagementWbs Management Component', () => {
    let technicalmanagementWbsServiceStub: SinonStubbedInstance<TechnicalmanagementWbsService>;
    let mountOptions: MountingOptions<TechnicalmanagementWbsComponentType>['global'];

    beforeEach(() => {
      technicalmanagementWbsServiceStub = sinon.createStubInstance<TechnicalmanagementWbsService>(TechnicalmanagementWbsService);
      technicalmanagementWbsServiceStub.retrieve.resolves({ headers: {} });

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
          technicalmanagementWbsService: () => technicalmanagementWbsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        technicalmanagementWbsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(TechnicalmanagementWbs, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(technicalmanagementWbsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.technicalmanagementWbs[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: TechnicalmanagementWbsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(TechnicalmanagementWbs, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        technicalmanagementWbsServiceStub.retrieve.reset();
        technicalmanagementWbsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        technicalmanagementWbsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeTechnicalmanagementWbs();
        await comp.$nextTick(); // clear components

        // THEN
        expect(technicalmanagementWbsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(technicalmanagementWbsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
