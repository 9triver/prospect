import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import RiskReturnService from './risk-return.service';
import { type IRiskReturn } from '@/shared/model/risk-return.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RiskReturn',
  setup() {
    const { t: t$ } = useI18n();
    const riskReturnService = inject('riskReturnService', () => new RiskReturnService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const riskReturns: Ref<IRiskReturn[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveRiskReturns = async () => {
      isFetching.value = true;
      try {
        const res = await riskReturnService().retrieve();
        riskReturns.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveRiskReturns();
    };

    onMounted(async () => {
      await retrieveRiskReturns();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IRiskReturn) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeRiskReturn = async () => {
      try {
        await riskReturnService().delete(removeId.value);
        const message = t$('jy1App.riskReturn.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveRiskReturns();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      riskReturns,
      handleSyncList,
      isFetching,
      retrieveRiskReturns,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeRiskReturn,
      t$,
    };
  },
});
