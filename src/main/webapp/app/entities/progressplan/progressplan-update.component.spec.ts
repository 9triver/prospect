/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProgressplanUpdate from './progressplan-update.vue';
import ProgressplanService from './progressplan.service';
import AlertService from '@/shared/alert/alert.service';

import DepartmentService from '@/entities/department/department.service';
import PlanreturnsService from '@/entities/planreturns/planreturns.service';
import OfficersService from '@/entities/officers/officers.service';

type ProgressplanUpdateComponentType = InstanceType<typeof ProgressplanUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const progressplanSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ProgressplanUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Progressplan Management Update Component', () => {
    let comp: ProgressplanUpdateComponentType;
    let progressplanServiceStub: SinonStubbedInstance<ProgressplanService>;

    beforeEach(() => {
      route = {};
      progressplanServiceStub = sinon.createStubInstance<ProgressplanService>(ProgressplanService);
      progressplanServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          progressplanService: () => progressplanServiceStub,
          departmentService: () =>
            sinon.createStubInstance<DepartmentService>(DepartmentService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
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
        const wrapper = shallowMount(ProgressplanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.progressplan = progressplanSample;
        progressplanServiceStub.update.resolves(progressplanSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(progressplanServiceStub.update.calledWith(progressplanSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        progressplanServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ProgressplanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.progressplan = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(progressplanServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        progressplanServiceStub.find.resolves(progressplanSample);
        progressplanServiceStub.retrieve.resolves([progressplanSample]);

        // WHEN
        route = {
          params: {
            progressplanId: '' + progressplanSample.id,
          },
        };
        const wrapper = shallowMount(ProgressplanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.progressplan).toMatchObject(progressplanSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        progressplanServiceStub.find.resolves(progressplanSample);
        const wrapper = shallowMount(ProgressplanUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
