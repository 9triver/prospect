import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import RiskReportService from './risk-report.service';
import { type IRiskReport } from '@/shared/model/risk-report.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RiskReport',
  setup() {
    const { t: t$ } = useI18n();
    const riskReportService = inject('riskReportService', () => new RiskReportService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const riskReports: Ref<IRiskReport[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveRiskReports = async () => {
      isFetching.value = true;
      try {
        const res = await riskReportService().retrieve();
        riskReports.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveRiskReports();
    };

    onMounted(async () => {
      await retrieveRiskReports();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IRiskReport) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeRiskReport = async () => {
      try {
        await riskReportService().delete(removeId.value);
        const message = t$('jy1App.riskReport.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveRiskReports();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      riskReports,
      handleSyncList,
      isFetching,
      retrieveRiskReports,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeRiskReport,
      t$,
    };
  },
});
