import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import QualityObjectivesDictionaryService from './quality-objectives-dictionary.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IQualityObjectivesDictionary, QualityObjectivesDictionary } from '@/shared/model/quality-objectives-dictionary.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QualityObjectivesDictionaryUpdate',
  setup() {
    const qualityObjectivesDictionaryService = inject('qualityObjectivesDictionaryService', () => new QualityObjectivesDictionaryService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const qualityObjectivesDictionary: Ref<IQualityObjectivesDictionary> = ref(new QualityObjectivesDictionary());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveQualityObjectivesDictionary = async qualityObjectivesDictionaryId => {
      try {
        const res = await qualityObjectivesDictionaryService().find(qualityObjectivesDictionaryId);
        qualityObjectivesDictionary.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.qualityObjectivesDictionaryId) {
      retrieveQualityObjectivesDictionary(route.params.qualityObjectivesDictionaryId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      objectiveslevel: {},
      objectivestype: {},
      objectivesname: {},
      objectivescontent: {},
      calculationmethod: {},
      frequency: {},
      evaluationcriteria: {},
    };
    const v$ = useVuelidate(validationRules, qualityObjectivesDictionary as any);
    v$.value.$validate();

    return {
      qualityObjectivesDictionaryService,
      alertService,
      qualityObjectivesDictionary,
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
      if (this.qualityObjectivesDictionary.id) {
        this.qualityObjectivesDictionaryService()
          .update(this.qualityObjectivesDictionary)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.qualityObjectivesDictionary.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.qualityObjectivesDictionaryService()
          .create(this.qualityObjectivesDictionary)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.qualityObjectivesDictionary.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
