import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ProjectremitService from './projectremit.service';
import { type IProjectremit } from '@/shared/model/projectremit.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectremitDetails',
  setup() {
    const projectremitService = inject('projectremitService', () => new ProjectremitService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const projectremit: Ref<IProjectremit> = ref({});

    const retrieveProjectremit = async projectremitId => {
      try {
        const res = await projectremitService().find(projectremitId);
        projectremit.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.projectremitId) {
      retrieveProjectremit(route.params.projectremitId);
    }

    return {
      alertService,
      projectremit,

      previousState,
      t$: useI18n().t,
    };
  },
});
