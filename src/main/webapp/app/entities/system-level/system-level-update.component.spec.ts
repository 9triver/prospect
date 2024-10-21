/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SystemLevelUpdate from './system-level-update.vue';
import SystemLevelService from './system-level.service';
import AlertService from '@/shared/alert/alert.service';

type SystemLevelUpdateComponentType = InstanceType<typeof SystemLevelUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const systemLevelSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<SystemLevelUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('SystemLevel Management Update Component', () => {
    let comp: SystemLevelUpdateComponentType;
    let systemLevelServiceStub: SinonStubbedInstance<SystemLevelService>;

    beforeEach(() => {
      route = {};
      systemLevelServiceStub = sinon.createStubInstance<SystemLevelService>(SystemLevelService);
      systemLevelServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          systemLevelService: () => systemLevelServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(SystemLevelUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.systemLevel = systemLevelSample;
        systemLevelServiceStub.update.resolves(systemLevelSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(systemLevelServiceStub.update.calledWith(systemLevelSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        systemLevelServiceStub.create.resolves(entity);
        const wrapper = shallowMount(SystemLevelUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.systemLevel = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(systemLevelServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        systemLevelServiceStub.find.resolves(systemLevelSample);
        systemLevelServiceStub.retrieve.resolves([systemLevelSample]);

        // WHEN
        route = {
          params: {
            systemLevelId: '' + systemLevelSample.id,
          },
        };
        const wrapper = shallowMount(SystemLevelUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.systemLevel).toMatchObject(systemLevelSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        systemLevelServiceStub.find.resolves(systemLevelSample);
        const wrapper = shallowMount(SystemLevelUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
