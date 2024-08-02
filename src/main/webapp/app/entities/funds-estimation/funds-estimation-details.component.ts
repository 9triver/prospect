import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import FundsEstimationService from './funds-estimation.service';
import { type IFundsEstimation } from '@/shared/model/funds-estimation.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'FundsEstimationDetails',
  setup() {
    const fundsEstimationService = inject('fundsEstimationService', () => new FundsEstimationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const fundsEstimation: Ref<IFundsEstimation> = ref({});

    const retrieveFundsEstimation = async fundsEstimationId => {
      try {
        const res = await fundsEstimationService().find(fundsEstimationId);
        fundsEstimation.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.fundsEstimationId) {
      retrieveFundsEstimation(route.params.fundsEstimationId);
    }

    return {
      alertService,
      fundsEstimation,

      previousState,
      t$: useI18n().t,
    };
  },
});
