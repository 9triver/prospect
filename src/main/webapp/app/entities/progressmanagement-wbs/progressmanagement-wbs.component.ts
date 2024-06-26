import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ProgressmanagementWbsService from './progressmanagement-wbs.service';
import { type IProgressmanagementWbs } from '@/shared/model/progressmanagement-wbs.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProgressmanagementWbs',
  setup() {
    const { t: t$ } = useI18n();
    const progressmanagementWbsService = inject('progressmanagementWbsService', () => new ProgressmanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const progressmanagementWbs: Ref<IProgressmanagementWbs[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveProgressmanagementWbss = async () => {
      isFetching.value = true;
      try {
        const res = await progressmanagementWbsService().retrieve();
        progressmanagementWbs.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveProgressmanagementWbss();
    };

    onMounted(async () => {
      await retrieveProgressmanagementWbss();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IProgressmanagementWbs) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeProgressmanagementWbs = async () => {
      try {
        await progressmanagementWbsService().delete(removeId.value);
        const message = t$('jHipster0App.progressmanagementWbs.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveProgressmanagementWbss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      progressmanagementWbs,
      handleSyncList,
      isFetching,
      retrieveProgressmanagementWbss,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeProgressmanagementWbs,
      t$,
    };
  },
});
