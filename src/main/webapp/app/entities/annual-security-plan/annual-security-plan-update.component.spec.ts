/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import AnnualSecurityPlanUpdate from './annual-security-plan-update.vue';
import AnnualSecurityPlanService from './annual-security-plan.service';
import AlertService from '@/shared/alert/alert.service';

import ProjectService from '@/entities/project/project.service';
import OfficersService from '@/entities/officers/officers.service';

type AnnualSecurityPlanUpdateComponentType = InstanceType<typeof AnnualSecurityPlanUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const annualSecurityPlanSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<AnnualSecurityPlanUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('AnnualSecurityPlan Management Update Component', () => {
    let comp: AnnualSecurityPlanUpdateComponentType;
    let annualSecurityPlanServiceStub: SinonStubbedInstance<AnnualSecurityPlanService>;

    beforeEach(() => {
      route = {};
      annualSecurityPlanServiceStub = sinon.createStubInstance<AnnualSecurityPlanService>(AnnualSecurityPlanService);
      annualSecurityPlanServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          annualSecurityPlanService: () => annualSecurityPlanServiceStub,
          projectService: () =>
            sinon.createStubInstance<ProjectService>(ProjectService, {
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
        const wrapper = shallowMount(AnnualSecurityPlanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.annualSecurityPlan = annualSecurityPlanSample;
        annualSecurityPlanServiceStub.update.resolves(annualSecurityPlanSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(annualSecurityPlanServiceStub.update.calledWith(annualSecurityPlanSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        annualSecurityPlanServiceStub.create.resolves(entity);
        const wrapper = shallowMount(AnnualSecurityPlanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.annualSecurityPlan = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(annualSecurityPlanServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        annualSecurityPlanServiceStub.find.resolves(annualSecurityPlanSample);
        annualSecurityPlanServiceStub.retrieve.resolves([annualSecurityPlanSample]);

        // WHEN
        route = {
          params: {
            annualSecurityPlanId: '' + annualSecurityPlanSample.id,
          },
        };
        const wrapper = shallowMount(AnnualSecurityPlanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.annualSecurityPlan).toMatchObject(annualSecurityPlanSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        annualSecurityPlanServiceStub.find.resolves(annualSecurityPlanSample);
        const wrapper = shallowMount(AnnualSecurityPlanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
