import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ProjectTotalwbsService from './projecttotalwbs.service';
import { type IProjectTotalwbs } from '@/shared/model/projecttotalwbs.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectTotalwbs',
  setup() {
    const { t: t$ } = useI18n();
    const projectTotalwbsService = inject('projectTotalwbsService', () => new ProjectTotalwbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const projectTotalwbs: Ref<IProjectTotalwbs[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveProjectTotalwbss = async () => {
      isFetching.value = true;
      try {
        const res = await projectTotalwbsService().retrieve();
        projectTotalwbs.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveProjectTotalwbss();
    };

    onMounted(async () => {
      await retrieveProjectTotalwbss();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IProjectTotalwbs) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeProjectTotalwbs = async () => {
      try {
        await projectTotalwbsService().delete(removeId.value);
        const message = t$('jy1App.projectTotalwbs.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveProjectTotalwbss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      projectTotalwbs,
      handleSyncList,
      isFetching,
      retrieveProjectTotalwbss,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeProjectTotalwbs,
      t$,
    };
  },
});
