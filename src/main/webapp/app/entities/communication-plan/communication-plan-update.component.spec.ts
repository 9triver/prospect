/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import CommunicationPlanUpdate from './communication-plan-update.vue';
import CommunicationPlanService from './communication-plan.service';
import AlertService from '@/shared/alert/alert.service';

import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';

type CommunicationPlanUpdateComponentType = InstanceType<typeof CommunicationPlanUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const communicationPlanSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<CommunicationPlanUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('CommunicationPlan Management Update Component', () => {
    let comp: CommunicationPlanUpdateComponentType;
    let communicationPlanServiceStub: SinonStubbedInstance<CommunicationPlanService>;

    beforeEach(() => {
      route = {};
      communicationPlanServiceStub = sinon.createStubInstance<CommunicationPlanService>(CommunicationPlanService);
      communicationPlanServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          communicationPlanService: () => communicationPlanServiceStub,
          projectwbsService: () =>
            sinon.createStubInstance<ProjectwbsService>(ProjectwbsService, {
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
        const wrapper = shallowMount(CommunicationPlanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.communicationPlan = communicationPlanSample;
        communicationPlanServiceStub.update.resolves(communicationPlanSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(communicationPlanServiceStub.update.calledWith(communicationPlanSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        communicationPlanServiceStub.create.resolves(entity);
        const wrapper = shallowMount(CommunicationPlanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.communicationPlan = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(communicationPlanServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        communicationPlanServiceStub.find.resolves(communicationPlanSample);
        communicationPlanServiceStub.retrieve.resolves([communicationPlanSample]);

        // WHEN
        route = {
          params: {
            communicationPlanId: '' + communicationPlanSample.id,
          },
        };
        const wrapper = shallowMount(CommunicationPlanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.communicationPlan).toMatchObject(communicationPlanSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        communicationPlanServiceStub.find.resolves(communicationPlanSample);
        const wrapper = shallowMount(CommunicationPlanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
