import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ProjectwbsService from './projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Projectwbs',
  setup() {
    const { t: t$ } = useI18n();
    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const projectwbs: Ref<IProjectwbs[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveProjectwbss = async () => {
      isFetching.value = true;
      try {
        const res = await projectwbsService().retrieve();
        projectwbs.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveProjectwbss();
    };

    onMounted(async () => {
      await retrieveProjectwbss();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IProjectwbs) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeProjectwbs = async () => {
      try {
        await projectwbsService().delete(removeId.value);
        const message = t$('jy1App.projectwbs.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveProjectwbss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      projectwbs,
      handleSyncList,
      isFetching,
      retrieveProjectwbss,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeProjectwbs,
      t$,
    };
  },
});
