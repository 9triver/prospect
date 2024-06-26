import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import TechnicalmanagementWbsService from './technicalmanagement-wbs.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import TechnicalConditionService from '@/entities/technical-condition/technical-condition.service';
import { type ITechnicalCondition } from '@/shared/model/technical-condition.model';
import { type ITechnicalmanagementWbs, TechnicalmanagementWbs } from '@/shared/model/technicalmanagement-wbs.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TechnicalmanagementWbsUpdate',
  setup() {
    const technicalmanagementWbsService = inject('technicalmanagementWbsService', () => new TechnicalmanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const technicalmanagementWbs: Ref<ITechnicalmanagementWbs> = ref(new TechnicalmanagementWbs());

    const technicalConditionService = inject('technicalConditionService', () => new TechnicalConditionService());

    const technicalConditions: Ref<ITechnicalCondition[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveTechnicalmanagementWbs = async technicalmanagementWbsId => {
      try {
        const res = await technicalmanagementWbsService().find(technicalmanagementWbsId);
        technicalmanagementWbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.technicalmanagementWbsId) {
      retrieveTechnicalmanagementWbs(route.params.technicalmanagementWbsId);
    }

    const initRelationships = () => {
      technicalConditionService()
        .retrieve()
        .then(res => {
          technicalConditions.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      wbsspare1: {},
      wbsspare2: {},
      wbsspare3: {},
      wbsspare4: {},
      wbsspare5: {},
      technicalCondition: {},
    };
    const v$ = useVuelidate(validationRules, technicalmanagementWbs as any);
    v$.value.$validate();

    return {
      technicalmanagementWbsService,
      alertService,
      technicalmanagementWbs,
      previousState,
      isSaving,
      currentLanguage,
      technicalConditions,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.technicalmanagementWbs.id) {
        this.technicalmanagementWbsService()
          .update(this.technicalmanagementWbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.technicalmanagementWbs.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.technicalmanagementWbsService()
          .create(this.technicalmanagementWbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.technicalmanagementWbs.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
