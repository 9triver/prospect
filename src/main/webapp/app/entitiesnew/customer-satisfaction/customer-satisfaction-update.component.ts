import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import CustomerSatisfactionService from './customer-satisfaction.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { type ICustomerSatisfaction, CustomerSatisfaction } from '@/shared/model/customer-satisfaction.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CustomerSatisfactionUpdate',
  setup() {
    const customerSatisfactionService = inject('customerSatisfactionService', () => new CustomerSatisfactionService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const customerSatisfaction: Ref<ICustomerSatisfaction> = ref(new CustomerSatisfaction());

    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());

    const projectwbs: Ref<IProjectwbs[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveCustomerSatisfaction = async customerSatisfactionId => {
      try {
        const res = await customerSatisfactionService().find(customerSatisfactionId);
        customerSatisfaction.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.customerSatisfactionId) {
      retrieveCustomerSatisfaction(route.params.customerSatisfactionId);
    }

    const initRelationships = () => {
      projectwbsService()
        .retrieve()
        .then(res => {
          projectwbs.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      year: {},
      satisfactionitem: {},
      score: {},
      opinion: {},
      totalscore: {},
      surveytime: {},
      customer: {},
      plonenumber: {},
      filename: {},
      wbsid: {},
    };
    const v$ = useVuelidate(validationRules, customerSatisfaction as any);
    v$.value.$validate();

    return {
      customerSatisfactionService,
      alertService,
      customerSatisfaction,
      previousState,
      isSaving,
      currentLanguage,
      projectwbs,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.customerSatisfaction.id) {
        this.customerSatisfactionService()
          .update(this.customerSatisfaction)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.customerSatisfaction.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.customerSatisfactionService()
          .create(this.customerSatisfaction)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.customerSatisfaction.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
