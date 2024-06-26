/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import WbsmanageUpdate from './wbsmanage-update.vue';
import WbsmanageService from './wbsmanage.service';
import AlertService from '@/shared/alert/alert.service';

import WbssubmanageService from '@/entities/wbssubmanage/wbssubmanage.service';
import PbssubmanageService from '@/entities/pbssubmanage/pbssubmanage.service';
import ProjectService from '@/entities/project/project.service';
import OfficersService from '@/entities/officers/officers.service';

type WbsmanageUpdateComponentType = InstanceType<typeof WbsmanageUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const wbsmanageSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<WbsmanageUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Wbsmanage Management Update Component', () => {
    let comp: WbsmanageUpdateComponentType;
    let wbsmanageServiceStub: SinonStubbedInstance<WbsmanageService>;

    beforeEach(() => {
      route = {};
      wbsmanageServiceStub = sinon.createStubInstance<WbsmanageService>(WbsmanageService);
      wbsmanageServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          wbsmanageService: () => wbsmanageServiceStub,
          wbssubmanageService: () =>
            sinon.createStubInstance<WbssubmanageService>(WbssubmanageService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          pbssubmanageService: () =>
            sinon.createStubInstance<PbssubmanageService>(PbssubmanageService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          projectService: () =>
            sinon.createStubInstance<ProjectService>(ProjectService, {
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
        const wrapper = shallowMount(WbsmanageUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.wbsmanage = wbsmanageSample;
        wbsmanageServiceStub.update.resolves(wbsmanageSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(wbsmanageServiceStub.update.calledWith(wbsmanageSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        wbsmanageServiceStub.create.resolves(entity);
        const wrapper = shallowMount(WbsmanageUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.wbsmanage = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(wbsmanageServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        wbsmanageServiceStub.find.resolves(wbsmanageSample);
        wbsmanageServiceStub.retrieve.resolves([wbsmanageSample]);

        // WHEN
        route = {
          params: {
            wbsmanageId: '' + wbsmanageSample.id,
          },
        };
        const wrapper = shallowMount(WbsmanageUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.wbsmanage).toMatchObject(wbsmanageSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        wbsmanageServiceStub.find.resolves(wbsmanageSample);
        const wrapper = shallowMount(WbsmanageUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
