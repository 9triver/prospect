/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import QualityobjectivesUpdate from './qualityobjectives-update.vue';
import QualityobjectivesService from './qualityobjectives.service';
import AlertService from '@/shared/alert/alert.service';

import QualityreturnsService from '@/entities/qualityreturns/qualityreturns.service';
import OfficersService from '@/entities/officers/officers.service';

type QualityobjectivesUpdateComponentType = InstanceType<typeof QualityobjectivesUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const qualityobjectivesSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<QualityobjectivesUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Qualityobjectives Management Update Component', () => {
    let comp: QualityobjectivesUpdateComponentType;
    let qualityobjectivesServiceStub: SinonStubbedInstance<QualityobjectivesService>;

    beforeEach(() => {
      route = {};
      qualityobjectivesServiceStub = sinon.createStubInstance<QualityobjectivesService>(QualityobjectivesService);
      qualityobjectivesServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          qualityobjectivesService: () => qualityobjectivesServiceStub,
          qualityreturnsService: () =>
            sinon.createStubInstance<QualityreturnsService>(QualityreturnsService, {
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
        const wrapper = shallowMount(QualityobjectivesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.qualityobjectives = qualityobjectivesSample;
        qualityobjectivesServiceStub.update.resolves(qualityobjectivesSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(qualityobjectivesServiceStub.update.calledWith(qualityobjectivesSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        qualityobjectivesServiceStub.create.resolves(entity);
        const wrapper = shallowMount(QualityobjectivesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.qualityobjectives = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(qualityobjectivesServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        qualityobjectivesServiceStub.find.resolves(qualityobjectivesSample);
        qualityobjectivesServiceStub.retrieve.resolves([qualityobjectivesSample]);

        // WHEN
        route = {
          params: {
            qualityobjectivesId: '' + qualityobjectivesSample.id,
          },
        };
        const wrapper = shallowMount(QualityobjectivesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.qualityobjectives).toMatchObject(qualityobjectivesSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        qualityobjectivesServiceStub.find.resolves(qualityobjectivesSample);
        const wrapper = shallowMount(QualityobjectivesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
