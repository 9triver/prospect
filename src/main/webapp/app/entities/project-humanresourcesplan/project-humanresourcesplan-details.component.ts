import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ProjectHumanresourcesplanService from './project-humanresourcesplan.service';
import { type IProjectHumanresourcesplan } from '@/shared/model/project-humanresourcesplan.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectHumanresourcesplanDetails',
  setup() {
    const projectHumanresourcesplanService = inject('projectHumanresourcesplanService', () => new ProjectHumanresourcesplanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const projectHumanresourcesplan: Ref<IProjectHumanresourcesplan> = ref({});

    const retrieveProjectHumanresourcesplan = async projectHumanresourcesplanId => {
      try {
        const res = await projectHumanresourcesplanService().find(projectHumanresourcesplanId);
        projectHumanresourcesplan.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.projectHumanresourcesplanId) {
      retrieveProjectHumanresourcesplan(route.params.projectHumanresourcesplanId);
    }

    return {
      alertService,
      projectHumanresourcesplan,

      previousState,
      t$: useI18n().t,
    };
  },
});
