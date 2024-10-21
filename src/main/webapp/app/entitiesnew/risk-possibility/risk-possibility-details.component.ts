import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import RiskPossibilityService from './risk-possibility.service';
import { type IRiskPossibility } from '@/shared/model/risk-possibility.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RiskPossibilityDetails',
  setup() {
    const riskPossibilityService = inject('riskPossibilityService', () => new RiskPossibilityService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const riskPossibility: Ref<IRiskPossibility> = ref({});

    const retrieveRiskPossibility = async riskPossibilityId => {
      try {
        const res = await riskPossibilityService().find(riskPossibilityId);
        riskPossibility.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.riskPossibilityId) {
      retrieveRiskPossibility(route.params.riskPossibilityId);
    }

    return {
      alertService,
      riskPossibility,

      previousState,
      t$: useI18n().t,
    };
  },
});
