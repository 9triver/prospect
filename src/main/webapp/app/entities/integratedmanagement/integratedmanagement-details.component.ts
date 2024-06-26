import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import IntegratedmanagementService from './integratedmanagement.service';
import { type IIntegratedmanagement } from '@/shared/model/integratedmanagement.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'IntegratedmanagementDetails',
  setup() {
    const integratedmanagementService = inject('integratedmanagementService', () => new IntegratedmanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const integratedmanagement: Ref<IIntegratedmanagement> = ref({});

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

    return {
      alertService,
      integratedmanagement,

      previousState,
      t$: useI18n().t,
    };
  },
});
