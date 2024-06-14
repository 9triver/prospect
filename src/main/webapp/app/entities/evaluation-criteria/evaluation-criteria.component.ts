import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import EvaluationCriteriaService from './evaluation-criteria.service';
import { type IEvaluationCriteria } from '@/shared/model/evaluation-criteria.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'EvaluationCriteria',
  setup() {
    const { t: t$ } = useI18n();
    const evaluationCriteriaService = inject('evaluationCriteriaService', () => new EvaluationCriteriaService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const evaluationCriteria: Ref<IEvaluationCriteria[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveEvaluationCriterias = async () => {
      isFetching.value = true;
      try {
        const res = await evaluationCriteriaService().retrieve();
        evaluationCriteria.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveEvaluationCriterias();
    };

    onMounted(async () => {
      await retrieveEvaluationCriterias();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IEvaluationCriteria) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeEvaluationCriteria = async () => {
      try {
        await evaluationCriteriaService().delete(removeId.value);
        const message = t$('jHipster3App.evaluationCriteria.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveEvaluationCriterias();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      evaluationCriteria,
      handleSyncList,
      isFetching,
      retrieveEvaluationCriterias,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeEvaluationCriteria,
      t$,
    };
  },
});
