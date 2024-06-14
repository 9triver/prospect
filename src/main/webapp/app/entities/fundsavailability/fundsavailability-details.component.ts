import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import FundsavailabilityService from './fundsavailability.service';
import { type IFundsavailability } from '@/shared/model/fundsavailability.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'FundsavailabilityDetails',
  setup() {
    const fundsavailabilityService = inject('fundsavailabilityService', () => new FundsavailabilityService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const fundsavailability: Ref<IFundsavailability> = ref({});

    const retrieveFundsavailability = async fundsavailabilityId => {
      try {
        const res = await fundsavailabilityService().find(fundsavailabilityId);
        fundsavailability.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.fundsavailabilityId) {
      retrieveFundsavailability(route.params.fundsavailabilityId);
    }

    return {
      alertService,
      fundsavailability,

      previousState,
      t$: useI18n().t,
    };
  },
});
