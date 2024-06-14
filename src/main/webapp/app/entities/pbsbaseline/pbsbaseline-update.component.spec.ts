/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import PbsbaselineUpdate from './pbsbaseline-update.vue';
import PbsbaselineService from './pbsbaseline.service';
import AlertService from '@/shared/alert/alert.service';

import ProjectchargeService from '@/entities/projectcharge/projectcharge.service';

type PbsbaselineUpdateComponentType = InstanceType<typeof PbsbaselineUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const pbsbaselineSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<PbsbaselineUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Pbsbaseline Management Update Component', () => {
    let comp: PbsbaselineUpdateComponentType;
    let pbsbaselineServiceStub: SinonStubbedInstance<PbsbaselineService>;

    beforeEach(() => {
      route = {};
      pbsbaselineServiceStub = sinon.createStubInstance<PbsbaselineService>(PbsbaselineService);
      pbsbaselineServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          pbsbaselineService: () => pbsbaselineServiceStub,
          projectchargeService: () =>
            sinon.createStubInstance<ProjectchargeService>(ProjectchargeService, {
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
        const wrapper = shallowMount(PbsbaselineUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.pbsbaseline = pbsbaselineSample;
        pbsbaselineServiceStub.update.resolves(pbsbaselineSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(pbsbaselineServiceStub.update.calledWith(pbsbaselineSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        pbsbaselineServiceStub.create.resolves(entity);
        const wrapper = shallowMount(PbsbaselineUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.pbsbaseline = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(pbsbaselineServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        pbsbaselineServiceStub.find.resolves(pbsbaselineSample);
        pbsbaselineServiceStub.retrieve.resolves([pbsbaselineSample]);

        // WHEN
        route = {
          params: {
            pbsbaselineId: '' + pbsbaselineSample.id,
          },
        };
        const wrapper = shallowMount(PbsbaselineUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.pbsbaseline).toMatchObject(pbsbaselineSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        pbsbaselineServiceStub.find.resolves(pbsbaselineSample);
        const wrapper = shallowMount(PbsbaselineUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
