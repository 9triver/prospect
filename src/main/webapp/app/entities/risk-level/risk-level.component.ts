import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import RiskLevelService from './risk-level.service';
import { type IRiskLevel } from '@/shared/model/risk-level.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RiskLevel',
  setup() {
    const { t: t$ } = useI18n();
    const riskLevelService = inject('riskLevelService', () => new RiskLevelService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const riskLevels: Ref<IRiskLevel[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveRiskLevels = async () => {
      isFetching.value = true;
      try {
        const res = await riskLevelService().retrieve();
        riskLevels.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveRiskLevels();
    };

    onMounted(async () => {
      await retrieveRiskLevels();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IRiskLevel) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeRiskLevel = async () => {
      try {
        await riskLevelService().delete(removeId.value);
        const message = t$('jy1App.riskLevel.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveRiskLevels();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      riskLevels,
      handleSyncList,
      isFetching,
      retrieveRiskLevels,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeRiskLevel,
      t$,
    };
  },
});
