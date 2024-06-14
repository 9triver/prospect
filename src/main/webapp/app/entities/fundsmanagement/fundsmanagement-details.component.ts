import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import FundsmanagementService from './fundsmanagement.service';
import { type IFundsmanagement } from '@/shared/model/fundsmanagement.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'FundsmanagementDetails',
  setup() {
    const fundsmanagementService = inject('fundsmanagementService', () => new FundsmanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const fundsmanagement: Ref<IFundsmanagement> = ref({});

    const retrieveFundsmanagement = async fundsmanagementId => {
      try {
        const res = await fundsmanagementService().find(fundsmanagementId);
        fundsmanagement.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.fundsmanagementId) {
      retrieveFundsmanagement(route.params.fundsmanagementId);
    }

    return {
      alertService,
      fundsmanagement,

      previousState,
      t$: useI18n().t,
    };
  },
});
