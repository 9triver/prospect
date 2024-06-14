import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import UnQualityAuditService from './un-quality-audit.service';
import { type IUnQualityAudit } from '@/shared/model/un-quality-audit.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'UnQualityAuditDetails',
  setup() {
    const unQualityAuditService = inject('unQualityAuditService', () => new UnQualityAuditService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const unQualityAudit: Ref<IUnQualityAudit> = ref({});

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

    return {
      alertService,
      unQualityAudit,

      previousState,
      t$: useI18n().t,
    };
  },
});
