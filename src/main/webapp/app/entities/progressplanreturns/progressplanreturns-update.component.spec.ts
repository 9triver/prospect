/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProgressplanreturnsUpdate from './progressplanreturns-update.vue';
import ProgressplanreturnsService from './progressplanreturns.service';
import AlertService from '@/shared/alert/alert.service';

import ProgressplanService from '@/entities/progressplan/progressplan.service';
import DocumentService from '@/entities/document/document.service';

type ProgressplanreturnsUpdateComponentType = InstanceType<typeof ProgressplanreturnsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const progressplanreturnsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ProgressplanreturnsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Progressplanreturns Management Update Component', () => {
    let comp: ProgressplanreturnsUpdateComponentType;
    let progressplanreturnsServiceStub: SinonStubbedInstance<ProgressplanreturnsService>;

    beforeEach(() => {
      route = {};
      progressplanreturnsServiceStub = sinon.createStubInstance<ProgressplanreturnsService>(ProgressplanreturnsService);
      progressplanreturnsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          progressplanreturnsService: () => progressplanreturnsServiceStub,
          progressplanService: () =>
            sinon.createStubInstance<ProgressplanService>(ProgressplanService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          documentService: () =>
            sinon.createStubInstance<DocumentService>(DocumentService, {
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
        const wrapper = shallowMount(ProgressplanreturnsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.progressplanreturns = progressplanreturnsSample;
        progressplanreturnsServiceStub.update.resolves(progressplanreturnsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(progressplanreturnsServiceStub.update.calledWith(progressplanreturnsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        progressplanreturnsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ProgressplanreturnsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.progressplanreturns = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(progressplanreturnsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        progressplanreturnsServiceStub.find.resolves(progressplanreturnsSample);
        progressplanreturnsServiceStub.retrieve.resolves([progressplanreturnsSample]);

        // WHEN
        route = {
          params: {
            progressplanreturnsId: '' + progressplanreturnsSample.id,
          },
        };
        const wrapper = shallowMount(ProgressplanreturnsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.progressplanreturns).toMatchObject(progressplanreturnsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        progressplanreturnsServiceStub.find.resolves(progressplanreturnsSample);
        const wrapper = shallowMount(ProgressplanreturnsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
