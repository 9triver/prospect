import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import TechnicalmanagementService from './technicalmanagement.service';
import { type ITechnicalmanagement } from '@/shared/model/technicalmanagement.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Technicalmanagement',
  setup() {
    const { t: t$ } = useI18n();
    const technicalmanagementService = inject('technicalmanagementService', () => new TechnicalmanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const technicalmanagements: Ref<ITechnicalmanagement[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveTechnicalmanagements = async () => {
      isFetching.value = true;
      try {
        const res = await technicalmanagementService().retrieve();
        technicalmanagements.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveTechnicalmanagements();
    };

    onMounted(async () => {
      await retrieveTechnicalmanagements();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ITechnicalmanagement) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeTechnicalmanagement = async () => {
      try {
        await technicalmanagementService().delete(removeId.value);
        const message = t$('jHipster0App.technicalmanagement.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveTechnicalmanagements();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      technicalmanagements,
      handleSyncList,
      isFetching,
      retrieveTechnicalmanagements,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeTechnicalmanagement,
      t$,
    };
  },
});
