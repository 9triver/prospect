import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import FundSourceListService from './fund-source-list.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ContractService from '@/entities/contract/contract.service';
import { type IContract } from '@/shared/model/contract.model';
import TransactionPaymentService from '@/entities/transaction-payment/transaction-payment.service';
import { type ITransactionPayment } from '@/shared/model/transaction-payment.model';
import SporadicPurchasePaymentService from '@/entities/sporadic-purchase-payment/sporadic-purchase-payment.service';
import { type ISporadicPurchasePayment } from '@/shared/model/sporadic-purchase-payment.model';
import SharePaymentService from '@/entities/share-payment/share-payment.service';
import { type ISharePayment } from '@/shared/model/share-payment.model';
import ContractPaymentService from '@/entities/contract-payment/contract-payment.service';
import { type IContractPayment } from '@/shared/model/contract-payment.model';
import { type IFundSourceList, FundSourceList } from '@/shared/model/fund-source-list.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'FundSourceListUpdate',
  setup() {
    const fundSourceListService = inject('fundSourceListService', () => new FundSourceListService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const fundSourceList: Ref<IFundSourceList> = ref(new FundSourceList());

    const contractService = inject('contractService', () => new ContractService());

    const contracts: Ref<IContract[]> = ref([]);

    const transactionPaymentService = inject('transactionPaymentService', () => new TransactionPaymentService());

    const transactionPayments: Ref<ITransactionPayment[]> = ref([]);

    const sporadicPurchasePaymentService = inject('sporadicPurchasePaymentService', () => new SporadicPurchasePaymentService());

    const sporadicPurchasePayments: Ref<ISporadicPurchasePayment[]> = ref([]);

    const sharePaymentService = inject('sharePaymentService', () => new SharePaymentService());

    const sharePayments: Ref<ISharePayment[]> = ref([]);

    const contractPaymentService = inject('contractPaymentService', () => new ContractPaymentService());

    const contractPayments: Ref<IContractPayment[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveFundSourceList = async fundSourceListId => {
      try {
        const res = await fundSourceListService().find(fundSourceListId);
        fundSourceList.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.fundSourceListId) {
      retrieveFundSourceList(route.params.fundSourceListId);
    }

    const initRelationships = () => {
      contractService()
        .retrieve()
        .then(res => {
          contracts.value = res.data;
        });
      transactionPaymentService()
        .retrieve()
        .then(res => {
          transactionPayments.value = res.data;
        });
      sporadicPurchasePaymentService()
        .retrieve()
        .then(res => {
          sporadicPurchasePayments.value = res.data;
        });
      sharePaymentService()
        .retrieve()
        .then(res => {
          sharePayments.value = res.data;
        });
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
      paymentid: {},
      contractcode: {},
      contractname: {},
      wbsid: {},
      wbsname: {},
      amount: {},
      contract: {},
      transactionPayment: {},
      sporadicPurchasePayment: {},
      sharePayment: {},
      contractPayments: {},
    };
    const v$ = useVuelidate(validationRules, fundSourceList as any);
    v$.value.$validate();

    return {
      fundSourceListService,
      alertService,
      fundSourceList,
      previousState,
      isSaving,
      currentLanguage,
      contracts,
      transactionPayments,
      sporadicPurchasePayments,
      sharePayments,
      contractPayments,
      v$,
      t$,
    };
  },
  created(): void {
    this.fundSourceList.contractPayments = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.fundSourceList.id) {
        this.fundSourceListService()
          .update(this.fundSourceList)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.fundSourceList.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.fundSourceListService()
          .create(this.fundSourceList)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.fundSourceList.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },

    getSelected(selectedVals, option, pkField = 'id'): any {
      if (selectedVals) {
        return selectedVals.find(value => option[pkField] === value[pkField]) ?? option;
      }
      return option;
    },
  },
});
