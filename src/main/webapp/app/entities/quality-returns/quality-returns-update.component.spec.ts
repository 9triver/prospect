/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import QualityReturnsUpdate from './quality-returns-update.vue';
import QualityReturnsService from './quality-returns.service';
import AlertService from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import QualityObjectivesService from '@/entities/quality-objectives/quality-objectives.service';

type QualityReturnsUpdateComponentType = InstanceType<typeof QualityReturnsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const qualityReturnsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<QualityReturnsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('QualityReturns Management Update Component', () => {
    let comp: QualityReturnsUpdateComponentType;
    let qualityReturnsServiceStub: SinonStubbedInstance<QualityReturnsService>;

    beforeEach(() => {
      route = {};
      qualityReturnsServiceStub = sinon.createStubInstance<QualityReturnsService>(QualityReturnsService);
      qualityReturnsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          qualityReturnsService: () => qualityReturnsServiceStub,
          officersService: () =>
            sinon.createStubInstance<OfficersService>(OfficersService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          qualityObjectivesService: () =>
            sinon.createStubInstance<QualityObjectivesService>(QualityObjectivesService, {
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
        const wrapper = shallowMount(QualityReturnsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.qualityReturns = qualityReturnsSample;
        qualityReturnsServiceStub.update.resolves(qualityReturnsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(qualityReturnsServiceStub.update.calledWith(qualityReturnsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        qualityReturnsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(QualityReturnsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.qualityReturns = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(qualityReturnsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        qualityReturnsServiceStub.find.resolves(qualityReturnsSample);
        qualityReturnsServiceStub.retrieve.resolves([qualityReturnsSample]);

        // WHEN
        route = {
          params: {
            qualityReturnsId: '' + qualityReturnsSample.id,
          },
        };
        const wrapper = shallowMount(QualityReturnsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.qualityReturns).toMatchObject(qualityReturnsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        qualityReturnsServiceStub.find.resolves(qualityReturnsSample);
        const wrapper = shallowMount(QualityReturnsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
