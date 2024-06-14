/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import UnQualityAuditUpdate from './un-quality-audit-update.vue';
import UnQualityAuditService from './un-quality-audit.service';
import AlertService from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';

type UnQualityAuditUpdateComponentType = InstanceType<typeof UnQualityAuditUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const unQualityAuditSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<UnQualityAuditUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('UnQualityAudit Management Update Component', () => {
    let comp: UnQualityAuditUpdateComponentType;
    let unQualityAuditServiceStub: SinonStubbedInstance<UnQualityAuditService>;

    beforeEach(() => {
      route = {};
      unQualityAuditServiceStub = sinon.createStubInstance<UnQualityAuditService>(UnQualityAuditService);
      unQualityAuditServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          unQualityAuditService: () => unQualityAuditServiceStub,
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
        const wrapper = shallowMount(UnQualityAuditUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.unQualityAudit = unQualityAuditSample;
        unQualityAuditServiceStub.update.resolves(unQualityAuditSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(unQualityAuditServiceStub.update.calledWith(unQualityAuditSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        unQualityAuditServiceStub.create.resolves(entity);
        const wrapper = shallowMount(UnQualityAuditUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.unQualityAudit = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(unQualityAuditServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        unQualityAuditServiceStub.find.resolves(unQualityAuditSample);
        unQualityAuditServiceStub.retrieve.resolves([unQualityAuditSample]);

        // WHEN
        route = {
          params: {
            unQualityAuditId: '' + unQualityAuditSample.id,
          },
        };
        const wrapper = shallowMount(UnQualityAuditUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.unQualityAudit).toMatchObject(unQualityAuditSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        unQualityAuditServiceStub.find.resolves(unQualityAuditSample);
        const wrapper = shallowMount(UnQualityAuditUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
