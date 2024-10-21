import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import WorkbagService from './workbag.service';
import { type IWorkbag } from '@/shared/model/workbag.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Workbag',
  setup() {
    const { t: t$ } = useI18n();
    const workbagService = inject('workbagService', () => new WorkbagService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const workbags: Ref<IWorkbag[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveWorkbags = async () => {
      isFetching.value = true;
      try {
        const res = await workbagService().retrieve();
        workbags.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveWorkbags();
    };

    onMounted(async () => {
      await retrieveWorkbags();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IWorkbag) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeWorkbag = async () => {
      try {
        await workbagService().delete(removeId.value);
        const message = t$('jy1App.workbag.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveWorkbags();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      workbags,
      handleSyncList,
      isFetching,
      retrieveWorkbags,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeWorkbag,
      t$,
    };
  },
});
