import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ProjectRiskService from './project-risk.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import WorkbagService from '@/entities/workbag/workbag.service';
import { type IWorkbag } from '@/shared/model/workbag.model';
import FrontlineService from '@/entities/frontline/frontline.service';
import { type IFrontline } from '@/shared/model/frontline.model';
import SystemLevelService from '@/entities/system-level/system-level.service';
import { type ISystemLevel } from '@/shared/model/system-level.model';
import RiskTypeService from '@/entities/risk-type/risk-type.service';
import { type IRiskType } from '@/shared/model/risk-type.model';
import RiskLevelService from '@/entities/risk-level/risk-level.service';
import { type IRiskLevel } from '@/shared/model/risk-level.model';
import RiskPossibilityService from '@/entities/risk-possibility/risk-possibility.service';
import { type IRiskPossibility } from '@/shared/model/risk-possibility.model';
import ProgressPlanService from '@/entities/progress-plan/progress-plan.service';
import { type IProgressPlan } from '@/shared/model/progress-plan.model';
import { type IProjectRisk, ProjectRisk } from '@/shared/model/project-risk.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectRiskUpdate',
  setup() {
    const projectRiskService = inject('projectRiskService', () => new ProjectRiskService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const projectRisk: Ref<IProjectRisk> = ref(new ProjectRisk());

    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());

    const projectwbs: Ref<IProjectwbs[]> = ref([]);

    const workbagService = inject('workbagService', () => new WorkbagService());

    const workbags: Ref<IWorkbag[]> = ref([]);

    const frontlineService = inject('frontlineService', () => new FrontlineService());

    const frontlines: Ref<IFrontline[]> = ref([]);

    const systemLevelService = inject('systemLevelService', () => new SystemLevelService());

    const systemLevels: Ref<ISystemLevel[]> = ref([]);

    const riskTypeService = inject('riskTypeService', () => new RiskTypeService());

    const riskTypes: Ref<IRiskType[]> = ref([]);

    const riskLevelService = inject('riskLevelService', () => new RiskLevelService());

    const riskLevels: Ref<IRiskLevel[]> = ref([]);

    const riskPossibilityService = inject('riskPossibilityService', () => new RiskPossibilityService());

    const riskPossibilities: Ref<IRiskPossibility[]> = ref([]);

    const progressPlanService = inject('progressPlanService', () => new ProgressPlanService());

    const progressPlans: Ref<IProgressPlan[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveProjectRisk = async projectRiskId => {
      try {
        const res = await projectRiskService().find(projectRiskId);
        projectRisk.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.projectRiskId) {
      retrieveProjectRisk(route.params.projectRiskId);
    }

    const initRelationships = () => {
      projectwbsService()
        .retrieve()
        .then(res => {
          projectwbs.value = res.data;
        });
      workbagService()
        .retrieve()
        .then(res => {
          workbags.value = res.data;
        });
      frontlineService()
        .retrieve()
        .then(res => {
          frontlines.value = res.data;
        });
      systemLevelService()
        .retrieve()
        .then(res => {
          systemLevels.value = res.data;
        });
      riskTypeService()
        .retrieve()
        .then(res => {
          riskTypes.value = res.data;
        });
      riskLevelService()
        .retrieve()
        .then(res => {
          riskLevels.value = res.data;
        });
      riskPossibilityService()
        .retrieve()
        .then(res => {
          riskPossibilities.value = res.data;
        });
      progressPlanService()
        .retrieve()
        .then(res => {
          progressPlans.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      year: {},
      name: {},
      riskcontent: {},
      identificationtime: {},
      riskreason: {},
      importantrange: {},
      measuresandtimelimit: {},
      conditions: {},
      closedloopindicator: {},
      wbsid: {},
      workbag: {},
      frontlineid: {},
      systemLevel: {},
      riskType: {},
      riskLevel: {},
      riskPossibility: {},
      progressPlans: {},
    };
    const v$ = useVuelidate(validationRules, projectRisk as any);
    v$.value.$validate();

    return {
      projectRiskService,
      alertService,
      projectRisk,
      previousState,
      isSaving,
      currentLanguage,
      projectwbs,
      workbags,
      frontlines,
      systemLevels,
      riskTypes,
      riskLevels,
      riskPossibilities,
      progressPlans,
      v$,
      t$,
    };
  },
  created(): void {
    this.projectRisk.progressPlans = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.projectRisk.id) {
        this.projectRiskService()
          .update(this.projectRisk)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.projectRisk.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.projectRiskService()
          .create(this.projectRisk)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.projectRisk.created', { param: param.id }).toString());
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
