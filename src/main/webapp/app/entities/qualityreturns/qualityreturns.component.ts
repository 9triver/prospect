import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import QualityreturnsService from './qualityreturns.service';
import { type IQualityreturns } from '@/shared/model/qualityreturns.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Qualityreturns',
  setup() {
    const { t: t$ } = useI18n();
    const qualityreturnsService = inject('qualityreturnsService', () => new QualityreturnsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const qualityreturns: Ref<IQualityreturns[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveQualityreturnss = async () => {
      isFetching.value = true;
      try {
        const res = await qualityreturnsService().retrieve();
        qualityreturns.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveQualityreturnss();
    };

    onMounted(async () => {
      await retrieveQualityreturnss();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IQualityreturns) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeQualityreturns = async () => {
      try {
        await qualityreturnsService().delete(removeId.value);
        const message = t$('jHipster3App.qualityreturns.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveQualityreturnss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      qualityreturns,
      handleSyncList,
      isFetching,
      retrieveQualityreturnss,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeQualityreturns,
      t$,
    };
  },
});
