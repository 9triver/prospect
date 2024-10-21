import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import RiskLevelService from './risk-level.service';
import { type IRiskLevel } from '@/shared/model/risk-level.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RiskLevelDetails',
  setup() {
    const riskLevelService = inject('riskLevelService', () => new RiskLevelService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const riskLevel: Ref<IRiskLevel> = ref({});

    const retrieveRiskLevel = async riskLevelId => {
      try {
        const res = await riskLevelService().find(riskLevelId);
        riskLevel.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.riskLevelId) {
      retrieveRiskLevel(route.params.riskLevelId);
    }

    return {
      alertService,
      riskLevel,

      previousState,
      t$: useI18n().t,
    };
  },
});
