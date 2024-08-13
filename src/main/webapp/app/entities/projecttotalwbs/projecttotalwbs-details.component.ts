import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ProjectTotalwbsService from './projecttotalwbs.service';
import { type IProjectTotalwbs } from '@/shared/model/projecttotalwbs.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectTotalwbsDetails',
  setup() {
    const projectTotalwbsService = inject('projectTotalwbsService', () => new ProjectTotalwbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const projectTotalwbs: Ref<IProjectTotalwbs> = ref({});

    const retrieveProjectTotalwbs = async projectTotalwbsId => {
      try {
        const res = await projectTotalwbsService().find(projectTotalwbsId);
        projectTotalwbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.projectTotalwbsId) {
      retrieveProjectTotalwbs(route.params.projectTotalwbsId);
    }

    return {
      alertService,
      projectTotalwbs,

      previousState,
      t$: useI18n().t,
    };
  },
});
