/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SafetycheckUpdate from './safetycheck-update.vue';
import SafetycheckService from './safetycheck.service';
import AlertService from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';

type SafetycheckUpdateComponentType = InstanceType<typeof SafetycheckUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const safetycheckSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<SafetycheckUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Safetycheck Management Update Component', () => {
    let comp: SafetycheckUpdateComponentType;
    let safetycheckServiceStub: SinonStubbedInstance<SafetycheckService>;

    beforeEach(() => {
      route = {};
      safetycheckServiceStub = sinon.createStubInstance<SafetycheckService>(SafetycheckService);
      safetycheckServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          safetycheckService: () => safetycheckServiceStub,
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
        const wrapper = shallowMount(SafetycheckUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.safetycheck = safetycheckSample;
        safetycheckServiceStub.update.resolves(safetycheckSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(safetycheckServiceStub.update.calledWith(safetycheckSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        safetycheckServiceStub.create.resolves(entity);
        const wrapper = shallowMount(SafetycheckUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.safetycheck = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(safetycheckServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        safetycheckServiceStub.find.resolves(safetycheckSample);
        safetycheckServiceStub.retrieve.resolves([safetycheckSample]);

        // WHEN
        route = {
          params: {
            safetycheckId: '' + safetycheckSample.id,
          },
        };
        const wrapper = shallowMount(SafetycheckUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.safetycheck).toMatchObject(safetycheckSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        safetycheckServiceStub.find.resolves(safetycheckSample);
        const wrapper = shallowMount(SafetycheckUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
