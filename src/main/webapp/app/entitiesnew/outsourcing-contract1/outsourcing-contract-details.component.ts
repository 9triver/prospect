import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import OutsourcingContractService from './outsourcing-contract.service';
import { type IOutsourcingContract } from '@/shared/model/outsourcing-contract.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OutsourcingContractDetails',
  setup() {
    const outsourcingContractService = inject('outsourcingContractService', () => new OutsourcingContractService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const outsourcingContract: Ref<IOutsourcingContract> = ref({});

    const retrieveOutsourcingContract = async outsourcingContractId => {
      try {
        const res = await outsourcingContractService().find(outsourcingContractId);
        outsourcingContract.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.outsourcingContractId) {
      retrieveOutsourcingContract(route.params.outsourcingContractId);
    }

    return {
      alertService,
      outsourcingContract,

      previousState,
      t$: useI18n().t,
    };
  },
});
