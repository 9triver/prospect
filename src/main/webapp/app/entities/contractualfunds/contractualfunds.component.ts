import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ContractualfundsService from './contractualfunds.service';
import { type IContractualfunds } from '@/shared/model/contractualfunds.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Contractualfunds',
  setup() {
    const { t: t$ } = useI18n();
    const contractualfundsService = inject('contractualfundsService', () => new ContractualfundsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const contractualfunds: Ref<IContractualfunds[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveContractualfundss = async () => {
      isFetching.value = true;
      try {
        const res = await contractualfundsService().retrieve();
        contractualfunds.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveContractualfundss();
    };

    onMounted(async () => {
      await retrieveContractualfundss();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IContractualfunds) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeContractualfunds = async () => {
      try {
        await contractualfundsService().delete(removeId.value);
        const message = t$('jHipster0App.contractualfunds.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveContractualfundss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      contractualfunds,
      handleSyncList,
      isFetching,
      retrieveContractualfundss,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeContractualfunds,
      t$,
    };
  },
});
