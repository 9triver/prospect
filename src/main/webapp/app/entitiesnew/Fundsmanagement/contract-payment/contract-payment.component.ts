import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ContractPaymentService from './contract-payment.service';
import { type IContractPayment } from '@/shared/model/contract-payment.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ContractPayment',
  setup() {
    const { t: t$ } = useI18n();
    const contractPaymentService = inject('contractPaymentService', () => new ContractPaymentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const contractPayments: Ref<IContractPayment[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveContractPayments = async () => {
      isFetching.value = true;
      try {
        const res = await contractPaymentService().retrieve();
        contractPayments.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveContractPayments();
    };

    onMounted(async () => {
      await retrieveContractPayments();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IContractPayment) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeContractPayment = async () => {
      try {
        await contractPaymentService().delete(removeId.value);
        const message = t$('jy1App.contractPayment.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveContractPayments();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      contractPayments,
      handleSyncList,
      isFetching,
      retrieveContractPayments,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeContractPayment,
      t$,
    };
  },
});
