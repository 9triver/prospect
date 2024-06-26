/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import PlanmonitorUpdate from './planmonitor-update.vue';
import PlanmonitorService from './planmonitor.service';
import AlertService from '@/shared/alert/alert.service';

type PlanmonitorUpdateComponentType = InstanceType<typeof PlanmonitorUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const planmonitorSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<PlanmonitorUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Planmonitor Management Update Component', () => {
    let comp: PlanmonitorUpdateComponentType;
    let planmonitorServiceStub: SinonStubbedInstance<PlanmonitorService>;

    beforeEach(() => {
      route = {};
      planmonitorServiceStub = sinon.createStubInstance<PlanmonitorService>(PlanmonitorService);
      planmonitorServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          planmonitorService: () => planmonitorServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(PlanmonitorUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.planmonitor = planmonitorSample;
        planmonitorServiceStub.update.resolves(planmonitorSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(planmonitorServiceStub.update.calledWith(planmonitorSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        planmonitorServiceStub.create.resolves(entity);
        const wrapper = shallowMount(PlanmonitorUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.planmonitor = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(planmonitorServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        planmonitorServiceStub.find.resolves(planmonitorSample);
        planmonitorServiceStub.retrieve.resolves([planmonitorSample]);

        // WHEN
        route = {
          params: {
            planmonitorId: '' + planmonitorSample.id,
          },
        };
        const wrapper = shallowMount(PlanmonitorUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.planmonitor).toMatchObject(planmonitorSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        planmonitorServiceStub.find.resolves(planmonitorSample);
        const wrapper = shallowMount(PlanmonitorUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
