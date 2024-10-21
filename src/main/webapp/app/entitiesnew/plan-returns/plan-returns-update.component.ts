import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import PlanReturnsService from './plan-returns.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import HrManagementService from '@/entities/hr-management/hr-management.service';
import { type IHrManagement } from '@/shared/model/hr-management.model';
import ProgressPlanService from '@/entities/progress-plan/progress-plan.service';
import { type IProgressPlan } from '@/shared/model/progress-plan.model';
import { type IPlanReturns, PlanReturns } from '@/shared/model/plan-returns.model';
import { PlanLevel } from '@/shared/model/enumerations/plan-level.model';
import { Planstatus } from '@/shared/model/enumerations/planstatus.model';
import { ReturnsStatus } from '@/shared/model/enumerations/returns-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PlanReturnsUpdate',
  setup() {
    const planReturnsService = inject('planReturnsService', () => new PlanReturnsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const planReturns: Ref<IPlanReturns> = ref(new PlanReturns());

    const hrManagementService = inject('hrManagementService', () => new HrManagementService());

    const hrManagements: Ref<IHrManagement[]> = ref([]);

    const progressPlanService = inject('progressPlanService', () => new ProgressPlanService());

    const progressPlans: Ref<IProgressPlan[]> = ref([]);
    const planLevelValues: Ref<string[]> = ref(Object.keys(PlanLevel));
    const planstatusValues: Ref<string[]> = ref(Object.keys(Planstatus));
    const returnsStatusValues: Ref<string[]> = ref(Object.keys(ReturnsStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrievePlanReturns = async planReturnsId => {
      try {
        const res = await planReturnsService().find(planReturnsId);
        planReturns.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.planReturnsId) {
      retrievePlanReturns(route.params.planReturnsId);
    }

    const initRelationships = () => {
      hrManagementService()
        .retrieve()
        .then(res => {
          hrManagements.value = res.data;
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
      planreturnsname: {},
      plantype: {},
      planlevel: {},
      description: {},
      actualstarttime: {},
      actualendtime: {},
      deliverables: {},
      progress: {},
      status: {},
      impactanalysis: {},
      returnstime: {},
      rejectionreason: {},
      returnsstatus: {},
      responsibleperson: {},
      auditorid: {},
      progressPlan: {},
    };
    const v$ = useVuelidate(validationRules, planReturns as any);
    v$.value.$validate();

    return {
      planReturnsService,
      alertService,
      planReturns,
      previousState,
      planLevelValues,
      planstatusValues,
      returnsStatusValues,
      isSaving,
      currentLanguage,
      hrManagements,
      progressPlans,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.planReturns.id) {
        this.planReturnsService()
          .update(this.planReturns)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.planReturns.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.planReturnsService()
          .create(this.planReturns)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.planReturns.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
