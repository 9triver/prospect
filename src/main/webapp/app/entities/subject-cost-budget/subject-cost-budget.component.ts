import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import SubjectCostBudgetService from './subject-cost-budget.service';
import { type ISubjectCostBudget } from '@/shared/model/subject-cost-budget.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SubjectCostBudget',
  setup() {
    const { t: t$ } = useI18n();
    const subjectCostBudgetService = inject('subjectCostBudgetService', () => new SubjectCostBudgetService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const subjectCostBudgets: Ref<ISubjectCostBudget[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveSubjectCostBudgets = async () => {
      isFetching.value = true;
      try {
        const res = await subjectCostBudgetService().retrieve();
        subjectCostBudgets.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveSubjectCostBudgets();
    };

    onMounted(async () => {
      await retrieveSubjectCostBudgets();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ISubjectCostBudget) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeSubjectCostBudget = async () => {
      try {
        await subjectCostBudgetService().delete(removeId.value);
        const message = t$('jy1App.subjectCostBudget.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveSubjectCostBudgets();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      subjectCostBudgets,
      handleSyncList,
      isFetching,
      retrieveSubjectCostBudgets,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeSubjectCostBudget,
      t$,
    };
  },
});
