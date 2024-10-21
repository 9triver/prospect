import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ProjectRiskService from './project-risk.service';
import { type IProjectRisk } from '@/shared/model/project-risk.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectRiskDetails',
  setup() {
    const projectRiskService = inject('projectRiskService', () => new ProjectRiskService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const projectRisk: Ref<IProjectRisk> = ref({});

    const retrieveProjectRisk = async projectRiskId => {
      try {
        const res = await projectRiskService().find(projectRiskId);
        projectRisk.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.projectRiskId) {
      retrieveProjectRisk(route.params.projectRiskId);
    }

    return {
      alertService,
      projectRisk,

      previousState,
      t$: useI18n().t,
    };
  },
});
