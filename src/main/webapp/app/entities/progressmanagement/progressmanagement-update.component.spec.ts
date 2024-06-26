/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProgressmanagementUpdate from './progressmanagement-update.vue';
import ProgressmanagementService from './progressmanagement.service';
import AlertService from '@/shared/alert/alert.service';

import ProgressmanagementWbsService from '@/entities/progressmanagement-wbs/progressmanagement-wbs.service';

type ProgressmanagementUpdateComponentType = InstanceType<typeof ProgressmanagementUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const progressmanagementSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ProgressmanagementUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Progressmanagement Management Update Component', () => {
    let comp: ProgressmanagementUpdateComponentType;
    let progressmanagementServiceStub: SinonStubbedInstance<ProgressmanagementService>;

    beforeEach(() => {
      route = {};
      progressmanagementServiceStub = sinon.createStubInstance<ProgressmanagementService>(ProgressmanagementService);
      progressmanagementServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          progressmanagementService: () => progressmanagementServiceStub,
          progressmanagementWbsService: () =>
            sinon.createStubInstance<ProgressmanagementWbsService>(ProgressmanagementWbsService, {
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
        const wrapper = shallowMount(ProgressmanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.progressmanagement = progressmanagementSample;
        progressmanagementServiceStub.update.resolves(progressmanagementSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(progressmanagementServiceStub.update.calledWith(progressmanagementSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        progressmanagementServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ProgressmanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.progressmanagement = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(progressmanagementServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        progressmanagementServiceStub.find.resolves(progressmanagementSample);
        progressmanagementServiceStub.retrieve.resolves([progressmanagementSample]);

        // WHEN
        route = {
          params: {
            progressmanagementId: '' + progressmanagementSample.id,
          },
        };
        const wrapper = shallowMount(ProgressmanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.progressmanagement).toMatchObject(progressmanagementSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        progressmanagementServiceStub.find.resolves(progressmanagementSample);
        const wrapper = shallowMount(ProgressmanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
