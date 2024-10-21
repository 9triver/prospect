import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import TransactionPaymentService from './transaction-payment.service';
import { type ITransactionPayment } from '@/shared/model/transaction-payment.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TransactionPaymentDetails',
  setup() {
    const transactionPaymentService = inject('transactionPaymentService', () => new TransactionPaymentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const transactionPayment: Ref<ITransactionPayment> = ref({});

    const retrieveTransactionPayment = async transactionPaymentId => {
      try {
        const res = await transactionPaymentService().find(transactionPaymentId);
        transactionPayment.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.transactionPaymentId) {
      retrieveTransactionPayment(route.params.transactionPaymentId);
    }

    return {
      alertService,
      transactionPayment,

      previousState,
      t$: useI18n().t,
    };
  },
});
