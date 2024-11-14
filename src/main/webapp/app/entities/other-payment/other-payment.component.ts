import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import OtherPaymentService from './other-payment.service';
import { type IOtherPayment } from '@/shared/model/other-payment.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OtherPayment',
  setup() {
    const { t: t$ } = useI18n();
    const otherPaymentService = inject('otherPaymentService', () => new OtherPaymentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const otherPayments: Ref<IOtherPayment[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveOtherPayments = async () => {
      isFetching.value = true;
      try {
        const res = await otherPaymentService().retrieve();
        otherPayments.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveOtherPayments();
    };

    onMounted(async () => {
      await retrieveOtherPayments();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IOtherPayment) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeOtherPayment = async () => {
      try {
        await otherPaymentService().delete(removeId.value);
        const message = t$('jy1App.otherPayment.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveOtherPayments();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      otherPayments,
      handleSyncList,
      isFetching,
      retrieveOtherPayments,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeOtherPayment,
      t$,
    };
  },
});
