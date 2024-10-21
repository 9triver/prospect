import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import OutsourcingContractService from './outsourcing-contract.service';
import { type IOutsourcingContract } from '@/shared/model/outsourcing-contract.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OutsourcingContract',
  setup() {
    const { t: t$ } = useI18n();
    const outsourcingContractService = inject('outsourcingContractService', () => new OutsourcingContractService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const outsourcingContracts: Ref<IOutsourcingContract[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveOutsourcingContracts = async () => {
      isFetching.value = true;
      try {
        const res = await outsourcingContractService().retrieve();
        outsourcingContracts.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveOutsourcingContracts();
    };

    onMounted(async () => {
      await retrieveOutsourcingContracts();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IOutsourcingContract) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeOutsourcingContract = async () => {
      try {
        await outsourcingContractService().delete(removeId.value);
        const message = t$('jy1App.outsourcingContract.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveOutsourcingContracts();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      outsourcingContracts,
      handleSyncList,
      isFetching,
      retrieveOutsourcingContracts,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeOutsourcingContract,
      t$,
    };
  },
});
