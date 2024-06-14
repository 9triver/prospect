import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ProjectSecrecyService from './project-secrecy.service';
import { type IProjectSecrecy } from '@/shared/model/project-secrecy.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectSecrecyDetails',
  setup() {
    const projectSecrecyService = inject('projectSecrecyService', () => new ProjectSecrecyService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const projectSecrecy: Ref<IProjectSecrecy> = ref({});

    const retrieveProjectSecrecy = async projectSecrecyId => {
      try {
        const res = await projectSecrecyService().find(projectSecrecyId);
        projectSecrecy.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.projectSecrecyId) {
      retrieveProjectSecrecy(route.params.projectSecrecyId);
    }

    return {
      alertService,
      projectSecrecy,

      previousState,
      t$: useI18n().t,
    };
  },
});
