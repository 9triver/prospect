import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ManagementCapacityEvaluationService from './management-capacity-evaluation.service';
import { type IManagementCapacityEvaluation } from '@/shared/model/management-capacity-evaluation.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ManagementCapacityEvaluationDetails',
  setup() {
    const managementCapacityEvaluationService = inject(
      'managementCapacityEvaluationService',
      () => new ManagementCapacityEvaluationService(),
    );
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const managementCapacityEvaluation: Ref<IManagementCapacityEvaluation> = ref({});

    const retrieveManagementCapacityEvaluation = async managementCapacityEvaluationId => {
      try {
        const res = await managementCapacityEvaluationService().find(managementCapacityEvaluationId);
        managementCapacityEvaluation.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.managementCapacityEvaluationId) {
      retrieveManagementCapacityEvaluation(route.params.managementCapacityEvaluationId);
    }

    return {
      alertService,
      managementCapacityEvaluation,

      previousState,
      t$: useI18n().t,
    };
  },
});
