/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import QualitymanagementUpdate from './qualitymanagement-update.vue';
import QualitymanagementService from './qualitymanagement.service';
import AlertService from '@/shared/alert/alert.service';

import QualitymanagementWbsService from '@/entities/qualitymanagement-wbs/qualitymanagement-wbs.service';

type QualitymanagementUpdateComponentType = InstanceType<typeof QualitymanagementUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const qualitymanagementSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<QualitymanagementUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Qualitymanagement Management Update Component', () => {
    let comp: QualitymanagementUpdateComponentType;
    let qualitymanagementServiceStub: SinonStubbedInstance<QualitymanagementService>;

    beforeEach(() => {
      route = {};
      qualitymanagementServiceStub = sinon.createStubInstance<QualitymanagementService>(QualitymanagementService);
      qualitymanagementServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          qualitymanagementService: () => qualitymanagementServiceStub,
          qualitymanagementWbsService: () =>
            sinon.createStubInstance<QualitymanagementWbsService>(QualitymanagementWbsService, {
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
        const wrapper = shallowMount(QualitymanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.qualitymanagement = qualitymanagementSample;
        qualitymanagementServiceStub.update.resolves(qualitymanagementSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(qualitymanagementServiceStub.update.calledWith(qualitymanagementSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        qualitymanagementServiceStub.create.resolves(entity);
        const wrapper = shallowMount(QualitymanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.qualitymanagement = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(qualitymanagementServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        qualitymanagementServiceStub.find.resolves(qualitymanagementSample);
        qualitymanagementServiceStub.retrieve.resolves([qualitymanagementSample]);

        // WHEN
        route = {
          params: {
            qualitymanagementId: '' + qualitymanagementSample.id,
          },
        };
        const wrapper = shallowMount(QualitymanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.qualitymanagement).toMatchObject(qualitymanagementSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        qualitymanagementServiceStub.find.resolves(qualitymanagementSample);
        const wrapper = shallowMount(QualitymanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
