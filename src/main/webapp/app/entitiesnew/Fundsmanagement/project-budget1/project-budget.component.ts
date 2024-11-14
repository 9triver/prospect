import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ProjectBudgetService from './project-budget.service';
import { type IProjectBudget } from '@/shared/model/project-budget.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectBudget',
  setup() {
    const { t: t$ } = useI18n();
    const projectBudgetService = inject('projectBudgetService', () => new ProjectBudgetService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const projectBudgets: Ref<IProjectBudget[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveProjectBudgets = async () => {
      isFetching.value = true;
      try {
        const res = await projectBudgetService().retrieve();
        projectBudgets.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveProjectBudgets();
    };

    onMounted(async () => {
      await retrieveProjectBudgets();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IProjectBudget) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeProjectBudget = async () => {
      try {
        await projectBudgetService().delete(removeId.value);
        const message = t$('jy1App.projectBudget.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveProjectBudgets();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      projectBudgets,
      handleSyncList,
      isFetching,
      retrieveProjectBudgets,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeProjectBudget,
      t$,
    };
  },
});
