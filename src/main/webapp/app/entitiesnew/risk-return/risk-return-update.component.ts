import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import RiskReturnService from './risk-return.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProjectRiskService from '@/entities/project-risk/project-risk.service';
import { type IProjectRisk } from '@/shared/model/project-risk.model';
import HrManagementService from '@/entities/hr-management/hr-management.service';
import { type IHrManagement } from '@/shared/model/hr-management.model';
import { type IRiskReturn, RiskReturn } from '@/shared/model/risk-return.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RiskReturnUpdate',
  setup() {
    const riskReturnService = inject('riskReturnService', () => new RiskReturnService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const riskReturn: Ref<IRiskReturn> = ref(new RiskReturn());

    const projectRiskService = inject('projectRiskService', () => new ProjectRiskService());

    const projectRisks: Ref<IProjectRisk[]> = ref([]);

    const hrManagementService = inject('hrManagementService', () => new HrManagementService());

    const hrManagements: Ref<IHrManagement[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveRiskReturn = async riskReturnId => {
      try {
        const res = await riskReturnService().find(riskReturnId);
        riskReturn.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.riskReturnId) {
      retrieveRiskReturn(route.params.riskReturnId);
    }

    const initRelationships = () => {
      projectRiskService()
        .retrieve()
        .then(res => {
          projectRisks.value = res.data;
        });
      hrManagementService()
        .retrieve()
        .then(res => {
          hrManagements.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      belongriskid: {},
      status: {},
      closestatus: {},
      evidencefile: {},
      riskid: {},
      creatorid: {},
    };
    const v$ = useVuelidate(validationRules, riskReturn as any);
    v$.value.$validate();

    return {
      riskReturnService,
      alertService,
      riskReturn,
      previousState,
      isSaving,
      currentLanguage,
      projectRisks,
      hrManagements,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.riskReturn.id) {
        this.riskReturnService()
          .update(this.riskReturn)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.riskReturn.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.riskReturnService()
          .create(this.riskReturn)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.riskReturn.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
