import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ProgressmanagementService from './progressmanagement.service';
import { type IProgressmanagement } from '@/shared/model/progressmanagement.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Progressmanagement',
  setup() {
    const { t: t$ } = useI18n();
    const progressmanagementService = inject('progressmanagementService', () => new ProgressmanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const progressmanagements: Ref<IProgressmanagement[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveProgressmanagements = async () => {
      isFetching.value = true;
      try {
        const res = await progressmanagementService().retrieve();
        progressmanagements.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveProgressmanagements();
    };

    onMounted(async () => {
      await retrieveProgressmanagements();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IProgressmanagement) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeProgressmanagement = async () => {
      try {
        await progressmanagementService().delete(removeId.value);
        const message = t$('jHipster3App.progressmanagement.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveProgressmanagements();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      progressmanagements,
      handleSyncList,
      isFetching,
      retrieveProgressmanagements,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeProgressmanagement,
      t$,
    };
  },
});
