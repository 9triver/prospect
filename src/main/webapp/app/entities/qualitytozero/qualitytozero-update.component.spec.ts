/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import QualitytozeroUpdate from './qualitytozero-update.vue';
import QualitytozeroService from './qualitytozero.service';
import AlertService from '@/shared/alert/alert.service';

import WorkbagService from '@/entities/workbag/workbag.service';

type QualitytozeroUpdateComponentType = InstanceType<typeof QualitytozeroUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const qualitytozeroSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<QualitytozeroUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Qualitytozero Management Update Component', () => {
    let comp: QualitytozeroUpdateComponentType;
    let qualitytozeroServiceStub: SinonStubbedInstance<QualitytozeroService>;

    beforeEach(() => {
      route = {};
      qualitytozeroServiceStub = sinon.createStubInstance<QualitytozeroService>(QualitytozeroService);
      qualitytozeroServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          qualitytozeroService: () => qualitytozeroServiceStub,
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
        const wrapper = shallowMount(QualitytozeroUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.qualitytozero = qualitytozeroSample;
        qualitytozeroServiceStub.update.resolves(qualitytozeroSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(qualitytozeroServiceStub.update.calledWith(qualitytozeroSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        qualitytozeroServiceStub.create.resolves(entity);
        const wrapper = shallowMount(QualitytozeroUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.qualitytozero = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(qualitytozeroServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        qualitytozeroServiceStub.find.resolves(qualitytozeroSample);
        qualitytozeroServiceStub.retrieve.resolves([qualitytozeroSample]);

        // WHEN
        route = {
          params: {
            qualitytozeroId: '' + qualitytozeroSample.id,
          },
        };
        const wrapper = shallowMount(QualitytozeroUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.qualitytozero).toMatchObject(qualitytozeroSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        qualitytozeroServiceStub.find.resolves(qualitytozeroSample);
        const wrapper = shallowMount(QualitytozeroUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
