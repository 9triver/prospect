/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProgressmanagementWbsUpdate from './progressmanagement-wbs-update.vue';
import ProgressmanagementWbsService from './progressmanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

import ProgressplanService from '@/entities/progressplan/progressplan.service';
import ProgressplanreturnsService from '@/entities/progressplanreturns/progressplanreturns.service';
import ProgressbaselineService from '@/entities/progressbaseline/progressbaseline.service';

type ProgressmanagementWbsUpdateComponentType = InstanceType<typeof ProgressmanagementWbsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const progressmanagementWbsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ProgressmanagementWbsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ProgressmanagementWbs Management Update Component', () => {
    let comp: ProgressmanagementWbsUpdateComponentType;
    let progressmanagementWbsServiceStub: SinonStubbedInstance<ProgressmanagementWbsService>;

    beforeEach(() => {
      route = {};
      progressmanagementWbsServiceStub = sinon.createStubInstance<ProgressmanagementWbsService>(ProgressmanagementWbsService);
      progressmanagementWbsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          progressmanagementWbsService: () => progressmanagementWbsServiceStub,
          progressplanService: () =>
            sinon.createStubInstance<ProgressplanService>(ProgressplanService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          progressplanreturnsService: () =>
            sinon.createStubInstance<ProgressplanreturnsService>(ProgressplanreturnsService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          progressbaselineService: () =>
            sinon.createStubInstance<ProgressbaselineService>(ProgressbaselineService, {
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
        const wrapper = shallowMount(ProgressmanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.progressmanagementWbs = progressmanagementWbsSample;
        progressmanagementWbsServiceStub.update.resolves(progressmanagementWbsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(progressmanagementWbsServiceStub.update.calledWith(progressmanagementWbsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        progressmanagementWbsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ProgressmanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.progressmanagementWbs = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(progressmanagementWbsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        progressmanagementWbsServiceStub.find.resolves(progressmanagementWbsSample);
        progressmanagementWbsServiceStub.retrieve.resolves([progressmanagementWbsSample]);

        // WHEN
        route = {
          params: {
            progressmanagementWbsId: '' + progressmanagementWbsSample.id,
          },
        };
        const wrapper = shallowMount(ProgressmanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.progressmanagementWbs).toMatchObject(progressmanagementWbsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        progressmanagementWbsServiceStub.find.resolves(progressmanagementWbsSample);
        const wrapper = shallowMount(ProgressmanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
