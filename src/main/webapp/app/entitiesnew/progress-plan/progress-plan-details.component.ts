import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ProgressPlanService from './progress-plan.service';
import { type IProgressPlan } from '@/shared/model/progress-plan.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProgressPlanDetails',
  setup() {
    const progressPlanService = inject('progressPlanService', () => new ProgressPlanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const progressPlan: Ref<IProgressPlan> = ref({});

    const retrieveProgressPlan = async progressPlanId => {
      try {
        const res = await progressPlanService().find(progressPlanId);
        progressPlan.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.progressPlanId) {
      retrieveProgressPlan(route.params.progressPlanId);
    }

    return {
      alertService,
      progressPlan,

      previousState,
      t$: useI18n().t,
    };
  },
});
