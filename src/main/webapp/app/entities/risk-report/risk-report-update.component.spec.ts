/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RiskReportUpdate from './risk-report-update.vue';
import RiskReportService from './risk-report.service';
import AlertService from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';

type RiskReportUpdateComponentType = InstanceType<typeof RiskReportUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const riskReportSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<RiskReportUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('RiskReport Management Update Component', () => {
    let comp: RiskReportUpdateComponentType;
    let riskReportServiceStub: SinonStubbedInstance<RiskReportService>;

    beforeEach(() => {
      route = {};
      riskReportServiceStub = sinon.createStubInstance<RiskReportService>(RiskReportService);
      riskReportServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          riskReportService: () => riskReportServiceStub,
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
        const wrapper = shallowMount(RiskReportUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.riskReport = riskReportSample;
        riskReportServiceStub.update.resolves(riskReportSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(riskReportServiceStub.update.calledWith(riskReportSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        riskReportServiceStub.create.resolves(entity);
        const wrapper = shallowMount(RiskReportUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.riskReport = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(riskReportServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        riskReportServiceStub.find.resolves(riskReportSample);
        riskReportServiceStub.retrieve.resolves([riskReportSample]);

        // WHEN
        route = {
          params: {
            riskReportId: '' + riskReportSample.id,
          },
        };
        const wrapper = shallowMount(RiskReportUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.riskReport).toMatchObject(riskReportSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        riskReportServiceStub.find.resolves(riskReportSample);
        const wrapper = shallowMount(RiskReportUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
