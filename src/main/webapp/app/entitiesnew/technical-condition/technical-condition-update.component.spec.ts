/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import TechnicalConditionUpdate from './technical-condition-update.vue';
import TechnicalConditionService from './technical-condition.service';
import AlertService from '@/shared/alert/alert.service';

import WorkbagService from '@/entities/workbag/workbag.service';

type TechnicalConditionUpdateComponentType = InstanceType<typeof TechnicalConditionUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const technicalConditionSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<TechnicalConditionUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('TechnicalCondition Management Update Component', () => {
    let comp: TechnicalConditionUpdateComponentType;
    let technicalConditionServiceStub: SinonStubbedInstance<TechnicalConditionService>;

    beforeEach(() => {
      route = {};
      technicalConditionServiceStub = sinon.createStubInstance<TechnicalConditionService>(TechnicalConditionService);
      technicalConditionServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          technicalConditionService: () => technicalConditionServiceStub,
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
        const wrapper = shallowMount(TechnicalConditionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.technicalCondition = technicalConditionSample;
        technicalConditionServiceStub.update.resolves(technicalConditionSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(technicalConditionServiceStub.update.calledWith(technicalConditionSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        technicalConditionServiceStub.create.resolves(entity);
        const wrapper = shallowMount(TechnicalConditionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.technicalCondition = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(technicalConditionServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        technicalConditionServiceStub.find.resolves(technicalConditionSample);
        technicalConditionServiceStub.retrieve.resolves([technicalConditionSample]);

        // WHEN
        route = {
          params: {
            technicalConditionId: '' + technicalConditionSample.id,
          },
        };
        const wrapper = shallowMount(TechnicalConditionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.technicalCondition).toMatchObject(technicalConditionSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        technicalConditionServiceStub.find.resolves(technicalConditionSample);
        const wrapper = shallowMount(TechnicalConditionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
