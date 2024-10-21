import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import QualityObjectivesService from './quality-objectives.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import HrManagementService from '@/entities/hr-management/hr-management.service';
import { type IHrManagement } from '@/shared/model/hr-management.model';
import QualityReturnsService from '@/entities/quality-returns/quality-returns.service';
import { type IQualityReturns } from '@/shared/model/quality-returns.model';
import QualityPlanService from '@/entities/quality-plan/quality-plan.service';
import { type IQualityPlan } from '@/shared/model/quality-plan.model';
import { type IQualityObjectives, QualityObjectives } from '@/shared/model/quality-objectives.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QualityObjectivesUpdate',
  setup() {
    const qualityObjectivesService = inject('qualityObjectivesService', () => new QualityObjectivesService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const qualityObjectives: Ref<IQualityObjectives> = ref(new QualityObjectives());

    const hrManagementService = inject('hrManagementService', () => new HrManagementService());

    const hrManagements: Ref<IHrManagement[]> = ref([]);

    const qualityReturnsService = inject('qualityReturnsService', () => new QualityReturnsService());

    const qualityReturns: Ref<IQualityReturns[]> = ref([]);

    const qualityPlanService = inject('qualityPlanService', () => new QualityPlanService());

    const qualityPlans: Ref<IQualityPlan[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveQualityObjectives = async qualityObjectivesId => {
      try {
        const res = await qualityObjectivesService().find(qualityObjectivesId);
        qualityObjectives.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.qualityObjectivesId) {
      retrieveQualityObjectives(route.params.qualityObjectivesId);
    }

    const initRelationships = () => {
      hrManagementService()
        .retrieve()
        .then(res => {
          hrManagements.value = res.data;
        });
      qualityReturnsService()
        .retrieve()
        .then(res => {
          qualityReturns.value = res.data;
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
      name: {},
      objectiveslevel: {},
      objectives: {},
      objectivesvalue: {},
      calculationmethod: {},
      frequency: {},
      takeaction: {},
      needresource: {},
      status: {},
      responsibleperson: {},
      qualityReturns: {},
      qualityPlan: {},
    };
    const v$ = useVuelidate(validationRules, qualityObjectives as any);
    v$.value.$validate();

    return {
      qualityObjectivesService,
      alertService,
      qualityObjectives,
      previousState,
      isSaving,
      currentLanguage,
      hrManagements,
      qualityReturns,
      qualityPlans,
      v$,
      t$,
    };
  },
  created(): void {
    this.qualityObjectives.qualityReturns = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.qualityObjectives.id) {
        this.qualityObjectivesService()
          .update(this.qualityObjectives)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.qualityObjectives.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.qualityObjectivesService()
          .create(this.qualityObjectives)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.qualityObjectives.created', { param: param.id }).toString());
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
