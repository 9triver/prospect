import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ProjectwbsService from './projectwbs.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import DepartmentService from '@/entities/department/department.service';
import { type IDepartment } from '@/shared/model/department.model';
import ProjectService from '@/entities/project/project.service';
import { type IProject } from '@/shared/model/project.model';
import ProjectpbsService from '@/entities/projectpbs/projectpbs.service';
import { type IProjectpbs } from '@/shared/model/projectpbs.model';
import ProgressPlanService from '@/entities/progress-plan/progress-plan.service';
import { type IProgressPlan } from '@/shared/model/progress-plan.model';
import FundsEstimationService from '@/entities/funds-estimation/funds-estimation.service';
import { type IFundsEstimation } from '@/shared/model/funds-estimation.model';
import ContractCostBudgetService from '@/entities/contract-cost-budget/contract-cost-budget.service';
import { type IContractCostBudget } from '@/shared/model/contract-cost-budget.model';
import CostControlSystemService from '@/entities/cost-control-system/cost-control-system.service';
import { type ICostControlSystem } from '@/shared/model/cost-control-system.model';
import QualityObjectivesService from '@/entities/quality-objectives/quality-objectives.service';
import { type IQualityObjectives } from '@/shared/model/quality-objectives.model';
import OutsourcingContractualService from '@/entities/outsourcing-contractual/outsourcing-contractual.service';
import { type IOutsourcingContractual } from '@/shared/model/outsourcing-contractual.model';
import OutsourcingPurchasePlanService from '@/entities/outsourcing-purchase-plan/outsourcing-purchase-plan.service';
import { type IOutsourcingPurchasePlan } from '@/shared/model/outsourcing-purchase-plan.model';
import TechnicalService from '@/entities/technical/technical.service';
import { type ITechnical } from '@/shared/model/technical.model';
import TechnicalConditionService from '@/entities/technical-condition/technical-condition.service';
import { type ITechnicalCondition } from '@/shared/model/technical-condition.model';
import ProjectRiskService from '@/entities/project-risk/project-risk.service';
import { type IProjectRisk } from '@/shared/model/project-risk.model';
import { type IProjectwbs, Projectwbs } from '@/shared/model/projectwbs.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { ProjectStatus } from '@/shared/model/enumerations/project-status.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectwbsUpdate',
  setup() {
    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const projectwbs: Ref<IProjectwbs> = ref(new Projectwbs());

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);

    const departmentService = inject('departmentService', () => new DepartmentService());

    const departments: Ref<IDepartment[]> = ref([]);

    const projectService = inject('projectService', () => new ProjectService());

    const projects: Ref<IProject[]> = ref([]);

    const projectpbsService = inject('projectpbsService', () => new ProjectpbsService());

    const projectpbs: Ref<IProjectpbs[]> = ref([]);

    const progressPlanService = inject('progressPlanService', () => new ProgressPlanService());

    const progressPlans: Ref<IProgressPlan[]> = ref([]);

    const fundsEstimationService = inject('fundsEstimationService', () => new FundsEstimationService());

    const fundsEstimations: Ref<IFundsEstimation[]> = ref([]);

    const contractCostBudgetService = inject('contractCostBudgetService', () => new ContractCostBudgetService());

    const contractCostBudgets: Ref<IContractCostBudget[]> = ref([]);

    const costControlSystemService = inject('costControlSystemService', () => new CostControlSystemService());

    const costControlSystems: Ref<ICostControlSystem[]> = ref([]);

    const qualityObjectivesService = inject('qualityObjectivesService', () => new QualityObjectivesService());

    const qualityObjectives: Ref<IQualityObjectives[]> = ref([]);

    const outsourcingContractualService = inject('outsourcingContractualService', () => new OutsourcingContractualService());

    const outsourcingContractuals: Ref<IOutsourcingContractual[]> = ref([]);

    const outsourcingPurchasePlanService = inject('outsourcingPurchasePlanService', () => new OutsourcingPurchasePlanService());

    const outsourcingPurchasePlans: Ref<IOutsourcingPurchasePlan[]> = ref([]);

    const technicalService = inject('technicalService', () => new TechnicalService());

    const technicals: Ref<ITechnical[]> = ref([]);

    const technicalConditionService = inject('technicalConditionService', () => new TechnicalConditionService());

    const technicalConditions: Ref<ITechnicalCondition[]> = ref([]);

    const projectRiskService = inject('projectRiskService', () => new ProjectRiskService());

    const projectRisks: Ref<IProjectRisk[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const projectStatusValues: Ref<string[]> = ref(Object.keys(ProjectStatus));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveProjectwbs = async projectwbsId => {
      try {
        const res = await projectwbsService().find(projectwbsId);
        projectwbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.projectwbsId) {
      retrieveProjectwbs(route.params.projectwbsId);
    }

    const initRelationships = () => {
      officersService()
        .retrieve()
        .then(res => {
          officers.value = res.data;
        });
      departmentService()
        .retrieve()
        .then(res => {
          departments.value = res.data;
        });
      projectService()
        .retrieve()
        .then(res => {
          projects.value = res.data;
        });
      projectpbsService()
        .retrieve()
        .then(res => {
          projectpbs.value = res.data;
        });
      progressPlanService()
        .retrieve()
        .then(res => {
          progressPlans.value = res.data;
        });
      fundsEstimationService()
        .retrieve()
        .then(res => {
          fundsEstimations.value = res.data;
        });
      contractCostBudgetService()
        .retrieve()
        .then(res => {
          contractCostBudgets.value = res.data;
        });
      costControlSystemService()
        .retrieve()
        .then(res => {
          costControlSystems.value = res.data;
        });
      qualityObjectivesService()
        .retrieve()
        .then(res => {
          qualityObjectives.value = res.data;
        });
      outsourcingContractualService()
        .retrieve()
        .then(res => {
          outsourcingContractuals.value = res.data;
        });
      outsourcingPurchasePlanService()
        .retrieve()
        .then(res => {
          outsourcingPurchasePlans.value = res.data;
        });
      technicalService()
        .retrieve()
        .then(res => {
          technicals.value = res.data;
        });
      technicalConditionService()
        .retrieve()
        .then(res => {
          technicalConditions.value = res.data;
        });
      projectRiskService()
        .retrieve()
        .then(res => {
          projectRisks.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      wbsname: {},
      parentwbsid: {},
      pbsid: {},
      description: {},
      belongfront: {},
      starttime: {},
      endtime: {},
      progress: {},
      type: {},
      priorty: {},
      secretlevel: {},
      deliverables: {},
      status: {},
      auditStatus: {},
      workbag: {},
      responsibleperson: {},
      technicaldirector: {},
      administrativedirector: {},
      knowingpeople: {},
      auditorid: {},
      responsibledepartment: {},
      relevantdepartment: {},
      department: {},
      projects: {},
      projectpbs: {},
      progressPlans: {},
      fundsEstimations: {},
      contractCostBudgets: {},
      costControlSystems: {},
      qualityObjectives: {},
      outsourcingContractuals: {},
      outsourcingPurchasePlans: {},
      technicals: {},
      technicalConditions: {},
      projectRisks: {},
    };
    const v$ = useVuelidate(validationRules, projectwbs as any);
    v$.value.$validate();

    return {
      projectwbsService,
      alertService,
      projectwbs,
      previousState,
      secretlevelValues,
      projectStatusValues,
      auditStatusValues,
      isSaving,
      currentLanguage,
      officers,
      departments,
      projects,
      projectpbs,
      progressPlans,
      fundsEstimations,
      contractCostBudgets,
      costControlSystems,
      qualityObjectives,
      outsourcingContractuals,
      outsourcingPurchasePlans,
      technicals,
      technicalConditions,
      projectRisks,
      v$,
      t$,
    };
  },
  created(): void {
    this.projectwbs.projects = [];
    this.projectwbs.projectpbs = [];
    this.projectwbs.progressPlans = [];
    this.projectwbs.fundsEstimations = [];
    this.projectwbs.contractCostBudgets = [];
    this.projectwbs.costControlSystems = [];
    this.projectwbs.qualityObjectives = [];
    this.projectwbs.outsourcingContractuals = [];
    this.projectwbs.outsourcingPurchasePlans = [];
    this.projectwbs.technicals = [];
    this.projectwbs.technicalConditions = [];
    this.projectwbs.projectRisks = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.projectwbs.id) {
        this.projectwbsService()
          .update(this.projectwbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.projectwbs.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.projectwbsService()
          .create(this.projectwbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.projectwbs.created', { param: param.id }).toString());
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
