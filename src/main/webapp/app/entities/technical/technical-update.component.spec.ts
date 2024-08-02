/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import TechnicalUpdate from './technical-update.vue';
import TechnicalService from './technical.service';
import AlertService from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';

type TechnicalUpdateComponentType = InstanceType<typeof TechnicalUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const technicalSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<TechnicalUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Technical Management Update Component', () => {
    let comp: TechnicalUpdateComponentType;
    let technicalServiceStub: SinonStubbedInstance<TechnicalService>;

    beforeEach(() => {
      route = {};
      technicalServiceStub = sinon.createStubInstance<TechnicalService>(TechnicalService);
      technicalServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          technicalService: () => technicalServiceStub,
          officersService: () =>
            sinon.createStubInstance<OfficersService>(OfficersService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          projectwbsService: () =>
            sinon.createStubInstance<ProjectwbsService>(ProjectwbsService, {
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
        const wrapper = shallowMount(TechnicalUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.technical = technicalSample;
        technicalServiceStub.update.resolves(technicalSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(technicalServiceStub.update.calledWith(technicalSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        technicalServiceStub.create.resolves(entity);
        const wrapper = shallowMount(TechnicalUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.technical = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(technicalServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        technicalServiceStub.find.resolves(technicalSample);
        technicalServiceStub.retrieve.resolves([technicalSample]);

        // WHEN
        route = {
          params: {
            technicalId: '' + technicalSample.id,
          },
        };
        const wrapper = shallowMount(TechnicalUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.technical).toMatchObject(technicalSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        technicalServiceStub.find.resolves(technicalSample);
        const wrapper = shallowMount(TechnicalUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
