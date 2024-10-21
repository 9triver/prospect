/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import UnQualityAudit from './un-quality-audit.vue';
import UnQualityAuditService from './un-quality-audit.service';
import AlertService from '@/shared/alert/alert.service';

type UnQualityAuditComponentType = InstanceType<typeof UnQualityAudit>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('UnQualityAudit Management Component', () => {
    let unQualityAuditServiceStub: SinonStubbedInstance<UnQualityAuditService>;
    let mountOptions: MountingOptions<UnQualityAuditComponentType>['global'];

    beforeEach(() => {
      unQualityAuditServiceStub = sinon.createStubInstance<UnQualityAuditService>(UnQualityAuditService);
      unQualityAuditServiceStub.retrieve.resolves({ headers: {} });

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
          unQualityAuditService: () => unQualityAuditServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        unQualityAuditServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(UnQualityAudit, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(unQualityAuditServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.unQualityAudits[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: UnQualityAuditComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(UnQualityAudit, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        unQualityAuditServiceStub.retrieve.reset();
        unQualityAuditServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        unQualityAuditServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeUnQualityAudit();
        await comp.$nextTick(); // clear components

        // THEN
        expect(unQualityAuditServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(unQualityAuditServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
