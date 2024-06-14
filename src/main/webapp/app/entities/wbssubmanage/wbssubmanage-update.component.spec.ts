/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import WbssubmanageUpdate from './wbssubmanage-update.vue';
import WbssubmanageService from './wbssubmanage.service';
import AlertService from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';

type WbssubmanageUpdateComponentType = InstanceType<typeof WbssubmanageUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const wbssubmanageSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<WbssubmanageUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Wbssubmanage Management Update Component', () => {
    let comp: WbssubmanageUpdateComponentType;
    let wbssubmanageServiceStub: SinonStubbedInstance<WbssubmanageService>;

    beforeEach(() => {
      route = {};
      wbssubmanageServiceStub = sinon.createStubInstance<WbssubmanageService>(WbssubmanageService);
      wbssubmanageServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          wbssubmanageService: () => wbssubmanageServiceStub,
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
        const wrapper = shallowMount(WbssubmanageUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.wbssubmanage = wbssubmanageSample;
        wbssubmanageServiceStub.update.resolves(wbssubmanageSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(wbssubmanageServiceStub.update.calledWith(wbssubmanageSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        wbssubmanageServiceStub.create.resolves(entity);
        const wrapper = shallowMount(WbssubmanageUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.wbssubmanage = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(wbssubmanageServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        wbssubmanageServiceStub.find.resolves(wbssubmanageSample);
        wbssubmanageServiceStub.retrieve.resolves([wbssubmanageSample]);

        // WHEN
        route = {
          params: {
            wbssubmanageId: '' + wbssubmanageSample.id,
          },
        };
        const wrapper = shallowMount(WbssubmanageUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.wbssubmanage).toMatchObject(wbssubmanageSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        wbssubmanageServiceStub.find.resolves(wbssubmanageSample);
        const wrapper = shallowMount(WbssubmanageUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
