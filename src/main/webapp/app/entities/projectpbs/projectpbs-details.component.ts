import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ProjectpbsService from './projectpbs.service';
import { type IProjectpbs } from '@/shared/model/projectpbs.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectpbsDetails',
  setup() {
    const projectpbsService = inject('projectpbsService', () => new ProjectpbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const projectpbs: Ref<IProjectpbs> = ref({});

    const retrieveProjectpbs = async projectpbsId => {
      try {
        const res = await projectpbsService().find(projectpbsId);
        projectpbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.projectpbsId) {
      retrieveProjectpbs(route.params.projectpbsId);
    }

    return {
      alertService,
      projectpbs,

      previousState,
      t$: useI18n().t,
    };
  },
});
