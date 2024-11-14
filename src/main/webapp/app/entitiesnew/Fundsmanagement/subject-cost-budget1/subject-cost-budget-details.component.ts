import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import SubjectCostBudgetService from './subject-cost-budget.service';
import { type ISubjectCostBudget } from '@/shared/model/subject-cost-budget.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SubjectCostBudgetDetails',
  setup() {
    const subjectCostBudgetService = inject('subjectCostBudgetService', () => new SubjectCostBudgetService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const subjectCostBudget: Ref<ISubjectCostBudget> = ref({});

    const retrieveSubjectCostBudget = async subjectCostBudgetId => {
      try {
        const res = await subjectCostBudgetService().find(subjectCostBudgetId);
        subjectCostBudget.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.subjectCostBudgetId) {
      retrieveSubjectCostBudget(route.params.subjectCostBudgetId);
    }

    return {
      alertService,
      subjectCostBudget,

      previousState,
      t$: useI18n().t,
    };
  },
});
