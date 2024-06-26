import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import FundsmanagementWbsService from './fundsmanagement-wbs.service';
import { type IFundsmanagementWbs } from '@/shared/model/fundsmanagement-wbs.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'FundsmanagementWbsDetails',
  setup() {
    const fundsmanagementWbsService = inject('fundsmanagementWbsService', () => new FundsmanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const fundsmanagementWbs: Ref<IFundsmanagementWbs> = ref({});

    const retrieveFundsmanagementWbs = async fundsmanagementWbsId => {
      try {
        const res = await fundsmanagementWbsService().find(fundsmanagementWbsId);
        fundsmanagementWbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.fundsmanagementWbsId) {
      retrieveFundsmanagementWbs(route.params.fundsmanagementWbsId);
    }

    return {
      alertService,
      fundsmanagementWbs,

      previousState,
      t$: useI18n().t,
    };
  },
});
