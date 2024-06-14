import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import RiskmanagementService from './riskmanagement.service';
import { type IRiskmanagement } from '@/shared/model/riskmanagement.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Riskmanagement',
  setup() {
    const { t: t$ } = useI18n();
    const riskmanagementService = inject('riskmanagementService', () => new RiskmanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const riskmanagements: Ref<IRiskmanagement[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveRiskmanagements = async () => {
      isFetching.value = true;
      try {
        const res = await riskmanagementService().retrieve();
        riskmanagements.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveRiskmanagements();
    };

    onMounted(async () => {
      await retrieveRiskmanagements();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IRiskmanagement) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeRiskmanagement = async () => {
      try {
        await riskmanagementService().delete(removeId.value);
        const message = t$('jHipster3App.riskmanagement.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveRiskmanagements();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      riskmanagements,
      handleSyncList,
      isFetching,
      retrieveRiskmanagements,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeRiskmanagement,
      t$,
    };
  },
});
