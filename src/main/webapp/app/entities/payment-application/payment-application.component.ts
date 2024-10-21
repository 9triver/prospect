import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import PaymentApplicationService from './payment-application.service';
import { type IPaymentApplication } from '@/shared/model/payment-application.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PaymentApplication',
  setup() {
    const { t: t$ } = useI18n();
    const paymentApplicationService = inject('paymentApplicationService', () => new PaymentApplicationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const paymentApplications: Ref<IPaymentApplication[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrievePaymentApplications = async () => {
      isFetching.value = true;
      try {
        const res = await paymentApplicationService().retrieve();
        paymentApplications.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrievePaymentApplications();
    };

    onMounted(async () => {
      await retrievePaymentApplications();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IPaymentApplication) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removePaymentApplication = async () => {
      try {
        await paymentApplicationService().delete(removeId.value);
        const message = t$('jy1App.paymentApplication.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrievePaymentApplications();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      paymentApplications,
      handleSyncList,
      isFetching,
      retrievePaymentApplications,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removePaymentApplication,
      t$,
    };
  },
});
