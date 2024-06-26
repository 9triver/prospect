import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import RiskmanagementWbsService from './riskmanagement-wbs.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import RiskidentificationService from '@/entities/riskidentification/riskidentification.service';
import { type IRiskidentification } from '@/shared/model/riskidentification.model';
import RiskreportService from '@/entities/riskreport/riskreport.service';
import { type IRiskreport } from '@/shared/model/riskreport.model';
import { type IRiskmanagementWbs, RiskmanagementWbs } from '@/shared/model/riskmanagement-wbs.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RiskmanagementWbsUpdate',
  setup() {
    const riskmanagementWbsService = inject('riskmanagementWbsService', () => new RiskmanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const riskmanagementWbs: Ref<IRiskmanagementWbs> = ref(new RiskmanagementWbs());

    const riskidentificationService = inject('riskidentificationService', () => new RiskidentificationService());

    const riskidentifications: Ref<IRiskidentification[]> = ref([]);

    const riskreportService = inject('riskreportService', () => new RiskreportService());

    const riskreports: Ref<IRiskreport[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveRiskmanagementWbs = async riskmanagementWbsId => {
      try {
        const res = await riskmanagementWbsService().find(riskmanagementWbsId);
        riskmanagementWbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.riskmanagementWbsId) {
      retrieveRiskmanagementWbs(route.params.riskmanagementWbsId);
    }

    const initRelationships = () => {
      riskidentificationService()
        .retrieve()
        .then(res => {
          riskidentifications.value = res.data;
        });
      riskreportService()
        .retrieve()
        .then(res => {
          riskreports.value = res.data;
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
      riskidentification: {},
      riskreport: {},
    };
    const v$ = useVuelidate(validationRules, riskmanagementWbs as any);
    v$.value.$validate();

    return {
      riskmanagementWbsService,
      alertService,
      riskmanagementWbs,
      previousState,
      isSaving,
      currentLanguage,
      riskidentifications,
      riskreports,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.riskmanagementWbs.id) {
        this.riskmanagementWbsService()
          .update(this.riskmanagementWbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.riskmanagementWbs.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.riskmanagementWbsService()
          .create(this.riskmanagementWbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.riskmanagementWbs.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
