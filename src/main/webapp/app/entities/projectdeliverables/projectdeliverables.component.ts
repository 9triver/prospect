import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ProjectdeliverablesService from './projectdeliverables.service';
import { type IProjectdeliverables } from '@/shared/model/projectdeliverables.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Projectdeliverables',
  setup() {
    const { t: t$ } = useI18n();
    const projectdeliverablesService = inject('projectdeliverablesService', () => new ProjectdeliverablesService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const projectdeliverables: Ref<IProjectdeliverables[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveProjectdeliverabless = async () => {
      isFetching.value = true;
      try {
        const res = await projectdeliverablesService().retrieve();
        projectdeliverables.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveProjectdeliverabless();
    };

    onMounted(async () => {
      await retrieveProjectdeliverabless();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IProjectdeliverables) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeProjectdeliverables = async () => {
      try {
        await projectdeliverablesService().delete(removeId.value);
        const message = t$('jy1App.projectdeliverables.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveProjectdeliverabless();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      projectdeliverables,
      handleSyncList,
      isFetching,
      retrieveProjectdeliverabless,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeProjectdeliverables,
      t$,
    };
  },
});
