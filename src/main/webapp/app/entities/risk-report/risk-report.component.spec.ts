/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import RiskReport from './risk-report.vue';
import RiskReportService from './risk-report.service';
import AlertService from '@/shared/alert/alert.service';

type RiskReportComponentType = InstanceType<typeof RiskReport>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('RiskReport Management Component', () => {
    let riskReportServiceStub: SinonStubbedInstance<RiskReportService>;
    let mountOptions: MountingOptions<RiskReportComponentType>['global'];

    beforeEach(() => {
      riskReportServiceStub = sinon.createStubInstance<RiskReportService>(RiskReportService);
      riskReportServiceStub.retrieve.resolves({ headers: {} });

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          bModal: bModalStub as any,
          'font-awesome-icon': true,
          'b-badge': true,
          'b-button': true,
          'router-link': true,
        },
        directives: {
          'b-modal': {},
        },
        provide: {
          alertService,
          riskReportService: () => riskReportServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        riskReportServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(RiskReport, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(riskReportServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.riskReports[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: RiskReportComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(RiskReport, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        riskReportServiceStub.retrieve.reset();
        riskReportServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        riskReportServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeRiskReport();
        await comp.$nextTick(); // clear components

        // THEN
        expect(riskReportServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(riskReportServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
