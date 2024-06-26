/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import PlanexecuteUpdate from './planexecute-update.vue';
import PlanexecuteService from './planexecute.service';
import AlertService from '@/shared/alert/alert.service';

import PlanreturnsService from '@/entities/planreturns/planreturns.service';
import OfficersService from '@/entities/officers/officers.service';

type PlanexecuteUpdateComponentType = InstanceType<typeof PlanexecuteUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const planexecuteSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<PlanexecuteUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Planexecute Management Update Component', () => {
    let comp: PlanexecuteUpdateComponentType;
    let planexecuteServiceStub: SinonStubbedInstance<PlanexecuteService>;

    beforeEach(() => {
      route = {};
      planexecuteServiceStub = sinon.createStubInstance<PlanexecuteService>(PlanexecuteService);
      planexecuteServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          planexecuteService: () => planexecuteServiceStub,
          planreturnsService: () =>
            sinon.createStubInstance<PlanreturnsService>(PlanreturnsService, {
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
        const wrapper = shallowMount(PlanexecuteUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.planexecute = planexecuteSample;
        planexecuteServiceStub.update.resolves(planexecuteSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(planexecuteServiceStub.update.calledWith(planexecuteSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        planexecuteServiceStub.create.resolves(entity);
        const wrapper = shallowMount(PlanexecuteUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.planexecute = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(planexecuteServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        planexecuteServiceStub.find.resolves(planexecuteSample);
        planexecuteServiceStub.retrieve.resolves([planexecuteSample]);

        // WHEN
        route = {
          params: {
            planexecuteId: '' + planexecuteSample.id,
          },
        };
        const wrapper = shallowMount(PlanexecuteUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.planexecute).toMatchObject(planexecuteSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        planexecuteServiceStub.find.resolves(planexecuteSample);
        const wrapper = shallowMount(PlanexecuteUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
