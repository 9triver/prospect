/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import TechnicalmanagementWbsUpdate from './technicalmanagement-wbs-update.vue';
import TechnicalmanagementWbsService from './technicalmanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

import TechnicalConditionService from '@/entities/technical-condition/technical-condition.service';

type TechnicalmanagementWbsUpdateComponentType = InstanceType<typeof TechnicalmanagementWbsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const technicalmanagementWbsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<TechnicalmanagementWbsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('TechnicalmanagementWbs Management Update Component', () => {
    let comp: TechnicalmanagementWbsUpdateComponentType;
    let technicalmanagementWbsServiceStub: SinonStubbedInstance<TechnicalmanagementWbsService>;

    beforeEach(() => {
      route = {};
      technicalmanagementWbsServiceStub = sinon.createStubInstance<TechnicalmanagementWbsService>(TechnicalmanagementWbsService);
      technicalmanagementWbsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          technicalmanagementWbsService: () => technicalmanagementWbsServiceStub,
          technicalConditionService: () =>
            sinon.createStubInstance<TechnicalConditionService>(TechnicalConditionService, {
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
        const wrapper = shallowMount(TechnicalmanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.technicalmanagementWbs = technicalmanagementWbsSample;
        technicalmanagementWbsServiceStub.update.resolves(technicalmanagementWbsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(technicalmanagementWbsServiceStub.update.calledWith(technicalmanagementWbsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        technicalmanagementWbsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(TechnicalmanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.technicalmanagementWbs = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(technicalmanagementWbsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        technicalmanagementWbsServiceStub.find.resolves(technicalmanagementWbsSample);
        technicalmanagementWbsServiceStub.retrieve.resolves([technicalmanagementWbsSample]);

        // WHEN
        route = {
          params: {
            technicalmanagementWbsId: '' + technicalmanagementWbsSample.id,
          },
        };
        const wrapper = shallowMount(TechnicalmanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.technicalmanagementWbs).toMatchObject(technicalmanagementWbsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        technicalmanagementWbsServiceStub.find.resolves(technicalmanagementWbsSample);
        const wrapper = shallowMount(TechnicalmanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
