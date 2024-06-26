import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import AuditedbudgetService from './auditedbudget.service';
import { type IAuditedbudget } from '@/shared/model/auditedbudget.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'AuditedbudgetDetails',
  setup() {
    const auditedbudgetService = inject('auditedbudgetService', () => new AuditedbudgetService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const auditedbudget: Ref<IAuditedbudget> = ref({});

    const retrieveAuditedbudget = async auditedbudgetId => {
      try {
        const res = await auditedbudgetService().find(auditedbudgetId);
        auditedbudget.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.auditedbudgetId) {
      retrieveAuditedbudget(route.params.auditedbudgetId);
    }

    return {
      alertService,
      auditedbudget,

      previousState,
      t$: useI18n().t,
    };
  },
});
