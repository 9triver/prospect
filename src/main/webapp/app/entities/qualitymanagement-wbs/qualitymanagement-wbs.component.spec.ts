/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import QualitymanagementWbs from './qualitymanagement-wbs.vue';
import QualitymanagementWbsService from './qualitymanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

type QualitymanagementWbsComponentType = InstanceType<typeof QualitymanagementWbs>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('QualitymanagementWbs Management Component', () => {
    let qualitymanagementWbsServiceStub: SinonStubbedInstance<QualitymanagementWbsService>;
    let mountOptions: MountingOptions<QualitymanagementWbsComponentType>['global'];

    beforeEach(() => {
      qualitymanagementWbsServiceStub = sinon.createStubInstance<QualitymanagementWbsService>(QualitymanagementWbsService);
      qualitymanagementWbsServiceStub.retrieve.resolves({ headers: {} });

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
          qualitymanagementWbsService: () => qualitymanagementWbsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        qualitymanagementWbsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(QualitymanagementWbs, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(qualitymanagementWbsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.qualitymanagementWbs[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: QualitymanagementWbsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(QualitymanagementWbs, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        qualitymanagementWbsServiceStub.retrieve.reset();
        qualitymanagementWbsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        qualitymanagementWbsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeQualitymanagementWbs();
        await comp.$nextTick(); // clear components

        // THEN
        expect(qualitymanagementWbsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(qualitymanagementWbsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
