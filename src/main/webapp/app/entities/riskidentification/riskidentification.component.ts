import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import RiskidentificationService from './riskidentification.service';
import { type IRiskidentification } from '@/shared/model/riskidentification.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Riskidentification',
  setup() {
    const { t: t$ } = useI18n();
    const riskidentificationService = inject('riskidentificationService', () => new RiskidentificationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const riskidentifications: Ref<IRiskidentification[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveRiskidentifications = async () => {
      isFetching.value = true;
      try {
        const res = await riskidentificationService().retrieve();
        riskidentifications.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveRiskidentifications();
    };

    onMounted(async () => {
      await retrieveRiskidentifications();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IRiskidentification) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeRiskidentification = async () => {
      try {
        await riskidentificationService().delete(removeId.value);
        const message = t$('jHipster0App.riskidentification.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveRiskidentifications();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      riskidentifications,
      handleSyncList,
      isFetching,
      retrieveRiskidentifications,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeRiskidentification,
      t$,
    };
  },
});
