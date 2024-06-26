/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import TechnicalmanagementUpdate from './technicalmanagement-update.vue';
import TechnicalmanagementService from './technicalmanagement.service';
import AlertService from '@/shared/alert/alert.service';

import TechnicalmanagementWbsService from '@/entities/technicalmanagement-wbs/technicalmanagement-wbs.service';

type TechnicalmanagementUpdateComponentType = InstanceType<typeof TechnicalmanagementUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const technicalmanagementSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<TechnicalmanagementUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Technicalmanagement Management Update Component', () => {
    let comp: TechnicalmanagementUpdateComponentType;
    let technicalmanagementServiceStub: SinonStubbedInstance<TechnicalmanagementService>;

    beforeEach(() => {
      route = {};
      technicalmanagementServiceStub = sinon.createStubInstance<TechnicalmanagementService>(TechnicalmanagementService);
      technicalmanagementServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          technicalmanagementService: () => technicalmanagementServiceStub,
          technicalmanagementWbsService: () =>
            sinon.createStubInstance<TechnicalmanagementWbsService>(TechnicalmanagementWbsService, {
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
        const wrapper = shallowMount(TechnicalmanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.technicalmanagement = technicalmanagementSample;
        technicalmanagementServiceStub.update.resolves(technicalmanagementSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(technicalmanagementServiceStub.update.calledWith(technicalmanagementSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        technicalmanagementServiceStub.create.resolves(entity);
        const wrapper = shallowMount(TechnicalmanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.technicalmanagement = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(technicalmanagementServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        technicalmanagementServiceStub.find.resolves(technicalmanagementSample);
        technicalmanagementServiceStub.retrieve.resolves([technicalmanagementSample]);

        // WHEN
        route = {
          params: {
            technicalmanagementId: '' + technicalmanagementSample.id,
          },
        };
        const wrapper = shallowMount(TechnicalmanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.technicalmanagement).toMatchObject(technicalmanagementSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        technicalmanagementServiceStub.find.resolves(technicalmanagementSample);
        const wrapper = shallowMount(TechnicalmanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
