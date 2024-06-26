import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ProjectHumanresourcesplanService from './project-humanresourcesplan.service';
import { type IProjectHumanresourcesplan } from '@/shared/model/project-humanresourcesplan.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectHumanresourcesplan',
  setup() {
    const { t: t$ } = useI18n();
    const projectHumanresourcesplanService = inject('projectHumanresourcesplanService', () => new ProjectHumanresourcesplanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const projectHumanresourcesplans: Ref<IProjectHumanresourcesplan[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveProjectHumanresourcesplans = async () => {
      isFetching.value = true;
      try {
        const res = await projectHumanresourcesplanService().retrieve();
        projectHumanresourcesplans.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveProjectHumanresourcesplans();
    };

    onMounted(async () => {
      await retrieveProjectHumanresourcesplans();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IProjectHumanresourcesplan) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeProjectHumanresourcesplan = async () => {
      try {
        await projectHumanresourcesplanService().delete(removeId.value);
        const message = t$('jHipster0App.projectHumanresourcesplan.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveProjectHumanresourcesplans();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      projectHumanresourcesplans,
      handleSyncList,
      isFetching,
      retrieveProjectHumanresourcesplans,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeProjectHumanresourcesplan,
      t$,
    };
  },
});
