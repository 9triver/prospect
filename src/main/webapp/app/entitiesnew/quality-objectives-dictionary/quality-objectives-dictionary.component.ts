import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import QualityObjectivesDictionaryService from './quality-objectives-dictionary.service';
import { type IQualityObjectivesDictionary } from '@/shared/model/quality-objectives-dictionary.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QualityObjectivesDictionary',
  setup() {
    const { t: t$ } = useI18n();
    const qualityObjectivesDictionaryService = inject('qualityObjectivesDictionaryService', () => new QualityObjectivesDictionaryService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const qualityObjectivesDictionaries: Ref<IQualityObjectivesDictionary[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveQualityObjectivesDictionarys = async () => {
      isFetching.value = true;
      try {
        const res = await qualityObjectivesDictionaryService().retrieve();
        qualityObjectivesDictionaries.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveQualityObjectivesDictionarys();
    };

    onMounted(async () => {
      await retrieveQualityObjectivesDictionarys();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IQualityObjectivesDictionary) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeQualityObjectivesDictionary = async () => {
      try {
        await qualityObjectivesDictionaryService().delete(removeId.value);
        const message = t$('jy1App.qualityObjectivesDictionary.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveQualityObjectivesDictionarys();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      qualityObjectivesDictionaries,
      handleSyncList,
      isFetching,
      retrieveQualityObjectivesDictionarys,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeQualityObjectivesDictionary,
      t$,
    };
  },
});
