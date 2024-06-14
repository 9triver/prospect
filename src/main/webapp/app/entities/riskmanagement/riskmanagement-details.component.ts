import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import RiskmanagementService from './riskmanagement.service';
import { type IRiskmanagement } from '@/shared/model/riskmanagement.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RiskmanagementDetails',
  setup() {
    const riskmanagementService = inject('riskmanagementService', () => new RiskmanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const riskmanagement: Ref<IRiskmanagement> = ref({});

    const retrieveRiskmanagement = async riskmanagementId => {
      try {
        const res = await riskmanagementService().find(riskmanagementId);
        riskmanagement.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.riskmanagementId) {
      retrieveRiskmanagement(route.params.riskmanagementId);
    }

    return {
      alertService,
      riskmanagement,

      previousState,
      t$: useI18n().t,
    };
  },
});
