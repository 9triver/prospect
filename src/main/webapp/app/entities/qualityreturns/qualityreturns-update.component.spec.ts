/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import QualityreturnsUpdate from './qualityreturns-update.vue';
import QualityreturnsService from './qualityreturns.service';
import AlertService from '@/shared/alert/alert.service';

type QualityreturnsUpdateComponentType = InstanceType<typeof QualityreturnsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const qualityreturnsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<QualityreturnsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Qualityreturns Management Update Component', () => {
    let comp: QualityreturnsUpdateComponentType;
    let qualityreturnsServiceStub: SinonStubbedInstance<QualityreturnsService>;

    beforeEach(() => {
      route = {};
      qualityreturnsServiceStub = sinon.createStubInstance<QualityreturnsService>(QualityreturnsService);
      qualityreturnsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          qualityreturnsService: () => qualityreturnsServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(QualityreturnsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.qualityreturns = qualityreturnsSample;
        qualityreturnsServiceStub.update.resolves(qualityreturnsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(qualityreturnsServiceStub.update.calledWith(qualityreturnsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        qualityreturnsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(QualityreturnsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.qualityreturns = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(qualityreturnsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        qualityreturnsServiceStub.find.resolves(qualityreturnsSample);
        qualityreturnsServiceStub.retrieve.resolves([qualityreturnsSample]);

        // WHEN
        route = {
          params: {
            qualityreturnsId: '' + qualityreturnsSample.id,
          },
        };
        const wrapper = shallowMount(QualityreturnsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.qualityreturns).toMatchObject(qualityreturnsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        qualityreturnsServiceStub.find.resolves(qualityreturnsSample);
        const wrapper = shallowMount(QualityreturnsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
