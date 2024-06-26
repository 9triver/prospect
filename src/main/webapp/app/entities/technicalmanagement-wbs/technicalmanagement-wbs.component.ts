import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import TechnicalmanagementWbsService from './technicalmanagement-wbs.service';
import { type ITechnicalmanagementWbs } from '@/shared/model/technicalmanagement-wbs.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TechnicalmanagementWbs',
  setup() {
    const { t: t$ } = useI18n();
    const technicalmanagementWbsService = inject('technicalmanagementWbsService', () => new TechnicalmanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const technicalmanagementWbs: Ref<ITechnicalmanagementWbs[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveTechnicalmanagementWbss = async () => {
      isFetching.value = true;
      try {
        const res = await technicalmanagementWbsService().retrieve();
        technicalmanagementWbs.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveTechnicalmanagementWbss();
    };

    onMounted(async () => {
      await retrieveTechnicalmanagementWbss();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ITechnicalmanagementWbs) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeTechnicalmanagementWbs = async () => {
      try {
        await technicalmanagementWbsService().delete(removeId.value);
        const message = t$('jHipster0App.technicalmanagementWbs.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveTechnicalmanagementWbss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      technicalmanagementWbs,
      handleSyncList,
      isFetching,
      retrieveTechnicalmanagementWbss,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeTechnicalmanagementWbs,
      t$,
    };
  },
});
