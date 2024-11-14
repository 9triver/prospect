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
import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import SubjectService from '@/entitiesnew/Fundsmanagement/subject/subject.service';
import { type ISubject } from '@/shared/model/subject.model';

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
    //项目信息
    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());
    const projectwbs: Ref<IProjectwbs[]> = ref([]);
    //获取科目字典-选择type=2其他7个科目
    const subjectService = inject('subjectService', () => new SubjectService());
    const subjects: Ref<ISubject[]> = ref([]);

    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    //科目经费信息保存
    const isSubjectSaving = ref(false);
    //系统内经费信息保存
    const isProjectSaving = ref(false);

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
      projectwbsService()
        .retrieve()
        .then(res => {
          projectwbs.value = res.data;
          //只选择出重大项目
          projectwbs.value = res.data.filter(projectwbs => projectwbs.parentwbsid === "" || projectwbs.parentwbsid === null || projectwbs.parentwbsid === undefined);
        
        });
      subjectService()
        .retrieve()
        .then(res => {
          subjects.value = res.data.filter(subject => subject.type === "1");
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
      projectwbs: {},
    };
    const v$ = useVuelidate(validationRules, contract as any);
    v$.value.$validate();


    // 默认显示“科目经费”Tab
    const activeTab = ref('subject');
    // 保存联系方式
    // const saveContactInfo = () => {
    //   if (subjectCostBudgets.value.some(row => !row.phone)) {
    //     return this.$message.error('电话不能为空！')
    //   }
    //   console.log("联系方式已保存:", subjectCostBudgets.value)
    //   this.$message.success('联系方式保存成功！')
    // }

    // 保存学习成绩
    // const saveGradeInfo = () => {
    //   if (gradeRows.value.some(row => !row.score)) {
    //     return this.$message.error('成绩不能为空！')
    //   }
    //   console.log("学习成绩已保存:", gradeRows.value)
    //   this.$message.success('学习成绩保存成功！')
    // }
    

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
      projectwbs,
      subjects,
      subjectCostBudgetService,
      projectBudgetService,

      // saveContactInfo,
      // saveGradeInfo,
      activeTab,
      isSubjectSaving,
      isProjectSaving,
    };
  },
  created(): void {
    this.contract.costControlSystems = [];
  },
  methods: {
    save(): void {
      alert('save');
      this.isSaving = true;
      if (this.contract.id) {
        this.contractService()
          .update(this.contract)
          .then(param => {
            // this.isSaving = false;
            // this.previousState();
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
            // this.isSaving = false;
            // this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.contract.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
    saveSubject(): void {
      this.isSubjectSaving = true;
      for (let i = 0; i < this.subjectCostBudgets.length; i++) {
        if(i < this.subjectCostBudgets.length-1) {
          this.subjectCostBudgetService()
          .update(this.subjectCostBudgets[i])
          .catch(error => {
            this.isSubjectSaving = false;
            this.alertService.showHttpError(error.response);
          });
        }else {
          this.subjectCostBudgetService()
          .update(this.subjectCostBudgets[i])
          .then(param => {
            this.isSubjectSaving = false;
            this.alertService.showInfo("科目经费预算保存成功", { param: param.id });
          })
          .catch(error => {
            this.isSubjectSaving = false;
            this.alertService.showHttpError(error.response);
          });
        }
      }
    },
    saveProject(): void {
      this.isProjectSaving = true;
      for (let i = 0; i < this.projectBudgets.length; i++) {
        if(i < this.projectBudgetService.length-1) {
          this.projectBudgetService()
          .update(this.projectBudgets[i])
          .catch(error => {
            this.isProjectSaving = false;
            this.alertService.showHttpError(error.response);
          });
        }else {
          this.projectBudgetService()
          .update(this.projectBudgets[i])
          .then(param => {
            this.isProjectSaving = false;
            this.alertService.showInfo("系统内经费预算保存成功", { param: param.id });
          })
          .catch(error => {
            this.isProjectSaving = false;
            this.alertService.showHttpError(error.response);
          });
        }
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
