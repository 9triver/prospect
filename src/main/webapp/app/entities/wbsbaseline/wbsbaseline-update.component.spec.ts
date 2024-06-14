/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import WbsbaselineUpdate from './wbsbaseline-update.vue';
import WbsbaselineService from './wbsbaseline.service';
import AlertService from '@/shared/alert/alert.service';

import ProjectchargeService from '@/entities/projectcharge/projectcharge.service';

type WbsbaselineUpdateComponentType = InstanceType<typeof WbsbaselineUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const wbsbaselineSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<WbsbaselineUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Wbsbaseline Management Update Component', () => {
    let comp: WbsbaselineUpdateComponentType;
    let wbsbaselineServiceStub: SinonStubbedInstance<WbsbaselineService>;

    beforeEach(() => {
      route = {};
      wbsbaselineServiceStub = sinon.createStubInstance<WbsbaselineService>(WbsbaselineService);
      wbsbaselineServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          wbsbaselineService: () => wbsbaselineServiceStub,
          projectchargeService: () =>
            sinon.createStubInstance<ProjectchargeService>(ProjectchargeService, {
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
        const wrapper = shallowMount(WbsbaselineUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.wbsbaseline = wbsbaselineSample;
        wbsbaselineServiceStub.update.resolves(wbsbaselineSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(wbsbaselineServiceStub.update.calledWith(wbsbaselineSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        wbsbaselineServiceStub.create.resolves(entity);
        const wrapper = shallowMount(WbsbaselineUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.wbsbaseline = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(wbsbaselineServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        wbsbaselineServiceStub.find.resolves(wbsbaselineSample);
        wbsbaselineServiceStub.retrieve.resolves([wbsbaselineSample]);

        // WHEN
        route = {
          params: {
            wbsbaselineId: '' + wbsbaselineSample.id,
          },
        };
        const wrapper = shallowMount(WbsbaselineUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.wbsbaseline).toMatchObject(wbsbaselineSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        wbsbaselineServiceStub.find.resolves(wbsbaselineSample);
        const wrapper = shallowMount(WbsbaselineUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
