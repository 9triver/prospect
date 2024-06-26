import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import QualitymanagementWbsService from './qualitymanagement-wbs.service';
import { type IQualitymanagementWbs } from '@/shared/model/qualitymanagement-wbs.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QualitymanagementWbs',
  setup() {
    const { t: t$ } = useI18n();
    const qualitymanagementWbsService = inject('qualitymanagementWbsService', () => new QualitymanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const qualitymanagementWbs: Ref<IQualitymanagementWbs[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveQualitymanagementWbss = async () => {
      isFetching.value = true;
      try {
        const res = await qualitymanagementWbsService().retrieve();
        qualitymanagementWbs.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveQualitymanagementWbss();
    };

    onMounted(async () => {
      await retrieveQualitymanagementWbss();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IQualitymanagementWbs) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeQualitymanagementWbs = async () => {
      try {
        await qualitymanagementWbsService().delete(removeId.value);
        const message = t$('jHipster0App.qualitymanagementWbs.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveQualitymanagementWbss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      qualitymanagementWbs,
      handleSyncList,
      isFetching,
      retrieveQualitymanagementWbss,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeQualitymanagementWbs,
      t$,
    };
  },
});
