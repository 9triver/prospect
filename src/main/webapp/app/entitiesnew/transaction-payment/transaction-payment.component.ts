import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import TransactionPaymentService from './transaction-payment.service';
import { type ITransactionPayment } from '@/shared/model/transaction-payment.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TransactionPayment',
  setup() {
    const { t: t$ } = useI18n();
    const transactionPaymentService = inject('transactionPaymentService', () => new TransactionPaymentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const transactionPayments: Ref<ITransactionPayment[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveTransactionPayments = async () => {
      isFetching.value = true;
      try {
        const res = await transactionPaymentService().retrieve();
        transactionPayments.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveTransactionPayments();
    };

    onMounted(async () => {
      await retrieveTransactionPayments();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ITransactionPayment) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeTransactionPayment = async () => {
      try {
        await transactionPaymentService().delete(removeId.value);
        const message = t$('jy1App.transactionPayment.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveTransactionPayments();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      transactionPayments,
      handleSyncList,
      isFetching,
      retrieveTransactionPayments,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeTransactionPayment,
      t$,
    };
  },
});
