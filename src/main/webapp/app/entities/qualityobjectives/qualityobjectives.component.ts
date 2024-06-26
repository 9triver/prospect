import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import QualityobjectivesService from './qualityobjectives.service';
import { type IQualityobjectives } from '@/shared/model/qualityobjectives.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Qualityobjectives',
  setup() {
    const { t: t$ } = useI18n();
    const qualityobjectivesService = inject('qualityobjectivesService', () => new QualityobjectivesService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const qualityobjectives: Ref<IQualityobjectives[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveQualityobjectivess = async () => {
      isFetching.value = true;
      try {
        const res = await qualityobjectivesService().retrieve();
        qualityobjectives.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveQualityobjectivess();
    };

    onMounted(async () => {
      await retrieveQualityobjectivess();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IQualityobjectives) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeQualityobjectives = async () => {
      try {
        await qualityobjectivesService().delete(removeId.value);
        const message = t$('jHipster0App.qualityobjectives.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveQualityobjectivess();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      qualityobjectives,
      handleSyncList,
      isFetching,
      retrieveQualityobjectivess,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeQualityobjectives,
      t$,
    };
  },
});
