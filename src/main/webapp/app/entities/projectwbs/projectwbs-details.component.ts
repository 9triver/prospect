import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ProjectwbsService from './projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectwbsDetails',
  setup() {
    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const projectwbs: Ref<IProjectwbs> = ref({});

    const retrieveProjectwbs = async projectwbsId => {
      try {
        const res = await projectwbsService().find(projectwbsId);
        projectwbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.projectwbsId) {
      retrieveProjectwbs(route.params.projectwbsId);
    }

    return {
      alertService,
      projectwbs,

      previousState,
      t$: useI18n().t,
    };
  },
});
