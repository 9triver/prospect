import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ProgressmanagementService from './progressmanagement.service';
import { type IProgressmanagement } from '@/shared/model/progressmanagement.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProgressmanagementDetails',
  setup() {
    const progressmanagementService = inject('progressmanagementService', () => new ProgressmanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const progressmanagement: Ref<IProgressmanagement> = ref({});

    const retrieveProgressmanagement = async progressmanagementId => {
      try {
        const res = await progressmanagementService().find(progressmanagementId);
        progressmanagement.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.progressmanagementId) {
      retrieveProgressmanagement(route.params.progressmanagementId);
    }

    return {
      alertService,
      progressmanagement,

      previousState,
      t$: useI18n().t,
    };
  },
});
