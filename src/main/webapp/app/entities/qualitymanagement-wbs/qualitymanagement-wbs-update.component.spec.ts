/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import QualitymanagementWbsUpdate from './qualitymanagement-wbs-update.vue';
import QualitymanagementWbsService from './qualitymanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

import QualityobjectivesService from '@/entities/qualityobjectives/qualityobjectives.service';
import QualityreturnsService from '@/entities/qualityreturns/qualityreturns.service';
import UnQualityAuditService from '@/entities/un-quality-audit/un-quality-audit.service';

type QualitymanagementWbsUpdateComponentType = InstanceType<typeof QualitymanagementWbsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const qualitymanagementWbsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<QualitymanagementWbsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('QualitymanagementWbs Management Update Component', () => {
    let comp: QualitymanagementWbsUpdateComponentType;
    let qualitymanagementWbsServiceStub: SinonStubbedInstance<QualitymanagementWbsService>;

    beforeEach(() => {
      route = {};
      qualitymanagementWbsServiceStub = sinon.createStubInstance<QualitymanagementWbsService>(QualitymanagementWbsService);
      qualitymanagementWbsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          qualitymanagementWbsService: () => qualitymanagementWbsServiceStub,
          qualityobjectivesService: () =>
            sinon.createStubInstance<QualityobjectivesService>(QualityobjectivesService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          qualityreturnsService: () =>
            sinon.createStubInstance<QualityreturnsService>(QualityreturnsService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          unQualityAuditService: () =>
            sinon.createStubInstance<UnQualityAuditService>(UnQualityAuditService, {
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
        const wrapper = shallowMount(QualitymanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.qualitymanagementWbs = qualitymanagementWbsSample;
        qualitymanagementWbsServiceStub.update.resolves(qualitymanagementWbsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(qualitymanagementWbsServiceStub.update.calledWith(qualitymanagementWbsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        qualitymanagementWbsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(QualitymanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.qualitymanagementWbs = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(qualitymanagementWbsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        qualitymanagementWbsServiceStub.find.resolves(qualitymanagementWbsSample);
        qualitymanagementWbsServiceStub.retrieve.resolves([qualitymanagementWbsSample]);

        // WHEN
        route = {
          params: {
            qualitymanagementWbsId: '' + qualitymanagementWbsSample.id,
          },
        };
        const wrapper = shallowMount(QualitymanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.qualitymanagementWbs).toMatchObject(qualitymanagementWbsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        qualitymanagementWbsServiceStub.find.resolves(qualitymanagementWbsSample);
        const wrapper = shallowMount(QualitymanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
