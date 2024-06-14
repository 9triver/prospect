import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ContractualfundsService from './contractualfunds.service';
import { type IContractualfunds } from '@/shared/model/contractualfunds.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ContractualfundsDetails',
  setup() {
    const contractualfundsService = inject('contractualfundsService', () => new ContractualfundsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const contractualfunds: Ref<IContractualfunds> = ref({});

    const retrieveContractualfunds = async contractualfundsId => {
      try {
        const res = await contractualfundsService().find(contractualfundsId);
        contractualfunds.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.contractualfundsId) {
      retrieveContractualfunds(route.params.contractualfundsId);
    }

    return {
      alertService,
      contractualfunds,

      previousState,
      t$: useI18n().t,
    };
  },
});
