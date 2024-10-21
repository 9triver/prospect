import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ProjectdeliverablesService from './projectdeliverables.service';
import { type IProjectdeliverables } from '@/shared/model/projectdeliverables.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectdeliverablesDetails',
  setup() {
    const projectdeliverablesService = inject('projectdeliverablesService', () => new ProjectdeliverablesService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const projectdeliverables: Ref<IProjectdeliverables> = ref({});

    const retrieveProjectdeliverables = async projectdeliverablesId => {
      try {
        const res = await projectdeliverablesService().find(projectdeliverablesId);
        projectdeliverables.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.projectdeliverablesId) {
      retrieveProjectdeliverables(route.params.projectdeliverablesId);
    }

    return {
      alertService,
      projectdeliverables,

      previousState,
      t$: useI18n().t,
    };
  },
});
