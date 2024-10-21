import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import SharePaymentService from './share-payment.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type ISharePayment, SharePayment } from '@/shared/model/share-payment.model';
import { PaymentType } from '@/shared/model/enumerations/payment-type.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SharePaymentUpdate',
  setup() {
    const sharePaymentService = inject('sharePaymentService', () => new SharePaymentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const sharePayment: Ref<ISharePayment> = ref(new SharePayment());
    const paymentTypeValues: Ref<string[]> = ref(Object.keys(PaymentType));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveSharePayment = async sharePaymentId => {
      try {
        const res = await sharePaymentService().find(sharePaymentId);
        sharePayment.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.sharePaymentId) {
      retrieveSharePayment(route.params.sharePaymentId);
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
    const v$ = useVuelidate(validationRules, sharePayment as any);
    v$.value.$validate();

    return {
      sharePaymentService,
      alertService,
      sharePayment,
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
      if (this.sharePayment.id) {
        this.sharePaymentService()
          .update(this.sharePayment)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.sharePayment.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.sharePaymentService()
          .create(this.sharePayment)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.sharePayment.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
