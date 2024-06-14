import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import AnnualSecurityPlanService from './annual-security-plan.service';
import { type IAnnualSecurityPlan } from '@/shared/model/annual-security-plan.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'AnnualSecurityPlanDetails',
  setup() {
    const annualSecurityPlanService = inject('annualSecurityPlanService', () => new AnnualSecurityPlanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const annualSecurityPlan: Ref<IAnnualSecurityPlan> = ref({});

    const retrieveAnnualSecurityPlan = async annualSecurityPlanId => {
      try {
        const res = await annualSecurityPlanService().find(annualSecurityPlanId);
        annualSecurityPlan.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.annualSecurityPlanId) {
      retrieveAnnualSecurityPlan(route.params.annualSecurityPlanId);
    }

    return {
      alertService,
      annualSecurityPlan,

      previousState,
      t$: useI18n().t,
    };
  },
});
