import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ProjectwbsService from './projectwbs.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProjectpbsService from '@/entities/projectpbs/projectpbs.service';
import { type IProjectpbs } from '@/shared/model/projectpbs.model';
import HrManagementService from '@/entities/hr-management/hr-management.service';
import { type IHrManagement } from '@/shared/model/hr-management.model';
import DepartmentService from '@/entities/department/department.service';
import { type IDepartment } from '@/shared/model/department.model';
import ProjectdeliverablesService from '@/entities/projectdeliverables/projectdeliverables.service';
import { type IProjectdeliverables } from '@/shared/model/projectdeliverables.model';
import WorkbagService from '@/entities/workbag/workbag.service';
import { type IWorkbag } from '@/shared/model/workbag.model';
import ProgressPlanService from '@/entities/progress-plan/progress-plan.service';
import { type IProgressPlan } from '@/shared/model/progress-plan.model';
import ProjectBudgetService from '@/entities/project-budget/project-budget.service';
import { type IProjectBudget } from '@/shared/model/project-budget.model';
import ProjectService from '@/entities/project/project.service';
import { type IProject } from '@/shared/model/project.model';
import FundsEstimationService from '@/entities/funds-estimation/funds-estimation.service';
import { type IFundsEstimation } from '@/shared/model/funds-estimation.model';
import ContractCostBudgetService from '@/entities/contract-cost-budget/contract-cost-budget.service';
import { type IContractCostBudget } from '@/shared/model/contract-cost-budget.model';
import CostControlSystemService from '@/entities/cost-control-system/cost-control-system.service';
import { type ICostControlSystem } from '@/shared/model/cost-control-system.model';
import OutsourcingContractualService from '@/entities/outsourcing-contractual/outsourcing-contractual.service';
import { type IOutsourcingContractual } from '@/shared/model/outsourcing-contractual.model';
import OutsourcingPurchasePlanService from '@/entities/outsourcing-purchase-plan/outsourcing-purchase-plan.service';
import { type IOutsourcingPurchasePlan } from '@/shared/model/outsourcing-purchase-plan.model';
import TechnicalService from '@/entities/technical/technical.service';
import { type ITechnical } from '@/shared/model/technical.model';
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

    const projectpbsService = inject('projectpbsService', () => new ProjectpbsService());

    const projectpbs: Ref<IProjectpbs[]> = ref([]);

    const hrManagementService = inject('hrManagementService', () => new HrManagementService());

    const hrManagements: Ref<IHrManagement[]> = ref([]);

    const departmentService = inject('departmentService', () => new DepartmentService());

    const departments: Ref<IDepartment[]> = ref([]);

    const projectdeliverablesService = inject('projectdeliverablesService', () => new ProjectdeliverablesService());

    const projectdeliverables: Ref<IProjectdeliverables[]> = ref([]);

    const workbagService = inject('workbagService', () => new WorkbagService());

    const workbags: Ref<IWorkbag[]> = ref([]);

    const progressPlanService = inject('progressPlanService', () => new ProgressPlanService());

    const progressPlans: Ref<IProgressPlan[]> = ref([]);

    const projectBudgetService = inject('projectBudgetService', () => new ProjectBudgetService());

    const projectBudgets: Ref<IProjectBudget[]> = ref([]);

    const projectService = inject('projectService', () => new ProjectService());

    const projects: Ref<IProject[]> = ref([]);

    const fundsEstimationService = inject('fundsEstimationService', () => new FundsEstimationService());

    const fundsEstimations: Ref<IFundsEstimation[]> = ref([]);

    const contractCostBudgetService = inject('contractCostBudgetService', () => new ContractCostBudgetService());

    const contractCostBudgets: Ref<IContractCostBudget[]> = ref([]);

    const costControlSystemService = inject('costControlSystemService', () => new CostControlSystemService());

    const costControlSystems: Ref<ICostControlSystem[]> = ref([]);

    const outsourcingContractualService = inject('outsourcingContractualService', () => new OutsourcingContractualService());

    const outsourcingContractuals: Ref<IOutsourcingContractual[]> = ref([]);

    const outsourcingPurchasePlanService = inject('outsourcingPurchasePlanService', () => new OutsourcingPurchasePlanService());

    const outsourcingPurchasePlans: Ref<IOutsourcingPurchasePlan[]> = ref([]);

    const technicalService = inject('technicalService', () => new TechnicalService());

    const technicals: Ref<ITechnical[]> = ref([]);
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
      projectpbsService()
        .retrieve()
        .then(res => {
          projectpbs.value = res.data;
        });
      hrManagementService()
        .retrieve()
        .then(res => {
          hrManagements.value = res.data;
        });
      departmentService()
        .retrieve()
        .then(res => {
          departments.value = res.data;
        });
      projectdeliverablesService()
        .retrieve()
        .then(res => {
          projectdeliverables.value = res.data;
        });
      workbagService()
        .retrieve()
        .then(res => {
          workbags.value = res.data;
        });
      progressPlanService()
        .retrieve()
        .then(res => {
          progressPlans.value = res.data;
        });
      projectBudgetService()
        .retrieve()
        .then(res => {
          projectBudgets.value = res.data;
        });
      projectService()
        .retrieve()
        .then(res => {
          projects.value = res.data;
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
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      wbsname: {},
      parentwbsid: {},
      description: {},
      belongfrontline: {},
      starttime: {},
      endtime: {},
      progress: {},
      type: {},
      priorty: {},
      secretlevel: {},
      deliverables: {},
      status: {},
      auditStatus: {},
      workbagid: {},
      projectpbs: {},
      responsibleperson: {},
      technicaldirector: {},
      knowingpeople: {},
      auditorid: {},
      responsibledepartment: {},
      projectdeliverables: {},
      relevantdepartments: {},
      workbags: {},
      progressPlans: {},
      projectBudgets: {},
      projects: {},
      fundsEstimations: {},
      contractCostBudgets: {},
      costControlSystems: {},
      outsourcingContractuals: {},
      outsourcingPurchasePlans: {},
      technicals: {},
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
      projectpbs,
      hrManagements,
      departments,
      projectdeliverables,
      workbags,
      progressPlans,
      projectBudgets,
      projects,
      fundsEstimations,
      contractCostBudgets,
      costControlSystems,
      outsourcingContractuals,
      outsourcingPurchasePlans,
      technicals,
      v$,
      t$,
    };
  },
  created(): void {
    this.projectwbs.projectdeliverables = [];
    this.projectwbs.relevantdepartments = [];
    this.projectwbs.workbags = [];
    this.projectwbs.progressPlans = [];
    this.projectwbs.projectBudgets = [];
    this.projectwbs.projects = [];
    this.projectwbs.fundsEstimations = [];
    this.projectwbs.contractCostBudgets = [];
    this.projectwbs.costControlSystems = [];
    this.projectwbs.outsourcingContractuals = [];
    this.projectwbs.outsourcingPurchasePlans = [];
    this.projectwbs.technicals = [];
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
