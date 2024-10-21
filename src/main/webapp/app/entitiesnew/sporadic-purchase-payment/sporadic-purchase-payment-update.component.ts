import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import SporadicPurchasePaymentService from './sporadic-purchase-payment.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type ISporadicPurchasePayment, SporadicPurchasePayment } from '@/shared/model/sporadic-purchase-payment.model';
import { PaymentType } from '@/shared/model/enumerations/payment-type.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SporadicPurchasePaymentUpdate',
  setup() {
    const sporadicPurchasePaymentService = inject('sporadicPurchasePaymentService', () => new SporadicPurchasePaymentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const sporadicPurchasePayment: Ref<ISporadicPurchasePayment> = ref(new SporadicPurchasePayment());
    const paymentTypeValues: Ref<string[]> = ref(Object.keys(PaymentType));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveSporadicPurchasePayment = async sporadicPurchasePaymentId => {
      try {
        const res = await sporadicPurchasePaymentService().find(sporadicPurchasePaymentId);
        sporadicPurchasePayment.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.sporadicPurchasePaymentId) {
      retrieveSporadicPurchasePayment(route.params.sporadicPurchasePaymentId);
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
    const v$ = useVuelidate(validationRules, sporadicPurchasePayment as any);
    v$.value.$validate();

    return {
      sporadicPurchasePaymentService,
      alertService,
      sporadicPurchasePayment,
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
      if (this.sporadicPurchasePayment.id) {
        this.sporadicPurchasePaymentService()
          .update(this.sporadicPurchasePayment)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.sporadicPurchasePayment.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.sporadicPurchasePaymentService()
          .create(this.sporadicPurchasePayment)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.sporadicPurchasePayment.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
