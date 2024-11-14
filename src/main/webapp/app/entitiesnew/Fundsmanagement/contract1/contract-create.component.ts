import { computed, defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ContractService from './contract.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import CostControlSystemService from '@/entities/cost-control-system/cost-control-system.service';
import { type ICostControlSystem } from '@/shared/model/cost-control-system.model';
import { type IContract, Contract } from '@/shared/model/contract.model';
import { ContractType } from '@/shared/model/enumerations/contract-type.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { ContractStatus } from '@/shared/model/enumerations/contract-status.model';
import SubjectCostBudgetService from '../subject-cost-budget/subject-cost-budget.service';
import { type ISubjectCostBudget } from '@/shared/model/subject-cost-budget.model';
import ProjectBudgetService from '../project-budget/project-budget.service';
import { type IProjectBudget } from '@/shared/model/project-budget.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ContractUpdate',
  setup() {
    const contractService = inject('contractService', () => new ContractService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const contract: Ref<IContract> = ref(new Contract());

    const costControlSystemService = inject('costControlSystemService', () => new CostControlSystemService());

    const costControlSystems: Ref<ICostControlSystem[]> = ref([]);
    const contractTypeValues: Ref<string[]> = ref(Object.keys(ContractType));
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const contractStatusValues: Ref<string[]> = ref(Object.keys(ContractStatus));
    //承沿合同信息
    const contracts: Ref<IContract[]> = ref([]);
    //科目经费信息
    const subjectCostBudgetService = inject('subjectCostBudgetService', () => new SubjectCostBudgetService());
    const subjectCostBudgets: Ref<ISubjectCostBudget[]> = ref([]);
    //系统内经费信息
    const projectBudgetService = inject('projectBudgetService', () => new ProjectBudgetService());
    const projectBudgets: Ref<IProjectBudget[]> = ref([]);

    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveContracts = async () => {
      try {
        const contractres = await contractService().retrieve();
        contracts.value = contractres.data;

        const subjectcostbudgetres = await subjectCostBudgetService().retrieve();
        subjectCostBudgets.value = subjectcostbudgetres.data;


        const projectbudgetres = await projectBudgetService().retrieve();
        projectBudgets.value = projectbudgetres.data;

      } catch (err) {
        alertService.showHttpError(err.response);
      }
    };

    onMounted(async () => {
      await retrieveContracts();
    });

    const initRelationships = () => {
      costControlSystemService()
        .retrieve()
        .then(res => {
          costControlSystems.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      contractcode: {},
      contractname: {},
      projectid: {},
      projectname: {},
      contracttype: {},
      year: {},
      amount: {},
      starttime: {},
      endtime: {},
      secretlevel: {},
      status: {},
      budgetamount: {},
      estimatedamount: {},
      implementedamount: {},
      difference: {},
      costControlSystems: {},
    };
    const v$ = useVuelidate(validationRules, contract as any);
    v$.value.$validate();

    return {
      contracts,
      contractService,
      alertService,
      contract,
      previousState,
      contractTypeValues,
      secretlevelValues,
      contractStatusValues,
      isSaving,
      currentLanguage,
      costControlSystems,
      v$,
      t$,
      subjectCostBudgets,
      projectBudgets,

      activeTab: 'first',
      firstTabData: {
        type: '',
        amount: null,
      },
      firstTabList: [],
      secondTabData: {
        project: '',
        materialCost: null,
      },
      secondTabList: [],
    };
  },
  created(): void {
    this.contract.costControlSystems = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.contract.id) {
        this.contractService()
          .update(this.contract)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.contract.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.contractService()
          .create(this.contract)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.contract.created', { param: param.id }).toString());
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


    addToFirstTab() {
      if (this.firstTabData.type && this.firstTabData.amount) {
        this.firstTabList.push({ ...this.firstTabData });
        this.firstTabData.type = '';
        this.firstTabData.amount = null;
      }
    },
    addToSecondTab() {
      if (this.secondTabData.project && this.secondTabData.materialCost) {
        this.secondTabList.push({ ...this.secondTabData });
        this.secondTabData.project = '';
        this.secondTabData.materialCost = null;
      }
    },


  },
});
