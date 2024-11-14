import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import PaymentCostListService from './payment-cost-list.service';
import { type IPaymentCostList } from '@/shared/model/payment-cost-list.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PaymentCostListDetails',
  setup() {
    const paymentCostListService = inject('paymentCostListService', () => new PaymentCostListService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const paymentCostList: Ref<IPaymentCostList> = ref({});

    const retrievePaymentCostList = async paymentCostListId => {
      try {
        const res = await paymentCostListService().find(paymentCostListId);
        paymentCostList.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.paymentCostListId) {
      retrievePaymentCostList(route.params.paymentCostListId);
    }

    return {
      alertService,
      paymentCostList,

      previousState,
      t$: useI18n().t,
    };
  },
});
