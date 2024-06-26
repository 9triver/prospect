/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RiskreportUpdate from './riskreport-update.vue';
import RiskreportService from './riskreport.service';
import AlertService from '@/shared/alert/alert.service';

import RiskidentificationService from '@/entities/riskidentification/riskidentification.service';
import OfficersService from '@/entities/officers/officers.service';

type RiskreportUpdateComponentType = InstanceType<typeof RiskreportUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const riskreportSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<RiskreportUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Riskreport Management Update Component', () => {
    let comp: RiskreportUpdateComponentType;
    let riskreportServiceStub: SinonStubbedInstance<RiskreportService>;

    beforeEach(() => {
      route = {};
      riskreportServiceStub = sinon.createStubInstance<RiskreportService>(RiskreportService);
      riskreportServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          riskreportService: () => riskreportServiceStub,
          riskidentificationService: () =>
            sinon.createStubInstance<RiskidentificationService>(RiskidentificationService, {
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
        const wrapper = shallowMount(RiskreportUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.riskreport = riskreportSample;
        riskreportServiceStub.update.resolves(riskreportSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(riskreportServiceStub.update.calledWith(riskreportSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        riskreportServiceStub.create.resolves(entity);
        const wrapper = shallowMount(RiskreportUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.riskreport = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(riskreportServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        riskreportServiceStub.find.resolves(riskreportSample);
        riskreportServiceStub.retrieve.resolves([riskreportSample]);

        // WHEN
        route = {
          params: {
            riskreportId: '' + riskreportSample.id,
          },
        };
        const wrapper = shallowMount(RiskreportUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.riskreport).toMatchObject(riskreportSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        riskreportServiceStub.find.resolves(riskreportSample);
        const wrapper = shallowMount(RiskreportUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
