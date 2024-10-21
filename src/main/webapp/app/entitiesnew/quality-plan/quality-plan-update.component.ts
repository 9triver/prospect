import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import QualityPlanService from './quality-plan.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import WorkbagService from '@/entities/workbag/workbag.service';
import { type IWorkbag } from '@/shared/model/workbag.model';
import { type IQualityPlan, QualityPlan } from '@/shared/model/quality-plan.model';
import { QualityType } from '@/shared/model/enumerations/quality-type.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QualityPlanUpdate',
  setup() {
    const qualityPlanService = inject('qualityPlanService', () => new QualityPlanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const qualityPlan: Ref<IQualityPlan> = ref(new QualityPlan());

    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());

    const projectwbs: Ref<IProjectwbs[]> = ref([]);

    const workbagService = inject('workbagService', () => new WorkbagService());

    const workbags: Ref<IWorkbag[]> = ref([]);
    const qualityTypeValues: Ref<string[]> = ref(Object.keys(QualityType));
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveQualityPlan = async qualityPlanId => {
      try {
        const res = await qualityPlanService().find(qualityPlanId);
        qualityPlan.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.qualityPlanId) {
      retrieveQualityPlan(route.params.qualityPlanId);
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
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      name: {},
      qualitytype: {},
      secretlevel: {},
      wbsid: {},
      workbagid: {},
      fileversion: {},
      author: {},
      attachment: {},
      projectwbs: {},
      workbag: {},
    };
    const v$ = useVuelidate(validationRules, qualityPlan as any);
    v$.value.$validate();

    return {
      qualityPlanService,
      alertService,
      qualityPlan,
      previousState,
      qualityTypeValues,
      secretlevelValues,
      isSaving,
      currentLanguage,
      projectwbs,
      workbags,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.qualityPlan.id) {
        this.qualityPlanService()
          .update(this.qualityPlan)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.qualityPlan.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.qualityPlanService()
          .create(this.qualityPlan)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.qualityPlan.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
