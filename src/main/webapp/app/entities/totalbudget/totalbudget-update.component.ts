import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import TotalbudgetService from './totalbudget.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type ITotalbudget, Totalbudget } from '@/shared/model/totalbudget.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TotalbudgetUpdate',
  setup() {
    const totalbudgetService = inject('totalbudgetService', () => new TotalbudgetService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const totalbudget: Ref<ITotalbudget> = ref(new Totalbudget());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveTotalbudget = async totalbudgetId => {
      try {
        const res = await totalbudgetService().find(totalbudgetId);
        totalbudget.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.totalbudgetId) {
      retrieveTotalbudget(route.params.totalbudgetId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      totalbudgetid: {},
      valuationsubjects: {},
      budget: {},
      percentage: {},
      remarks: {},
    };
    const v$ = useVuelidate(validationRules, totalbudget as any);
    v$.value.$validate();

    return {
      totalbudgetService,
      alertService,
      totalbudget,
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
      if (this.totalbudget.id) {
        this.totalbudgetService()
          .update(this.totalbudget)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster3App.totalbudget.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.totalbudgetService()
          .create(this.totalbudget)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster3App.totalbudget.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
