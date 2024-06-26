/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import PbsmanageUpdate from './pbsmanage-update.vue';
import PbsmanageService from './pbsmanage.service';
import AlertService from '@/shared/alert/alert.service';

import PbssubmanageService from '@/entities/pbssubmanage/pbssubmanage.service';
import OfficersService from '@/entities/officers/officers.service';

type PbsmanageUpdateComponentType = InstanceType<typeof PbsmanageUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const pbsmanageSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<PbsmanageUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Pbsmanage Management Update Component', () => {
    let comp: PbsmanageUpdateComponentType;
    let pbsmanageServiceStub: SinonStubbedInstance<PbsmanageService>;

    beforeEach(() => {
      route = {};
      pbsmanageServiceStub = sinon.createStubInstance<PbsmanageService>(PbsmanageService);
      pbsmanageServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          pbsmanageService: () => pbsmanageServiceStub,
          pbssubmanageService: () =>
            sinon.createStubInstance<PbssubmanageService>(PbssubmanageService, {
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
        const wrapper = shallowMount(PbsmanageUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.pbsmanage = pbsmanageSample;
        pbsmanageServiceStub.update.resolves(pbsmanageSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(pbsmanageServiceStub.update.calledWith(pbsmanageSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        pbsmanageServiceStub.create.resolves(entity);
        const wrapper = shallowMount(PbsmanageUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.pbsmanage = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(pbsmanageServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        pbsmanageServiceStub.find.resolves(pbsmanageSample);
        pbsmanageServiceStub.retrieve.resolves([pbsmanageSample]);

        // WHEN
        route = {
          params: {
            pbsmanageId: '' + pbsmanageSample.id,
          },
        };
        const wrapper = shallowMount(PbsmanageUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.pbsmanage).toMatchObject(pbsmanageSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        pbsmanageServiceStub.find.resolves(pbsmanageSample);
        const wrapper = shallowMount(PbsmanageUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
