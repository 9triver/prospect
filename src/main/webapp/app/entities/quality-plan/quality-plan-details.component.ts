import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import QualityPlanService from './quality-plan.service';
import { type IQualityPlan } from '@/shared/model/quality-plan.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QualityPlanDetails',
  setup() {
    const qualityPlanService = inject('qualityPlanService', () => new QualityPlanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const qualityPlan: Ref<IQualityPlan> = ref({});

    const retrieveQualityPlan = async qualityPlanId => {
      try {
        const res = await qualityPlanService().find(qualityPlanId);
        qualityPlan.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.qualityPlanId) {
      retrieveQualityPlan(route.params.qualityPlanId);
    }

    return {
      alertService,
      qualityPlan,

      previousState,
      t$: useI18n().t,
    };
  },
});
