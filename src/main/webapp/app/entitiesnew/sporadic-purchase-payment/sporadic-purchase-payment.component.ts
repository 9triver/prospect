import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import SporadicPurchasePaymentService from './sporadic-purchase-payment.service';
import { type ISporadicPurchasePayment } from '@/shared/model/sporadic-purchase-payment.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SporadicPurchasePayment',
  setup() {
    const { t: t$ } = useI18n();
    const sporadicPurchasePaymentService = inject('sporadicPurchasePaymentService', () => new SporadicPurchasePaymentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const sporadicPurchasePayments: Ref<ISporadicPurchasePayment[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveSporadicPurchasePayments = async () => {
      isFetching.value = true;
      try {
        const res = await sporadicPurchasePaymentService().retrieve();
        sporadicPurchasePayments.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveSporadicPurchasePayments();
    };

    onMounted(async () => {
      await retrieveSporadicPurchasePayments();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ISporadicPurchasePayment) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeSporadicPurchasePayment = async () => {
      try {
        await sporadicPurchasePaymentService().delete(removeId.value);
        const message = t$('jy1App.sporadicPurchasePayment.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveSporadicPurchasePayments();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      sporadicPurchasePayments,
      handleSyncList,
      isFetching,
      retrieveSporadicPurchasePayments,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeSporadicPurchasePayment,
      t$,
    };
  },
});
