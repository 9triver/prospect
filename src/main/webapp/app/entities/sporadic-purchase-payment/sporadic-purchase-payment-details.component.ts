import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import SporadicPurchasePaymentService from './sporadic-purchase-payment.service';
import { type ISporadicPurchasePayment } from '@/shared/model/sporadic-purchase-payment.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SporadicPurchasePaymentDetails',
  setup() {
    const sporadicPurchasePaymentService = inject('sporadicPurchasePaymentService', () => new SporadicPurchasePaymentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const sporadicPurchasePayment: Ref<ISporadicPurchasePayment> = ref({});

    const retrieveSporadicPurchasePayment = async sporadicPurchasePaymentId => {
      try {
        const res = await sporadicPurchasePaymentService().find(sporadicPurchasePaymentId);
        sporadicPurchasePayment.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.sporadicPurchasePaymentId) {
      retrieveSporadicPurchasePayment(route.params.sporadicPurchasePaymentId);
    }

    return {
      alertService,
      sporadicPurchasePayment,

      previousState,
      t$: useI18n().t,
    };
  },
});
