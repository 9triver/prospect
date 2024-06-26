import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import RiskmanagementWbsService from './riskmanagement-wbs.service';
import { type IRiskmanagementWbs } from '@/shared/model/riskmanagement-wbs.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RiskmanagementWbs',
  setup() {
    const { t: t$ } = useI18n();
    const riskmanagementWbsService = inject('riskmanagementWbsService', () => new RiskmanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const riskmanagementWbs: Ref<IRiskmanagementWbs[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveRiskmanagementWbss = async () => {
      isFetching.value = true;
      try {
        const res = await riskmanagementWbsService().retrieve();
        riskmanagementWbs.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveRiskmanagementWbss();
    };

    onMounted(async () => {
      await retrieveRiskmanagementWbss();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IRiskmanagementWbs) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeRiskmanagementWbs = async () => {
      try {
        await riskmanagementWbsService().delete(removeId.value);
        const message = t$('jHipster0App.riskmanagementWbs.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveRiskmanagementWbss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      riskmanagementWbs,
      handleSyncList,
      isFetching,
      retrieveRiskmanagementWbss,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeRiskmanagementWbs,
      t$,
    };
  },
});
