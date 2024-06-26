import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import IntegratedmanagementService from './integratedmanagement.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import IntegratedmanagementWbsService from '@/entities/integratedmanagement-wbs/integratedmanagement-wbs.service';
import { type IIntegratedmanagementWbs } from '@/shared/model/integratedmanagement-wbs.model';
import { type IIntegratedmanagement, Integratedmanagement } from '@/shared/model/integratedmanagement.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'IntegratedmanagementUpdate',
  setup() {
    const integratedmanagementService = inject('integratedmanagementService', () => new IntegratedmanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const integratedmanagement: Ref<IIntegratedmanagement> = ref(new Integratedmanagement());

    const integratedmanagementWbsService = inject('integratedmanagementWbsService', () => new IntegratedmanagementWbsService());

    const integratedmanagementWbs: Ref<IIntegratedmanagementWbs[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveIntegratedmanagement = async integratedmanagementId => {
      try {
        const res = await integratedmanagementService().find(integratedmanagementId);
        integratedmanagement.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.integratedmanagementId) {
      retrieveIntegratedmanagement(route.params.integratedmanagementId);
    }

    const initRelationships = () => {
      integratedmanagementWbsService()
        .retrieve()
        .then(res => {
          integratedmanagementWbs.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      name: {},
      description: {},
      wbs: {},
    };
    const v$ = useVuelidate(validationRules, integratedmanagement as any);
    v$.value.$validate();

    return {
      integratedmanagementService,
      alertService,
      integratedmanagement,
      previousState,
      isSaving,
      currentLanguage,
      integratedmanagementWbs,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.integratedmanagement.id) {
        this.integratedmanagementService()
          .update(this.integratedmanagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.integratedmanagement.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.integratedmanagementService()
          .create(this.integratedmanagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.integratedmanagement.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
