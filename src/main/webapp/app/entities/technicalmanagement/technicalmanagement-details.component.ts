import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import TechnicalmanagementService from './technicalmanagement.service';
import { type ITechnicalmanagement } from '@/shared/model/technicalmanagement.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TechnicalmanagementDetails',
  setup() {
    const technicalmanagementService = inject('technicalmanagementService', () => new TechnicalmanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const technicalmanagement: Ref<ITechnicalmanagement> = ref({});

    const retrieveTechnicalmanagement = async technicalmanagementId => {
      try {
        const res = await technicalmanagementService().find(technicalmanagementId);
        technicalmanagement.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.technicalmanagementId) {
      retrieveTechnicalmanagement(route.params.technicalmanagementId);
    }

    return {
      alertService,
      technicalmanagement,

      previousState,
      t$: useI18n().t,
    };
  },
});
