/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import PlanreturnsUpdate from './planreturns-update.vue';
import PlanreturnsService from './planreturns.service';
import AlertService from '@/shared/alert/alert.service';

type PlanreturnsUpdateComponentType = InstanceType<typeof PlanreturnsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const planreturnsSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<PlanreturnsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Planreturns Management Update Component', () => {
    let comp: PlanreturnsUpdateComponentType;
    let planreturnsServiceStub: SinonStubbedInstance<PlanreturnsService>;

    beforeEach(() => {
      route = {};
      planreturnsServiceStub = sinon.createStubInstance<PlanreturnsService>(PlanreturnsService);
      planreturnsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          planreturnsService: () => planreturnsServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(PlanreturnsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.planreturns = planreturnsSample;
        planreturnsServiceStub.update.resolves(planreturnsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(planreturnsServiceStub.update.calledWith(planreturnsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        planreturnsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(PlanreturnsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.planreturns = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(planreturnsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        planreturnsServiceStub.find.resolves(planreturnsSample);
        planreturnsServiceStub.retrieve.resolves([planreturnsSample]);

        // WHEN
        route = {
          params: {
            planreturnsId: '' + planreturnsSample.id,
          },
        };
        const wrapper = shallowMount(PlanreturnsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.planreturns).toMatchObject(planreturnsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        planreturnsServiceStub.find.resolves(planreturnsSample);
        const wrapper = shallowMount(PlanreturnsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
