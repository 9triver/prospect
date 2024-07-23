import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ProjectService from './project.service';
import { type IProject } from '@/shared/model/project.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectDetails',
  setup() {
    const projectService = inject('projectService', () => new ProjectService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const project: Ref<IProject> = ref({});

    const retrieveProject = async projectId => {
      try {
        const res = await projectService().find(projectId);
        project.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.projectId) {
      retrieveProject(route.params.projectId);
    }

    return {
      alertService,
      project,

      previousState,
      t$: useI18n().t,
    };
  },
});
