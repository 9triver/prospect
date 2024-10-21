import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import RiskLevelService from './risk-level.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IRiskLevel, RiskLevel } from '@/shared/model/risk-level.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RiskLevelUpdate',
  setup() {
    const riskLevelService = inject('riskLevelService', () => new RiskLevelService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const riskLevel: Ref<IRiskLevel> = ref(new RiskLevel());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveRiskLevel = async riskLevelId => {
      try {
        const res = await riskLevelService().find(riskLevelId);
        riskLevel.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.riskLevelId) {
      retrieveRiskLevel(route.params.riskLevelId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      name: {},
    };
    const v$ = useVuelidate(validationRules, riskLevel as any);
    v$.value.$validate();

    return {
      riskLevelService,
      alertService,
      riskLevel,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.riskLevel.id) {
        this.riskLevelService()
          .update(this.riskLevel)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.riskLevel.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.riskLevelService()
          .create(this.riskLevel)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.riskLevel.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
