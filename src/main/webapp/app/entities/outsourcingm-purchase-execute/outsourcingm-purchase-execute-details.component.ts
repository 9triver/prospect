import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import OutsourcingmPurchaseExecuteService from './outsourcingm-purchase-execute.service';
import { type IOutsourcingmPurchaseExecute } from '@/shared/model/outsourcingm-purchase-execute.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OutsourcingmPurchaseExecuteDetails',
  setup() {
    const outsourcingmPurchaseExecuteService = inject('outsourcingmPurchaseExecuteService', () => new OutsourcingmPurchaseExecuteService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const outsourcingmPurchaseExecute: Ref<IOutsourcingmPurchaseExecute> = ref({});

    const retrieveOutsourcingmPurchaseExecute = async outsourcingmPurchaseExecuteId => {
      try {
        const res = await outsourcingmPurchaseExecuteService().find(outsourcingmPurchaseExecuteId);
        outsourcingmPurchaseExecute.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.outsourcingmPurchaseExecuteId) {
      retrieveOutsourcingmPurchaseExecute(route.params.outsourcingmPurchaseExecuteId);
    }

    return {
      alertService,
      outsourcingmPurchaseExecute,

      previousState,
      t$: useI18n().t,
    };
  },
});
