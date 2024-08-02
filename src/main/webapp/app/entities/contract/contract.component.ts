import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ContractService from './contract.service';
import { type IContract } from '@/shared/model/contract.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Contract',
  setup() {
    const { t: t$ } = useI18n();
    const contractService = inject('contractService', () => new ContractService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const contracts: Ref<IContract[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveContracts = async () => {
      isFetching.value = true;
      try {
        const res = await contractService().retrieve();
        contracts.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveContracts();
    };

    onMounted(async () => {
      await retrieveContracts();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IContract) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeContract = async () => {
      try {
        await contractService().delete(removeId.value);
        const message = t$('jy1App.contract.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveContracts();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      contracts,
      handleSyncList,
      isFetching,
      retrieveContracts,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeContract,
      t$,
    };
  },
});
