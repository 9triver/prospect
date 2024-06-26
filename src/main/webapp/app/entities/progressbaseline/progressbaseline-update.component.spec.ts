/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProgressbaselineUpdate from './progressbaseline-update.vue';
import ProgressbaselineService from './progressbaseline.service';
import AlertService from '@/shared/alert/alert.service';

type ProgressbaselineUpdateComponentType = InstanceType<typeof ProgressbaselineUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const progressbaselineSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ProgressbaselineUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Progressbaseline Management Update Component', () => {
    let comp: ProgressbaselineUpdateComponentType;
    let progressbaselineServiceStub: SinonStubbedInstance<ProgressbaselineService>;

    beforeEach(() => {
      route = {};
      progressbaselineServiceStub = sinon.createStubInstance<ProgressbaselineService>(ProgressbaselineService);
      progressbaselineServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          progressbaselineService: () => progressbaselineServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(ProgressbaselineUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.progressbaseline = progressbaselineSample;
        progressbaselineServiceStub.update.resolves(progressbaselineSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(progressbaselineServiceStub.update.calledWith(progressbaselineSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        progressbaselineServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ProgressbaselineUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.progressbaseline = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(progressbaselineServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        progressbaselineServiceStub.find.resolves(progressbaselineSample);
        progressbaselineServiceStub.retrieve.resolves([progressbaselineSample]);

        // WHEN
        route = {
          params: {
            progressbaselineId: '' + progressbaselineSample.id,
          },
        };
        const wrapper = shallowMount(ProgressbaselineUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.progressbaseline).toMatchObject(progressbaselineSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        progressbaselineServiceStub.find.resolves(progressbaselineSample);
        const wrapper = shallowMount(ProgressbaselineUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
