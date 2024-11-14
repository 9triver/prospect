import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ContractCostBudgetService from './contract-cost-budget.service';
import { type IContractCostBudget } from '@/shared/model/contract-cost-budget.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ContractCostBudget',
  setup() {
    const { t: t$ } = useI18n();
    const contractCostBudgetService = inject('contractCostBudgetService', () => new ContractCostBudgetService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const contractCostBudgets: Ref<IContractCostBudget[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveContractCostBudgets = async () => {
      isFetching.value = true;
      try {
        const res = await contractCostBudgetService().retrieve();
        contractCostBudgets.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveContractCostBudgets();
    };

    onMounted(async () => {
      await retrieveContractCostBudgets();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IContractCostBudget) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeContractCostBudget = async () => {
      try {
        await contractCostBudgetService().delete(removeId.value);
        const message = t$('jy1App.contractCostBudget.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveContractCostBudgets();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      contractCostBudgets,
      handleSyncList,
      isFetching,
      retrieveContractCostBudgets,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeContractCostBudget,
      t$,
    };
  },
});
