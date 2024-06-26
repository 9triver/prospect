import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import QualitymanagementService from './qualitymanagement.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import QualitymanagementWbsService from '@/entities/qualitymanagement-wbs/qualitymanagement-wbs.service';
import { type IQualitymanagementWbs } from '@/shared/model/qualitymanagement-wbs.model';
import { type IQualitymanagement, Qualitymanagement } from '@/shared/model/qualitymanagement.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QualitymanagementUpdate',
  setup() {
    const qualitymanagementService = inject('qualitymanagementService', () => new QualitymanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const qualitymanagement: Ref<IQualitymanagement> = ref(new Qualitymanagement());

    const qualitymanagementWbsService = inject('qualitymanagementWbsService', () => new QualitymanagementWbsService());

    const qualitymanagementWbs: Ref<IQualitymanagementWbs[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveQualitymanagement = async qualitymanagementId => {
      try {
        const res = await qualitymanagementService().find(qualitymanagementId);
        qualitymanagement.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.qualitymanagementId) {
      retrieveQualitymanagement(route.params.qualitymanagementId);
    }

    const initRelationships = () => {
      qualitymanagementWbsService()
        .retrieve()
        .then(res => {
          qualitymanagementWbs.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      name: {},
      description: {},
      starttime: {},
      endtime: {},
      wbs: {},
    };
    const v$ = useVuelidate(validationRules, qualitymanagement as any);
    v$.value.$validate();

    return {
      qualitymanagementService,
      alertService,
      qualitymanagement,
      previousState,
      isSaving,
      currentLanguage,
      qualitymanagementWbs,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.qualitymanagement.id) {
        this.qualitymanagementService()
          .update(this.qualitymanagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.qualitymanagement.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.qualitymanagementService()
          .create(this.qualitymanagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.qualitymanagement.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
