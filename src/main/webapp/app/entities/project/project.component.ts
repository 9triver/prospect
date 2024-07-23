import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ProjectService from './project.service';
import { type IProject } from '@/shared/model/project.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Project',
  setup() {
    const { t: t$ } = useI18n();
    const projectService = inject('projectService', () => new ProjectService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const projects: Ref<IProject[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveProjects = async () => {
      isFetching.value = true;
      try {
        const res = await projectService().retrieve();
        projects.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveProjects();
    };

    onMounted(async () => {
      await retrieveProjects();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IProject) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeProject = async () => {
      try {
        await projectService().delete(removeId.value);
        const message = t$('jy1App.project.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveProjects();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      projects,
      handleSyncList,
      isFetching,
      retrieveProjects,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeProject,
      t$,
    };
  },
});
