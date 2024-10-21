import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ContractPaymentService from './contract-payment.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IContractPayment, ContractPayment } from '@/shared/model/contract-payment.model';
import { PaymentType } from '@/shared/model/enumerations/payment-type.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ContractPaymentUpdate',
  setup() {
    const contractPaymentService = inject('contractPaymentService', () => new ContractPaymentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const contractPayment: Ref<IContractPayment> = ref(new ContractPayment());
    const paymentTypeValues: Ref<string[]> = ref(Object.keys(PaymentType));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

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

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      workbagid: {},
      workbagname: {},
      contractcode: {},
      contractname: {},
      planpaymentnode: {},
      planpaymentamount: {},
      actualpaymentamount: {},
      paymenttype: {},
      financialvoucherid: {},
    };
    const v$ = useVuelidate(validationRules, contractPayment as any);
    v$.value.$validate();

    return {
      contractPaymentService,
      alertService,
      contractPayment,
      previousState,
      paymentTypeValues,
      isSaving,
      currentLanguage,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.contractPayment.id) {
        this.contractPaymentService()
          .update(this.contractPayment)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.contractPayment.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.contractPaymentService()
          .create(this.contractPayment)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.contractPayment.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
