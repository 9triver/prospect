import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ProjectBudgetService from './project-budget.service';
import { type IProjectBudget } from '@/shared/model/project-budget.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectBudgetDetails',
  setup() {
    const projectBudgetService = inject('projectBudgetService', () => new ProjectBudgetService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const projectBudget: Ref<IProjectBudget> = ref({});

    const retrieveProjectBudget = async projectBudgetId => {
      try {
        const res = await projectBudgetService().find(projectBudgetId);
        projectBudget.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.projectBudgetId) {
      retrieveProjectBudget(route.params.projectBudgetId);
    }

    return {
      alertService,
      projectBudget,

      previousState,
      t$: useI18n().t,
    };
  },
});
