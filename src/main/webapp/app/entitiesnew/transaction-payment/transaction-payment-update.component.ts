import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import TransactionPaymentService from './transaction-payment.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type ITransactionPayment, TransactionPayment } from '@/shared/model/transaction-payment.model';
import { PaymentType } from '@/shared/model/enumerations/payment-type.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TransactionPaymentUpdate',
  setup() {
    const transactionPaymentService = inject('transactionPaymentService', () => new TransactionPaymentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const transactionPayment: Ref<ITransactionPayment> = ref(new TransactionPayment());
    const paymentTypeValues: Ref<string[]> = ref(Object.keys(PaymentType));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveTransactionPayment = async transactionPaymentId => {
      try {
        const res = await transactionPaymentService().find(transactionPaymentId);
        transactionPayment.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.transactionPaymentId) {
      retrieveTransactionPayment(route.params.transactionPaymentId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      planpaymentnode: {},
      planpaymentamount: {},
      actualpaymentamount: {},
      paymenttype: {},
      financialvoucherid: {},
    };
    const v$ = useVuelidate(validationRules, transactionPayment as any);
    v$.value.$validate();

    return {
      transactionPaymentService,
      alertService,
      transactionPayment,
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
      if (this.transactionPayment.id) {
        this.transactionPaymentService()
          .update(this.transactionPayment)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.transactionPayment.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.transactionPaymentService()
          .create(this.transactionPayment)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.transactionPayment.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
