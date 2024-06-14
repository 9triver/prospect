import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import EvaluationCriteriaService from './evaluation-criteria.service';
import { type IEvaluationCriteria } from '@/shared/model/evaluation-criteria.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'EvaluationCriteriaDetails',
  setup() {
    const evaluationCriteriaService = inject('evaluationCriteriaService', () => new EvaluationCriteriaService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const evaluationCriteria: Ref<IEvaluationCriteria> = ref({});

    const retrieveEvaluationCriteria = async evaluationCriteriaId => {
      try {
        const res = await evaluationCriteriaService().find(evaluationCriteriaId);
        evaluationCriteria.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.evaluationCriteriaId) {
      retrieveEvaluationCriteria(route.params.evaluationCriteriaId);
    }

    return {
      alertService,
      evaluationCriteria,

      previousState,
      t$: useI18n().t,
    };
  },
});
