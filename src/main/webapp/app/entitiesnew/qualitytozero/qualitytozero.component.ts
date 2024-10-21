import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import QualitytozeroService from './qualitytozero.service';
import { type IQualitytozero } from '@/shared/model/qualitytozero.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Qualitytozero',
  setup() {
    const { t: t$ } = useI18n();
    const qualitytozeroService = inject('qualitytozeroService', () => new QualitytozeroService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const qualitytozeros: Ref<IQualitytozero[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveQualitytozeros = async () => {
      isFetching.value = true;
      try {
        const res = await qualitytozeroService().retrieve();
        qualitytozeros.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveQualitytozeros();
    };

    onMounted(async () => {
      await retrieveQualitytozeros();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IQualitytozero) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeQualitytozero = async () => {
      try {
        await qualitytozeroService().delete(removeId.value);
        const message = t$('jy1App.qualitytozero.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveQualitytozeros();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      qualitytozeros,
      handleSyncList,
      isFetching,
      retrieveQualitytozeros,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeQualitytozero,
      t$,
    };
  },
});
