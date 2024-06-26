/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import AuditedbudgetUpdate from './auditedbudget-update.vue';
import AuditedbudgetService from './auditedbudget.service';
import AlertService from '@/shared/alert/alert.service';

import TotalbudgetService from '@/entities/totalbudget/totalbudget.service';
import UnitbudgetService from '@/entities/unitbudget/unitbudget.service';
import DocumentService from '@/entities/document/document.service';
import OfficersService from '@/entities/officers/officers.service';

type AuditedbudgetUpdateComponentType = InstanceType<typeof AuditedbudgetUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const auditedbudgetSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<AuditedbudgetUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Auditedbudget Management Update Component', () => {
    let comp: AuditedbudgetUpdateComponentType;
    let auditedbudgetServiceStub: SinonStubbedInstance<AuditedbudgetService>;

    beforeEach(() => {
      route = {};
      auditedbudgetServiceStub = sinon.createStubInstance<AuditedbudgetService>(AuditedbudgetService);
      auditedbudgetServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'b-input-group': true,
          'b-input-group-prepend': true,
          'b-form-datepicker': true,
          'b-form-input': true,
        },
        provide: {
          alertService,
          auditedbudgetService: () => auditedbudgetServiceStub,
          totalbudgetService: () =>
            sinon.createStubInstance<TotalbudgetService>(TotalbudgetService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          unitbudgetService: () =>
            sinon.createStubInstance<UnitbudgetService>(UnitbudgetService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          documentService: () =>
            sinon.createStubInstance<DocumentService>(DocumentService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          officersService: () =>
            sinon.createStubInstance<OfficersService>(OfficersService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(AuditedbudgetUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.auditedbudget = auditedbudgetSample;
        auditedbudgetServiceStub.update.resolves(auditedbudgetSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(auditedbudgetServiceStub.update.calledWith(auditedbudgetSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        auditedbudgetServiceStub.create.resolves(entity);
        const wrapper = shallowMount(AuditedbudgetUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.auditedbudget = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(auditedbudgetServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        auditedbudgetServiceStub.find.resolves(auditedbudgetSample);
        auditedbudgetServiceStub.retrieve.resolves([auditedbudgetSample]);

        // WHEN
        route = {
          params: {
            auditedbudgetId: '' + auditedbudgetSample.id,
          },
        };
        const wrapper = shallowMount(AuditedbudgetUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.auditedbudget).toMatchObject(auditedbudgetSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        auditedbudgetServiceStub.find.resolves(auditedbudgetSample);
        const wrapper = shallowMount(AuditedbudgetUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
