import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import RiskReturnService from './risk-return.service';
import { type IRiskReturn } from '@/shared/model/risk-return.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RiskReturnDetails',
  setup() {
    const riskReturnService = inject('riskReturnService', () => new RiskReturnService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const riskReturn: Ref<IRiskReturn> = ref({});

    const retrieveRiskReturn = async riskReturnId => {
      try {
        const res = await riskReturnService().find(riskReturnId);
        riskReturn.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.riskReturnId) {
      retrieveRiskReturn(route.params.riskReturnId);
    }

    return {
      alertService,
      riskReturn,

      previousState,
      t$: useI18n().t,
    };
  },
});
