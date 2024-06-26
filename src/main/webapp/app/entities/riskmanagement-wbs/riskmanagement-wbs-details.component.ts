import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import RiskmanagementWbsService from './riskmanagement-wbs.service';
import { type IRiskmanagementWbs } from '@/shared/model/riskmanagement-wbs.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RiskmanagementWbsDetails',
  setup() {
    const riskmanagementWbsService = inject('riskmanagementWbsService', () => new RiskmanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const riskmanagementWbs: Ref<IRiskmanagementWbs> = ref({});

    const retrieveRiskmanagementWbs = async riskmanagementWbsId => {
      try {
        const res = await riskmanagementWbsService().find(riskmanagementWbsId);
        riskmanagementWbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.riskmanagementWbsId) {
      retrieveRiskmanagementWbs(route.params.riskmanagementWbsId);
    }

    return {
      alertService,
      riskmanagementWbs,

      previousState,
      t$: useI18n().t,
    };
  },
});
