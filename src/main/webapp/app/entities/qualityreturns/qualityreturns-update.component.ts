import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import QualityreturnsService from './qualityreturns.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IQualityreturns, Qualityreturns } from '@/shared/model/qualityreturns.model';
import { ReturnsStatus } from '@/shared/model/enumerations/returns-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QualityreturnsUpdate',
  setup() {
    const qualityreturnsService = inject('qualityreturnsService', () => new QualityreturnsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const qualityreturns: Ref<IQualityreturns> = ref(new Qualityreturns());
    const returnsStatusValues: Ref<string[]> = ref(Object.keys(ReturnsStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveQualityreturns = async qualityreturnsId => {
      try {
        const res = await qualityreturnsService().find(qualityreturnsId);
        qualityreturns.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.qualityreturnsId) {
      retrieveQualityreturns(route.params.qualityreturnsId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      qualityreturnsid: {
        integer: validations.integer(t$('entity.validation.number').toString()),
      },
      qualityreturnsname: {},
      starttime: {},
      endtime: {},
      qualitytype: {},
      returnstime: {},
      returnsstatus: {},
    };
    const v$ = useVuelidate(validationRules, qualityreturns as any);
    v$.value.$validate();

    return {
      qualityreturnsService,
      alertService,
      qualityreturns,
      previousState,
      returnsStatusValues,
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
      if (this.qualityreturns.id) {
        this.qualityreturnsService()
          .update(this.qualityreturns)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster3App.qualityreturns.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.qualityreturnsService()
          .create(this.qualityreturns)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster3App.qualityreturns.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
