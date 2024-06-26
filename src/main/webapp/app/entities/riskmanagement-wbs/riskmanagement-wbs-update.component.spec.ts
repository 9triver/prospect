/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RiskmanagementWbsUpdate from './riskmanagement-wbs-update.vue';
import RiskmanagementWbsService from './riskmanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

import RiskidentificationService from '@/entities/riskidentification/riskidentification.service';
import RiskreportService from '@/entities/riskreport/riskreport.service';

type RiskmanagementWbsUpdateComponentType = InstanceType<typeof RiskmanagementWbsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const riskmanagementWbsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<RiskmanagementWbsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('RiskmanagementWbs Management Update Component', () => {
    let comp: RiskmanagementWbsUpdateComponentType;
    let riskmanagementWbsServiceStub: SinonStubbedInstance<RiskmanagementWbsService>;

    beforeEach(() => {
      route = {};
      riskmanagementWbsServiceStub = sinon.createStubInstance<RiskmanagementWbsService>(RiskmanagementWbsService);
      riskmanagementWbsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          riskmanagementWbsService: () => riskmanagementWbsServiceStub,
          riskidentificationService: () =>
            sinon.createStubInstance<RiskidentificationService>(RiskidentificationService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          riskreportService: () =>
            sinon.createStubInstance<RiskreportService>(RiskreportService, {
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
        const wrapper = shallowMount(RiskmanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.riskmanagementWbs = riskmanagementWbsSample;
        riskmanagementWbsServiceStub.update.resolves(riskmanagementWbsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(riskmanagementWbsServiceStub.update.calledWith(riskmanagementWbsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        riskmanagementWbsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(RiskmanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.riskmanagementWbs = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(riskmanagementWbsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        riskmanagementWbsServiceStub.find.resolves(riskmanagementWbsSample);
        riskmanagementWbsServiceStub.retrieve.resolves([riskmanagementWbsSample]);

        // WHEN
        route = {
          params: {
            riskmanagementWbsId: '' + riskmanagementWbsSample.id,
          },
        };
        const wrapper = shallowMount(RiskmanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.riskmanagementWbs).toMatchObject(riskmanagementWbsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        riskmanagementWbsServiceStub.find.resolves(riskmanagementWbsSample);
        const wrapper = shallowMount(RiskmanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
