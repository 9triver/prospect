/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import WorkUpdate from './work-update.vue';
import WorkService from './work.service';
import AlertService from '@/shared/alert/alert.service';

import WorkbagService from '@/entities/workbag/workbag.service';

type WorkUpdateComponentType = InstanceType<typeof WorkUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const workSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<WorkUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Work Management Update Component', () => {
    let comp: WorkUpdateComponentType;
    let workServiceStub: SinonStubbedInstance<WorkService>;

    beforeEach(() => {
      route = {};
      workServiceStub = sinon.createStubInstance<WorkService>(WorkService);
      workServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          workService: () => workServiceStub,
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
        const wrapper = shallowMount(WorkUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.work = workSample;
        workServiceStub.update.resolves(workSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(workServiceStub.update.calledWith(workSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        workServiceStub.create.resolves(entity);
        const wrapper = shallowMount(WorkUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.work = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(workServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        workServiceStub.find.resolves(workSample);
        workServiceStub.retrieve.resolves([workSample]);

        // WHEN
        route = {
          params: {
            workId: '' + workSample.id,
          },
        };
        const wrapper = shallowMount(WorkUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.work).toMatchObject(workSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        workServiceStub.find.resolves(workSample);
        const wrapper = shallowMount(WorkUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
