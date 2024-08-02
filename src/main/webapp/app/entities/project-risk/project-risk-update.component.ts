import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ProjectRiskService from './project-risk.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import RiskReportService from '@/entities/risk-report/risk-report.service';
import { type IRiskReport } from '@/shared/model/risk-report.model';
import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import ProgressPlanService from '@/entities/progress-plan/progress-plan.service';
import { type IProgressPlan } from '@/shared/model/progress-plan.model';
import { type IProjectRisk, ProjectRisk } from '@/shared/model/project-risk.model';
import { Risklevel } from '@/shared/model/enumerations/risklevel.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectRiskUpdate',
  setup() {
    const projectRiskService = inject('projectRiskService', () => new ProjectRiskService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const projectRisk: Ref<IProjectRisk> = ref(new ProjectRisk());

    const riskReportService = inject('riskReportService', () => new RiskReportService());

    const riskReports: Ref<IRiskReport[]> = ref([]);

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);

    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());

    const projectwbs: Ref<IProjectwbs[]> = ref([]);

    const progressPlanService = inject('progressPlanService', () => new ProgressPlanService());

    const progressPlans: Ref<IProgressPlan[]> = ref([]);
    const risklevelValues: Ref<string[]> = ref(Object.keys(Risklevel));
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
      riskReportService()
        .retrieve()
        .then(res => {
          riskReports.value = res.data;
        });
      officersService()
        .retrieve()
        .then(res => {
          officers.value = res.data;
        });
      projectwbsService()
        .retrieve()
        .then(res => {
          projectwbs.value = res.data;
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
      nodename: {},
      risktype: {},
      decumentid: {},
      version: {},
      usetime: {},
      systemlevel: {},
      risklevel: {},
      limitationtime: {},
      closetype: {},
      riskReport: {},
      creatorid: {},
      responsibleperson: {},
      auditorid: {},
      projectwbs: {},
      progressPlans: {},
    };
    const v$ = useVuelidate(validationRules, projectRisk as any);
    v$.value.$validate();

    return {
      projectRiskService,
      alertService,
      projectRisk,
      previousState,
      risklevelValues,
      isSaving,
      currentLanguage,
      riskReports,
      officers,
      projectwbs,
      progressPlans,
      v$,
      t$,
    };
  },
  created(): void {
    this.projectRisk.projectwbs = [];
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
