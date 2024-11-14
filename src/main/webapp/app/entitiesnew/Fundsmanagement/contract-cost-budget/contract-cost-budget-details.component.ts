import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ContractCostBudgetService from './contract-cost-budget.service';
import { type IContractCostBudget } from '@/shared/model/contract-cost-budget.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ContractCostBudgetDetails',
  setup() {
    const contractCostBudgetService = inject('contractCostBudgetService', () => new ContractCostBudgetService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const contractCostBudget: Ref<IContractCostBudget> = ref({});

    const retrieveContractCostBudget = async contractCostBudgetId => {
      try {
        const res = await contractCostBudgetService().find(contractCostBudgetId);
        contractCostBudget.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.contractCostBudgetId) {
      retrieveContractCostBudget(route.params.contractCostBudgetId);
    }

    return {
      alertService,
      contractCostBudget,

      previousState,
      t$: useI18n().t,
    };
  },
});
