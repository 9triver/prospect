import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import RiskTypeService from './risk-type.service';
import { type IRiskType } from '@/shared/model/risk-type.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RiskTypeDetails',
  setup() {
    const riskTypeService = inject('riskTypeService', () => new RiskTypeService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const riskType: Ref<IRiskType> = ref({});

    const retrieveRiskType = async riskTypeId => {
      try {
        const res = await riskTypeService().find(riskTypeId);
        riskType.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.riskTypeId) {
      retrieveRiskType(route.params.riskTypeId);
    }

    return {
      alertService,
      riskType,

      previousState,
      t$: useI18n().t,
    };
  },
});
