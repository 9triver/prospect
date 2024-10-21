import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import QualityObjectivesService from './quality-objectives.service';
import { type IQualityObjectives } from '@/shared/model/quality-objectives.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QualityObjectives',
  setup() {
    const { t: t$ } = useI18n();
    const qualityObjectivesService = inject('qualityObjectivesService', () => new QualityObjectivesService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const qualityObjectives: Ref<IQualityObjectives[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveQualityObjectivess = async () => {
      isFetching.value = true;
      try {
        const res = await qualityObjectivesService().retrieve();
        qualityObjectives.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveQualityObjectivess();
    };

    onMounted(async () => {
      await retrieveQualityObjectivess();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IQualityObjectives) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeQualityObjectives = async () => {
      try {
        await qualityObjectivesService().delete(removeId.value);
        const message = t$('jy1App.qualityObjectives.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveQualityObjectivess();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      qualityObjectives,
      handleSyncList,
      isFetching,
      retrieveQualityObjectivess,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeQualityObjectives,
      t$,
    };
  },
});
