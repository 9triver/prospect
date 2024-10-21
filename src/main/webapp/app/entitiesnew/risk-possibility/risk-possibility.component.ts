import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import RiskPossibilityService from './risk-possibility.service';
import { type IRiskPossibility } from '@/shared/model/risk-possibility.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RiskPossibility',
  setup() {
    const { t: t$ } = useI18n();
    const riskPossibilityService = inject('riskPossibilityService', () => new RiskPossibilityService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const riskPossibilities: Ref<IRiskPossibility[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveRiskPossibilitys = async () => {
      isFetching.value = true;
      try {
        const res = await riskPossibilityService().retrieve();
        riskPossibilities.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveRiskPossibilitys();
    };

    onMounted(async () => {
      await retrieveRiskPossibilitys();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IRiskPossibility) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeRiskPossibility = async () => {
      try {
        await riskPossibilityService().delete(removeId.value);
        const message = t$('jy1App.riskPossibility.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveRiskPossibilitys();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      riskPossibilities,
      handleSyncList,
      isFetching,
      retrieveRiskPossibilitys,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeRiskPossibility,
      t$,
    };
  },
});
