import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import UnQualityAuditService from './un-quality-audit.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IUnQualityAudit, UnQualityAudit } from '@/shared/model/un-quality-audit.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'UnQualityAuditUpdate',
  setup() {
    const unQualityAuditService = inject('unQualityAuditService', () => new UnQualityAuditService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const unQualityAudit: Ref<IUnQualityAudit> = ref(new UnQualityAudit());
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveUnQualityAudit = async unQualityAuditId => {
      try {
        const res = await unQualityAuditService().find(unQualityAuditId);
        unQualityAudit.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.unQualityAuditId) {
      retrieveUnQualityAudit(route.params.unQualityAuditId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      unqualityname: {},
      unqualitytype: {},
      belongunitid: {},
      belongunitname: {},
      auditteam: {},
      auditperson: {},
      unqualitynum: {},
      creatorname: {},
      auditStatus: {},
    };
    const v$ = useVuelidate(validationRules, unQualityAudit as any);
    v$.value.$validate();

    return {
      unQualityAuditService,
      alertService,
      unQualityAudit,
      previousState,
      auditStatusValues,
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
      if (this.unQualityAudit.id) {
        this.unQualityAuditService()
          .update(this.unQualityAudit)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.unQualityAudit.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.unQualityAuditService()
          .create(this.unQualityAudit)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.unQualityAudit.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
