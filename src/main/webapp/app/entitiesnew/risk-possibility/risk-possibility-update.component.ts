import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import RiskPossibilityService from './risk-possibility.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IRiskPossibility, RiskPossibility } from '@/shared/model/risk-possibility.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RiskPossibilityUpdate',
  setup() {
    const riskPossibilityService = inject('riskPossibilityService', () => new RiskPossibilityService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const riskPossibility: Ref<IRiskPossibility> = ref(new RiskPossibility());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveRiskPossibility = async riskPossibilityId => {
      try {
        const res = await riskPossibilityService().find(riskPossibilityId);
        riskPossibility.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.riskPossibilityId) {
      retrieveRiskPossibility(route.params.riskPossibilityId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      name: {},
    };
    const v$ = useVuelidate(validationRules, riskPossibility as any);
    v$.value.$validate();

    return {
      riskPossibilityService,
      alertService,
      riskPossibility,
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
      if (this.riskPossibility.id) {
        this.riskPossibilityService()
          .update(this.riskPossibility)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.riskPossibility.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.riskPossibilityService()
          .create(this.riskPossibility)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.riskPossibility.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
