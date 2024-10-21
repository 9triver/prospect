/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import QualityPlanUpdate from './quality-plan-update.vue';
import QualityPlanService from './quality-plan.service';
import AlertService from '@/shared/alert/alert.service';

import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import WorkbagService from '@/entities/workbag/workbag.service';

type QualityPlanUpdateComponentType = InstanceType<typeof QualityPlanUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const qualityPlanSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<QualityPlanUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('QualityPlan Management Update Component', () => {
    let comp: QualityPlanUpdateComponentType;
    let qualityPlanServiceStub: SinonStubbedInstance<QualityPlanService>;

    beforeEach(() => {
      route = {};
      qualityPlanServiceStub = sinon.createStubInstance<QualityPlanService>(QualityPlanService);
      qualityPlanServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          qualityPlanService: () => qualityPlanServiceStub,
          projectwbsService: () =>
            sinon.createStubInstance<ProjectwbsService>(ProjectwbsService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          workbagService: () =>
            sinon.createStubInstance<WorkbagService>(WorkbagService, {
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
        const wrapper = shallowMount(QualityPlanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.qualityPlan = qualityPlanSample;
        qualityPlanServiceStub.update.resolves(qualityPlanSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(qualityPlanServiceStub.update.calledWith(qualityPlanSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        qualityPlanServiceStub.create.resolves(entity);
        const wrapper = shallowMount(QualityPlanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.qualityPlan = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(qualityPlanServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        qualityPlanServiceStub.find.resolves(qualityPlanSample);
        qualityPlanServiceStub.retrieve.resolves([qualityPlanSample]);

        // WHEN
        route = {
          params: {
            qualityPlanId: '' + qualityPlanSample.id,
          },
        };
        const wrapper = shallowMount(QualityPlanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.qualityPlan).toMatchObject(qualityPlanSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        qualityPlanServiceStub.find.resolves(qualityPlanSample);
        const wrapper = shallowMount(QualityPlanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
