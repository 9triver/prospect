/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import QualityObjectivesUpdate from './quality-objectives-update.vue';
import QualityObjectivesService from './quality-objectives.service';
import AlertService from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import QualityReturnsService from '@/entities/quality-returns/quality-returns.service';

type QualityObjectivesUpdateComponentType = InstanceType<typeof QualityObjectivesUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const qualityObjectivesSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<QualityObjectivesUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('QualityObjectives Management Update Component', () => {
    let comp: QualityObjectivesUpdateComponentType;
    let qualityObjectivesServiceStub: SinonStubbedInstance<QualityObjectivesService>;

    beforeEach(() => {
      route = {};
      qualityObjectivesServiceStub = sinon.createStubInstance<QualityObjectivesService>(QualityObjectivesService);
      qualityObjectivesServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          qualityObjectivesService: () => qualityObjectivesServiceStub,
          officersService: () =>
            sinon.createStubInstance<OfficersService>(OfficersService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          projectwbsService: () =>
            sinon.createStubInstance<ProjectwbsService>(ProjectwbsService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          qualityReturnsService: () =>
            sinon.createStubInstance<QualityReturnsService>(QualityReturnsService, {
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
        const wrapper = shallowMount(QualityObjectivesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.qualityObjectives = qualityObjectivesSample;
        qualityObjectivesServiceStub.update.resolves(qualityObjectivesSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(qualityObjectivesServiceStub.update.calledWith(qualityObjectivesSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        qualityObjectivesServiceStub.create.resolves(entity);
        const wrapper = shallowMount(QualityObjectivesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.qualityObjectives = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(qualityObjectivesServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        qualityObjectivesServiceStub.find.resolves(qualityObjectivesSample);
        qualityObjectivesServiceStub.retrieve.resolves([qualityObjectivesSample]);

        // WHEN
        route = {
          params: {
            qualityObjectivesId: '' + qualityObjectivesSample.id,
          },
        };
        const wrapper = shallowMount(QualityObjectivesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.qualityObjectives).toMatchObject(qualityObjectivesSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        qualityObjectivesServiceStub.find.resolves(qualityObjectivesSample);
        const wrapper = shallowMount(QualityObjectivesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
