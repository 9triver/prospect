import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import QualityReturnsService from './quality-returns.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import HrManagementService from '@/entities/hr-management/hr-management.service';
import { type IHrManagement } from '@/shared/model/hr-management.model';
import QualityObjectivesService from '@/entities/quality-objectives/quality-objectives.service';
import { type IQualityObjectives } from '@/shared/model/quality-objectives.model';
import QualityPlanService from '@/entities/quality-plan/quality-plan.service';
import { type IQualityPlan } from '@/shared/model/quality-plan.model';
import { type IQualityReturns, QualityReturns } from '@/shared/model/quality-returns.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QualityReturnsUpdate',
  setup() {
    const qualityReturnsService = inject('qualityReturnsService', () => new QualityReturnsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const qualityReturns: Ref<IQualityReturns> = ref(new QualityReturns());

    const hrManagementService = inject('hrManagementService', () => new HrManagementService());

    const hrManagements: Ref<IHrManagement[]> = ref([]);

    const qualityObjectivesService = inject('qualityObjectivesService', () => new QualityObjectivesService());

    const qualityObjectives: Ref<IQualityObjectives[]> = ref([]);

    const qualityPlanService = inject('qualityPlanService', () => new QualityPlanService());

    const qualityPlans: Ref<IQualityPlan[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveQualityReturns = async qualityReturnsId => {
      try {
        const res = await qualityReturnsService().find(qualityReturnsId);
        qualityReturns.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.qualityReturnsId) {
      retrieveQualityReturns(route.params.qualityReturnsId);
    }

    const initRelationships = () => {
      hrManagementService()
        .retrieve()
        .then(res => {
          hrManagements.value = res.data;
        });
      qualityObjectivesService()
        .retrieve()
        .then(res => {
          qualityObjectives.value = res.data;
        });
      qualityPlanService()
        .retrieve()
        .then(res => {
          qualityPlans.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      qualityplanid: {},
      qualityobjectivesid: {},
      name: {},
      department: {},
      responsibleid: {},
      wbsid: {},
      workbagid: {},
      objectiveslevel: {},
      objectives: {},
      objectivesvalue: {},
      calculationmethod: {},
      frequency: {},
      isobjectivesvalue: {},
      percentage: {},
      objectivescompletion: {},
      problem: {},
      takeaction: {},
      continuousimprovement: {},
      workevidence: {},
      returntime: {},
      status: {},
      responsibleperson: {},
      auditorid: {},
      qualityObjectives: {},
      qualityPlan: {},
    };
    const v$ = useVuelidate(validationRules, qualityReturns as any);
    v$.value.$validate();

    return {
      qualityReturnsService,
      alertService,
      qualityReturns,
      previousState,
      isSaving,
      currentLanguage,
      hrManagements,
      qualityObjectives,
      qualityPlans,
      v$,
      t$,
    };
  },
  created(): void {
    this.qualityReturns.qualityObjectives = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.qualityReturns.id) {
        this.qualityReturnsService()
          .update(this.qualityReturns)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.qualityReturns.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.qualityReturnsService()
          .create(this.qualityReturns)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.qualityReturns.created', { param: param.id }).toString());
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
