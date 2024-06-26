import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import RiskidentificationService from './riskidentification.service';
import { type IRiskidentification } from '@/shared/model/riskidentification.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RiskidentificationDetails',
  setup() {
    const riskidentificationService = inject('riskidentificationService', () => new RiskidentificationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const riskidentification: Ref<IRiskidentification> = ref({});

    const retrieveRiskidentification = async riskidentificationId => {
      try {
        const res = await riskidentificationService().find(riskidentificationId);
        riskidentification.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.riskidentificationId) {
      retrieveRiskidentification(route.params.riskidentificationId);
    }

    return {
      alertService,
      riskidentification,

      previousState,
      t$: useI18n().t,
    };
  },
});
