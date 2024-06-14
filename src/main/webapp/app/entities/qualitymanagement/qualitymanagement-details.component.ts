import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import QualitymanagementService from './qualitymanagement.service';
import { type IQualitymanagement } from '@/shared/model/qualitymanagement.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QualitymanagementDetails',
  setup() {
    const qualitymanagementService = inject('qualitymanagementService', () => new QualitymanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const qualitymanagement: Ref<IQualitymanagement> = ref({});

    const retrieveQualitymanagement = async qualitymanagementId => {
      try {
        const res = await qualitymanagementService().find(qualitymanagementId);
        qualitymanagement.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.qualitymanagementId) {
      retrieveQualitymanagement(route.params.qualitymanagementId);
    }

    return {
      alertService,
      qualitymanagement,

      previousState,
      t$: useI18n().t,
    };
  },
});
