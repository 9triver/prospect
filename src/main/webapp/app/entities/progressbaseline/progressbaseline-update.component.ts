import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ProgressbaselineService from './progressbaseline.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IProgressbaseline, Progressbaseline } from '@/shared/model/progressbaseline.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProgressbaselineUpdate',
  setup() {
    const progressbaselineService = inject('progressbaselineService', () => new ProgressbaselineService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const progressbaseline: Ref<IProgressbaseline> = ref(new Progressbaseline());
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveProgressbaseline = async progressbaselineId => {
      try {
        const res = await progressbaselineService().find(progressbaselineId);
        progressbaseline.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.progressbaselineId) {
      retrieveProgressbaseline(route.params.progressbaselineId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      secretlevel: {},
      requestdeportment: {},
      chargetype: {},
      chargecontent: {},
      status: {},
      version: {},
      remark: {},
    };
    const v$ = useVuelidate(validationRules, progressbaseline as any);
    v$.value.$validate();

    return {
      progressbaselineService,
      alertService,
      progressbaseline,
      previousState,
      secretlevelValues,
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
      if (this.progressbaseline.id) {
        this.progressbaselineService()
          .update(this.progressbaseline)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.progressbaseline.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.progressbaselineService()
          .create(this.progressbaseline)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.progressbaseline.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
