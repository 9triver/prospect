import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import PaymentApplicationService from './payment-application.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import WorkbagService from '@/entities/workbag/workbag.service';
import { type IWorkbag } from '@/shared/model/workbag.model';
import { type IPaymentApplication, PaymentApplication } from '@/shared/model/payment-application.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PaymentApplicationUpdate',
  setup() {
    const paymentApplicationService = inject('paymentApplicationService', () => new PaymentApplicationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const paymentApplication: Ref<IPaymentApplication> = ref(new PaymentApplication());

    const workbagService = inject('workbagService', () => new WorkbagService());

    const workbags: Ref<IWorkbag[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrievePaymentApplication = async paymentApplicationId => {
      try {
        const res = await paymentApplicationService().find(paymentApplicationId);
        paymentApplication.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.paymentApplicationId) {
      retrievePaymentApplication(route.params.paymentApplicationId);
    }

    const initRelationships = () => {
      workbagService()
        .retrieve()
        .then(res => {
          workbags.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      workbagname: {},
      outsourcingcontractid: {},
      outsourcingcontractname: {},
      planpaymentnode: {},
      planpaymentname: {},
      planpaymentamount: {},
      contractpaymentid: {},
      status: {},
      workbag: {},
    };
    const v$ = useVuelidate(validationRules, paymentApplication as any);
    v$.value.$validate();

    return {
      paymentApplicationService,
      alertService,
      paymentApplication,
      previousState,
      isSaving,
      currentLanguage,
      workbags,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.paymentApplication.id) {
        this.paymentApplicationService()
          .update(this.paymentApplication)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.paymentApplication.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.paymentApplicationService()
          .create(this.paymentApplication)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.paymentApplication.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
