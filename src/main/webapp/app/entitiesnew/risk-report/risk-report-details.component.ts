import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import RiskReportService from './risk-report.service';
import { type IRiskReport } from '@/shared/model/risk-report.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RiskReportDetails',
  setup() {
    const riskReportService = inject('riskReportService', () => new RiskReportService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const riskReport: Ref<IRiskReport> = ref({});

    const retrieveRiskReport = async riskReportId => {
      try {
        const res = await riskReportService().find(riskReportId);
        riskReport.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.riskReportId) {
      retrieveRiskReport(route.params.riskReportId);
    }

    return {
      alertService,
      riskReport,

      previousState,
      t$: useI18n().t,
    };
  },
});
