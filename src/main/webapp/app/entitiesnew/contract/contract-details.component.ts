import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ContractService from './contract.service';
import { type IContract } from '@/shared/model/contract.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ContractDetails',
  setup() {
    const contractService = inject('contractService', () => new ContractService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const contract: Ref<IContract> = ref({});

    const retrieveContract = async contractId => {
      try {
        const res = await contractService().find(contractId);
        contract.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.contractId) {
      retrieveContract(route.params.contractId);
    }

    return {
      alertService,
      contract,

      previousState,
      t$: useI18n().t,
    };
  },
});
