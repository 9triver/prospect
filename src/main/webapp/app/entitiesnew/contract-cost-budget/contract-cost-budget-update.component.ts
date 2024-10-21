import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ContractCostBudgetService from './contract-cost-budget.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { type IContractCostBudget, ContractCostBudget } from '@/shared/model/contract-cost-budget.model';
import { ContractSubject } from '@/shared/model/enumerations/contract-subject.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ContractCostBudgetUpdate',
  setup() {
    const contractCostBudgetService = inject('contractCostBudgetService', () => new ContractCostBudgetService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const contractCostBudget: Ref<IContractCostBudget> = ref(new ContractCostBudget());

    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());

    const projectwbs: Ref<IProjectwbs[]> = ref([]);
    const contractSubjectValues: Ref<string[]> = ref(Object.keys(ContractSubject));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveContractCostBudget = async contractCostBudgetId => {
      try {
        const res = await contractCostBudgetService().find(contractCostBudgetId);
        contractCostBudget.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.contractCostBudgetId) {
      retrieveContractCostBudget(route.params.contractCostBudgetId);
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
      subject: {},
      auxiliaryitem: {},
      unit: {},
      number: {},
      unitprice: {},
      totalprice: {},
      projectwbs: {},
    };
    const v$ = useVuelidate(validationRules, contractCostBudget as any);
    v$.value.$validate();

    return {
      contractCostBudgetService,
      alertService,
      contractCostBudget,
      previousState,
      contractSubjectValues,
      isSaving,
      currentLanguage,
      projectwbs,
      v$,
      t$,
    };
  },
  created(): void {
    this.contractCostBudget.projectwbs = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.contractCostBudget.id) {
        this.contractCostBudgetService()
          .update(this.contractCostBudget)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.contractCostBudget.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.contractCostBudgetService()
          .create(this.contractCostBudget)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.contractCostBudget.created', { param: param.id }).toString());
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
