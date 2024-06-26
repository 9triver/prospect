import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import RiskmanagementService from './riskmanagement.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import RiskmanagementWbsService from '@/entities/riskmanagement-wbs/riskmanagement-wbs.service';
import { type IRiskmanagementWbs } from '@/shared/model/riskmanagement-wbs.model';
import { type IRiskmanagement, Riskmanagement } from '@/shared/model/riskmanagement.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RiskmanagementUpdate',
  setup() {
    const riskmanagementService = inject('riskmanagementService', () => new RiskmanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const riskmanagement: Ref<IRiskmanagement> = ref(new Riskmanagement());

    const riskmanagementWbsService = inject('riskmanagementWbsService', () => new RiskmanagementWbsService());

    const riskmanagementWbs: Ref<IRiskmanagementWbs[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveRiskmanagement = async riskmanagementId => {
      try {
        const res = await riskmanagementService().find(riskmanagementId);
        riskmanagement.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.riskmanagementId) {
      retrieveRiskmanagement(route.params.riskmanagementId);
    }

    const initRelationships = () => {
      riskmanagementWbsService()
        .retrieve()
        .then(res => {
          riskmanagementWbs.value = res.data;
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
    const v$ = useVuelidate(validationRules, riskmanagement as any);
    v$.value.$validate();

    return {
      riskmanagementService,
      alertService,
      riskmanagement,
      previousState,
      isSaving,
      currentLanguage,
      riskmanagementWbs,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.riskmanagement.id) {
        this.riskmanagementService()
          .update(this.riskmanagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.riskmanagement.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.riskmanagementService()
          .create(this.riskmanagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.riskmanagement.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
