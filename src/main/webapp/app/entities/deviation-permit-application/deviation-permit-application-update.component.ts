import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import DeviationPermitApplicationService from './deviation-permit-application.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { type IDeviationPermitApplication, DeviationPermitApplication } from '@/shared/model/deviation-permit-application.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'DeviationPermitApplicationUpdate',
  setup() {
    const deviationPermitApplicationService = inject('deviationPermitApplicationService', () => new DeviationPermitApplicationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const deviationPermitApplication: Ref<IDeviationPermitApplication> = ref(new DeviationPermitApplication());

    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());

    const projectwbs: Ref<IProjectwbs[]> = ref([]);
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveDeviationPermitApplication = async deviationPermitApplicationId => {
      try {
        const res = await deviationPermitApplicationService().find(deviationPermitApplicationId);
        deviationPermitApplication.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.deviationPermitApplicationId) {
      retrieveDeviationPermitApplication(route.params.deviationPermitApplicationId);
    }

    const initRelationships = () => {
      projectwbsService()
        .retrieve()
        .then(res => {
          projectwbs.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      wbsid: {},
      technicalfileid: {},
      applicationunit: {},
      applicant: {},
      applicationdate: {},
      permitcontent: {},
      permitreason: {},
      projectinfluence: {},
      contractinfluence: {},
      permitrange: {},
      implementationdate: {},
      remarks: {},
      auditStatus: {},
      projectwbs: {},
    };
    const v$ = useVuelidate(validationRules, deviationPermitApplication as any);
    v$.value.$validate();

    return {
      deviationPermitApplicationService,
      alertService,
      deviationPermitApplication,
      previousState,
      auditStatusValues,
      isSaving,
      currentLanguage,
      projectwbs,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.deviationPermitApplication.id) {
        this.deviationPermitApplicationService()
          .update(this.deviationPermitApplication)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.deviationPermitApplication.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.deviationPermitApplicationService()
          .create(this.deviationPermitApplication)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.deviationPermitApplication.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
