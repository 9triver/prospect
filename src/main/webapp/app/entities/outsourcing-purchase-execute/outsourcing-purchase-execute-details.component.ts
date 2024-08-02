import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import OutsourcingPurchaseExecuteService from './outsourcing-purchase-execute.service';
import { type IOutsourcingPurchaseExecute } from '@/shared/model/outsourcing-purchase-execute.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OutsourcingPurchaseExecuteDetails',
  setup() {
    const outsourcingPurchaseExecuteService = inject('outsourcingPurchaseExecuteService', () => new OutsourcingPurchaseExecuteService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const outsourcingPurchaseExecute: Ref<IOutsourcingPurchaseExecute> = ref({});

    const retrieveOutsourcingPurchaseExecute = async outsourcingPurchaseExecuteId => {
      try {
        const res = await outsourcingPurchaseExecuteService().find(outsourcingPurchaseExecuteId);
        outsourcingPurchaseExecute.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.outsourcingPurchaseExecuteId) {
      retrieveOutsourcingPurchaseExecute(route.params.outsourcingPurchaseExecuteId);
    }

    return {
      alertService,
      outsourcingPurchaseExecute,

      previousState,
      t$: useI18n().t,
    };
  },
});
