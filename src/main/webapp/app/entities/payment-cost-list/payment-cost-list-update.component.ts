import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import PaymentCostListService from './payment-cost-list.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ContractPaymentService from '@/entities/contract-payment/contract-payment.service';
import { type IContractPayment } from '@/shared/model/contract-payment.model';
import { type IPaymentCostList, PaymentCostList } from '@/shared/model/payment-cost-list.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PaymentCostListUpdate',
  setup() {
    const paymentCostListService = inject('paymentCostListService', () => new PaymentCostListService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const paymentCostList: Ref<IPaymentCostList> = ref(new PaymentCostList());

    const contractPaymentService = inject('contractPaymentService', () => new ContractPaymentService());

    const contractPayments: Ref<IContractPayment[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrievePaymentCostList = async paymentCostListId => {
      try {
        const res = await paymentCostListService().find(paymentCostListId);
        paymentCostList.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.paymentCostListId) {
      retrievePaymentCostList(route.params.paymentCostListId);
    }

    const initRelationships = () => {
      contractPaymentService()
        .retrieve()
        .then(res => {
          contractPayments.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      wbsid: {},
      wbsname: {},
      parentwbsid: {},
      unit: {},
      unitprice: {},
      number: {},
      invoicepaymentamount: {},
      borrowingpaymentamount: {},
      accountingamount: {},
      contractPayment: {},
    };
    const v$ = useVuelidate(validationRules, paymentCostList as any);
    v$.value.$validate();

    return {
      paymentCostListService,
      alertService,
      paymentCostList,
      previousState,
      isSaving,
      currentLanguage,
      contractPayments,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.paymentCostList.id) {
        this.paymentCostListService()
          .update(this.paymentCostList)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.paymentCostList.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.paymentCostListService()
          .create(this.paymentCostList)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.paymentCostList.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
