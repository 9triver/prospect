import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ComprehensiveledgerService from './comprehensiveledger.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IComprehensiveledger, Comprehensiveledger } from '@/shared/model/comprehensiveledger.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ComprehensiveledgerUpdate',
  setup() {
    const comprehensiveledgerService = inject('comprehensiveledgerService', () => new ComprehensiveledgerService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const comprehensiveledger: Ref<IComprehensiveledger> = ref(new Comprehensiveledger());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveComprehensiveledger = async comprehensiveledgerId => {
      try {
        const res = await comprehensiveledgerService().find(comprehensiveledgerId);
        comprehensiveledger.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.comprehensiveledgerId) {
      retrieveComprehensiveledger(route.params.comprehensiveledgerId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      fundsname: {},
      wbsname: {},
      unitname: {},
      budgetsection: {},
      purpose: {},
      unit: {},
      number: {},
      unitprice: {},
      foreignexchange: {},
    };
    const v$ = useVuelidate(validationRules, comprehensiveledger as any);
    v$.value.$validate();

    return {
      comprehensiveledgerService,
      alertService,
      comprehensiveledger,
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
      if (this.comprehensiveledger.id) {
        this.comprehensiveledgerService()
          .update(this.comprehensiveledger)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.comprehensiveledger.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.comprehensiveledgerService()
          .create(this.comprehensiveledger)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.comprehensiveledger.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
