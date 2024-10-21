import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import RiskTypeService from './risk-type.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IRiskType, RiskType } from '@/shared/model/risk-type.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RiskTypeUpdate',
  setup() {
    const riskTypeService = inject('riskTypeService', () => new RiskTypeService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const riskType: Ref<IRiskType> = ref(new RiskType());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveRiskType = async riskTypeId => {
      try {
        const res = await riskTypeService().find(riskTypeId);
        riskType.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.riskTypeId) {
      retrieveRiskType(route.params.riskTypeId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      name: {},
    };
    const v$ = useVuelidate(validationRules, riskType as any);
    v$.value.$validate();

    return {
      riskTypeService,
      alertService,
      riskType,
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
      if (this.riskType.id) {
        this.riskTypeService()
          .update(this.riskType)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.riskType.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.riskTypeService()
          .create(this.riskType)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.riskType.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
