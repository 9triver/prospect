import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ContractPaymentService from './contract-payment.service';
import { type IContractPayment } from '@/shared/model/contract-payment.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ContractPaymentDetails',
  setup() {
    const contractPaymentService = inject('contractPaymentService', () => new ContractPaymentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const contractPayment: Ref<IContractPayment> = ref({});

    const retrieveContractPayment = async contractPaymentId => {
      try {
        const res = await contractPaymentService().find(contractPaymentId);
        contractPayment.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.contractPaymentId) {
      retrieveContractPayment(route.params.contractPaymentId);
    }

    return {
      alertService,
      contractPayment,

      previousState,
      t$: useI18n().t,
    };
  },
});
