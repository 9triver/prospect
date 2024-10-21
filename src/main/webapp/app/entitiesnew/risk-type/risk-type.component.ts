import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import RiskTypeService from './risk-type.service';
import { type IRiskType } from '@/shared/model/risk-type.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RiskType',
  setup() {
    const { t: t$ } = useI18n();
    const riskTypeService = inject('riskTypeService', () => new RiskTypeService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const riskTypes: Ref<IRiskType[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveRiskTypes = async () => {
      isFetching.value = true;
      try {
        const res = await riskTypeService().retrieve();
        riskTypes.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveRiskTypes();
    };

    onMounted(async () => {
      await retrieveRiskTypes();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IRiskType) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeRiskType = async () => {
      try {
        await riskTypeService().delete(removeId.value);
        const message = t$('jy1App.riskType.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveRiskTypes();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      riskTypes,
      handleSyncList,
      isFetching,
      retrieveRiskTypes,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeRiskType,
      t$,
    };
  },
});
