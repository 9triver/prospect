import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import QualityReturnsService from './quality-returns.service';
import { type IQualityReturns } from '@/shared/model/quality-returns.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QualityReturns',
  setup() {
    const { t: t$ } = useI18n();
    const qualityReturnsService = inject('qualityReturnsService', () => new QualityReturnsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const qualityReturns: Ref<IQualityReturns[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveQualityReturnss = async () => {
      isFetching.value = true;
      try {
        const res = await qualityReturnsService().retrieve();
        qualityReturns.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveQualityReturnss();
    };

    onMounted(async () => {
      await retrieveQualityReturnss();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IQualityReturns) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeQualityReturns = async () => {
      try {
        await qualityReturnsService().delete(removeId.value);
        const message = t$('jy1App.qualityReturns.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveQualityReturnss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      qualityReturns,
      handleSyncList,
      isFetching,
      retrieveQualityReturnss,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeQualityReturns,
      t$,
    };
  },
});
