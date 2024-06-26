/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Auditedbudget from './auditedbudget.vue';
import AuditedbudgetService from './auditedbudget.service';
import AlertService from '@/shared/alert/alert.service';

type AuditedbudgetComponentType = InstanceType<typeof Auditedbudget>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Auditedbudget Management Component', () => {
    let auditedbudgetServiceStub: SinonStubbedInstance<AuditedbudgetService>;
    let mountOptions: MountingOptions<AuditedbudgetComponentType>['global'];

    beforeEach(() => {
      auditedbudgetServiceStub = sinon.createStubInstance<AuditedbudgetService>(AuditedbudgetService);
      auditedbudgetServiceStub.retrieve.resolves({ headers: {} });

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
          auditedbudgetService: () => auditedbudgetServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        auditedbudgetServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Auditedbudget, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(auditedbudgetServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.auditedbudgets[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: AuditedbudgetComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Auditedbudget, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        auditedbudgetServiceStub.retrieve.reset();
        auditedbudgetServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        auditedbudgetServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeAuditedbudget();
        await comp.$nextTick(); // clear components

        // THEN
        expect(auditedbudgetServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(auditedbudgetServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
