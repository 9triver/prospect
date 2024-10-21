import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import SharePaymentService from './share-payment.service';
import { type ISharePayment } from '@/shared/model/share-payment.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SharePayment',
  setup() {
    const { t: t$ } = useI18n();
    const sharePaymentService = inject('sharePaymentService', () => new SharePaymentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const sharePayments: Ref<ISharePayment[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveSharePayments = async () => {
      isFetching.value = true;
      try {
        const res = await sharePaymentService().retrieve();
        sharePayments.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveSharePayments();
    };

    onMounted(async () => {
      await retrieveSharePayments();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ISharePayment) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeSharePayment = async () => {
      try {
        await sharePaymentService().delete(removeId.value);
        const message = t$('jy1App.sharePayment.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveSharePayments();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      sharePayments,
      handleSyncList,
      isFetching,
      retrieveSharePayments,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeSharePayment,
      t$,
    };
  },
});
