import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import SecuritymanagementWbsService from './securitymanagement-wbs.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import AnnualSecurityPlanService from '@/entities/annual-security-plan/annual-security-plan.service';
import { type IAnnualSecurityPlan } from '@/shared/model/annual-security-plan.model';
import SafetycheckService from '@/entities/safetycheck/safetycheck.service';
import { type ISafetycheck } from '@/shared/model/safetycheck.model';
import { type ISecuritymanagementWbs, SecuritymanagementWbs } from '@/shared/model/securitymanagement-wbs.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SecuritymanagementWbsUpdate',
  setup() {
    const securitymanagementWbsService = inject('securitymanagementWbsService', () => new SecuritymanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const securitymanagementWbs: Ref<ISecuritymanagementWbs> = ref(new SecuritymanagementWbs());

    const annualSecurityPlanService = inject('annualSecurityPlanService', () => new AnnualSecurityPlanService());

    const annualSecurityPlans: Ref<IAnnualSecurityPlan[]> = ref([]);

    const safetycheckService = inject('safetycheckService', () => new SafetycheckService());

    const safetychecks: Ref<ISafetycheck[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveSecuritymanagementWbs = async securitymanagementWbsId => {
      try {
        const res = await securitymanagementWbsService().find(securitymanagementWbsId);
        securitymanagementWbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.securitymanagementWbsId) {
      retrieveSecuritymanagementWbs(route.params.securitymanagementWbsId);
    }

    const initRelationships = () => {
      annualSecurityPlanService()
        .retrieve()
        .then(res => {
          annualSecurityPlans.value = res.data;
        });
      safetycheckService()
        .retrieve()
        .then(res => {
          safetychecks.value = res.data;
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
      annualSecurityPlan: {},
      safetycheck: {},
    };
    const v$ = useVuelidate(validationRules, securitymanagementWbs as any);
    v$.value.$validate();

    return {
      securitymanagementWbsService,
      alertService,
      securitymanagementWbs,
      previousState,
      isSaving,
      currentLanguage,
      annualSecurityPlans,
      safetychecks,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.securitymanagementWbs.id) {
        this.securitymanagementWbsService()
          .update(this.securitymanagementWbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.securitymanagementWbs.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.securitymanagementWbsService()
          .create(this.securitymanagementWbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.securitymanagementWbs.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
