/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Qualitymanagement from './qualitymanagement.vue';
import QualitymanagementService from './qualitymanagement.service';
import AlertService from '@/shared/alert/alert.service';

type QualitymanagementComponentType = InstanceType<typeof Qualitymanagement>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Qualitymanagement Management Component', () => {
    let qualitymanagementServiceStub: SinonStubbedInstance<QualitymanagementService>;
    let mountOptions: MountingOptions<QualitymanagementComponentType>['global'];

    beforeEach(() => {
      qualitymanagementServiceStub = sinon.createStubInstance<QualitymanagementService>(QualitymanagementService);
      qualitymanagementServiceStub.retrieve.resolves({ headers: {} });

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
          qualitymanagementService: () => qualitymanagementServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        qualitymanagementServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Qualitymanagement, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(qualitymanagementServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.qualitymanagements[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: QualitymanagementComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Qualitymanagement, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        qualitymanagementServiceStub.retrieve.reset();
        qualitymanagementServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        qualitymanagementServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeQualitymanagement();
        await comp.$nextTick(); // clear components

        // THEN
        expect(qualitymanagementServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(qualitymanagementServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
