import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import QualitymanagementService from './qualitymanagement.service';
import { type IQualitymanagement } from '@/shared/model/qualitymanagement.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Qualitymanagement',
  setup() {
    const { t: t$ } = useI18n();
    const qualitymanagementService = inject('qualitymanagementService', () => new QualitymanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const qualitymanagements: Ref<IQualitymanagement[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveQualitymanagements = async () => {
      isFetching.value = true;
      try {
        const res = await qualitymanagementService().retrieve();
        qualitymanagements.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveQualitymanagements();
    };

    onMounted(async () => {
      await retrieveQualitymanagements();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IQualitymanagement) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeQualitymanagement = async () => {
      try {
        await qualitymanagementService().delete(removeId.value);
        const message = t$('jHipster0App.qualitymanagement.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveQualitymanagements();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      qualitymanagements,
      handleSyncList,
      isFetching,
      retrieveQualitymanagements,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeQualitymanagement,
      t$,
    };
  },
});
