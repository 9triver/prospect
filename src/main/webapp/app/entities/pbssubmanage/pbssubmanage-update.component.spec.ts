/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import PbssubmanageUpdate from './pbssubmanage-update.vue';
import PbssubmanageService from './pbssubmanage.service';
import AlertService from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';

type PbssubmanageUpdateComponentType = InstanceType<typeof PbssubmanageUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const pbssubmanageSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<PbssubmanageUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Pbssubmanage Management Update Component', () => {
    let comp: PbssubmanageUpdateComponentType;
    let pbssubmanageServiceStub: SinonStubbedInstance<PbssubmanageService>;

    beforeEach(() => {
      route = {};
      pbssubmanageServiceStub = sinon.createStubInstance<PbssubmanageService>(PbssubmanageService);
      pbssubmanageServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          pbssubmanageService: () => pbssubmanageServiceStub,
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
        const wrapper = shallowMount(PbssubmanageUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.pbssubmanage = pbssubmanageSample;
        pbssubmanageServiceStub.update.resolves(pbssubmanageSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(pbssubmanageServiceStub.update.calledWith(pbssubmanageSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        pbssubmanageServiceStub.create.resolves(entity);
        const wrapper = shallowMount(PbssubmanageUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.pbssubmanage = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(pbssubmanageServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        pbssubmanageServiceStub.find.resolves(pbssubmanageSample);
        pbssubmanageServiceStub.retrieve.resolves([pbssubmanageSample]);

        // WHEN
        route = {
          params: {
            pbssubmanageId: '' + pbssubmanageSample.id,
          },
        };
        const wrapper = shallowMount(PbssubmanageUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.pbssubmanage).toMatchObject(pbssubmanageSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        pbssubmanageServiceStub.find.resolves(pbssubmanageSample);
        const wrapper = shallowMount(PbssubmanageUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
