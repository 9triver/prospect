import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import TechnicalmanagementService from './technicalmanagement.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import TechnicalmanagementWbsService from '@/entities/technicalmanagement-wbs/technicalmanagement-wbs.service';
import { type ITechnicalmanagementWbs } from '@/shared/model/technicalmanagement-wbs.model';
import { type ITechnicalmanagement, Technicalmanagement } from '@/shared/model/technicalmanagement.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TechnicalmanagementUpdate',
  setup() {
    const technicalmanagementService = inject('technicalmanagementService', () => new TechnicalmanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const technicalmanagement: Ref<ITechnicalmanagement> = ref(new Technicalmanagement());

    const technicalmanagementWbsService = inject('technicalmanagementWbsService', () => new TechnicalmanagementWbsService());

    const technicalmanagementWbs: Ref<ITechnicalmanagementWbs[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveTechnicalmanagement = async technicalmanagementId => {
      try {
        const res = await technicalmanagementService().find(technicalmanagementId);
        technicalmanagement.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.technicalmanagementId) {
      retrieveTechnicalmanagement(route.params.technicalmanagementId);
    }

    const initRelationships = () => {
      technicalmanagementWbsService()
        .retrieve()
        .then(res => {
          technicalmanagementWbs.value = res.data;
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
    const v$ = useVuelidate(validationRules, technicalmanagement as any);
    v$.value.$validate();

    return {
      technicalmanagementService,
      alertService,
      technicalmanagement,
      previousState,
      isSaving,
      currentLanguage,
      technicalmanagementWbs,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.technicalmanagement.id) {
        this.technicalmanagementService()
          .update(this.technicalmanagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.technicalmanagement.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.technicalmanagementService()
          .create(this.technicalmanagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.technicalmanagement.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
