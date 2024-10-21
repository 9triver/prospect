import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ProgressPlanService from './progress-plan.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import HrManagementService from '@/entities/hr-management/hr-management.service';
import { type IHrManagement } from '@/shared/model/hr-management.model';
import DepartmentService from '@/entities/department/department.service';
import { type IDepartment } from '@/shared/model/department.model';
import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import ProjectRiskService from '@/entities/project-risk/project-risk.service';
import { type IProjectRisk } from '@/shared/model/project-risk.model';
import RiskReturnService from '@/entities/risk-return/risk-return.service';
import { type IRiskReturn } from '@/shared/model/risk-return.model';
import { type IProgressPlan, ProgressPlan } from '@/shared/model/progress-plan.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { PlanLevel } from '@/shared/model/enumerations/plan-level.model';
import { Progressstatus } from '@/shared/model/enumerations/progressstatus.model';
import { Planstatus } from '@/shared/model/enumerations/planstatus.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProgressPlanUpdate',
  setup() {
    const progressPlanService = inject('progressPlanService', () => new ProgressPlanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const progressPlan: Ref<IProgressPlan> = ref(new ProgressPlan());

    const hrManagementService = inject('hrManagementService', () => new HrManagementService());

    const hrManagements: Ref<IHrManagement[]> = ref([]);

    const departmentService = inject('departmentService', () => new DepartmentService());

    const departments: Ref<IDepartment[]> = ref([]);

    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());

    const projectwbs: Ref<IProjectwbs[]> = ref([]);

    const projectRiskService = inject('projectRiskService', () => new ProjectRiskService());

    const projectRisks: Ref<IProjectRisk[]> = ref([]);

    const riskReturnService = inject('riskReturnService', () => new RiskReturnService());

    const riskReturns: Ref<IRiskReturn[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const planLevelValues: Ref<string[]> = ref(Object.keys(PlanLevel));
    const progressstatusValues: Ref<string[]> = ref(Object.keys(Progressstatus));
    const planstatusValues: Ref<string[]> = ref(Object.keys(Planstatus));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveProgressPlan = async progressPlanId => {
      try {
        const res = await progressPlanService().find(progressPlanId);
        progressPlan.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.progressPlanId) {
      retrieveProgressPlan(route.params.progressPlanId);
    }

    const initRelationships = () => {
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
      projectwbsService()
        .retrieve()
        .then(res => {
          projectwbs.value = res.data;
        });
      projectRiskService()
        .retrieve()
        .then(res => {
          projectRisks.value = res.data;
        });
      riskReturnService()
        .retrieve()
        .then(res => {
          riskReturns.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      planname: {},
      belongproject: {},
      belongplanid: {},
      secretlevel: {},
      plantype: {},
      planlevel: {},
      planstage: {},
      readytime: {},
      description: {},
      deliverables: {},
      planobjectives: {},
      preplan: {},
      starttime: {},
      endtime: {},
      actualstarttime: {},
      actualendtime: {},
      progress: {},
      progresstype: {},
      iskey: {},
      status: {},
      auditStatus: {},
      returns: {},
      remark: {},
      responsibleperson: {},
      cooperatingperson: {},
      auditorid: {},
      responsibledepartment: {},
      cooperatingdepartment: {},
      projectwbs: {},
      projectRisks: {},
      riskReturn: {},
    };
    const v$ = useVuelidate(validationRules, progressPlan as any);
    v$.value.$validate();

    return {
      progressPlanService,
      alertService,
      progressPlan,
      previousState,
      secretlevelValues,
      planLevelValues,
      progressstatusValues,
      planstatusValues,
      auditStatusValues,
      isSaving,
      currentLanguage,
      hrManagements,
      departments,
      projectwbs,
      projectRisks,
      riskReturns,
      v$,
      t$,
    };
  },
  created(): void {
    this.progressPlan.projectwbs = [];
    this.progressPlan.projectRisks = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.progressPlan.id) {
        this.progressPlanService()
          .update(this.progressPlan)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.progressPlan.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.progressPlanService()
          .create(this.progressPlan)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.progressPlan.created', { param: param.id }).toString());
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
