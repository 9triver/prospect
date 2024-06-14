import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ManagementCapacityEvaluationService from './management-capacity-evaluation.service';
import { type IManagementCapacityEvaluation } from '@/shared/model/management-capacity-evaluation.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ManagementCapacityEvaluation',
  setup() {
    const { t: t$ } = useI18n();
    const managementCapacityEvaluationService = inject(
      'managementCapacityEvaluationService',
      () => new ManagementCapacityEvaluationService(),
    );
    const alertService = inject('alertService', () => useAlertService(), true);

    const managementCapacityEvaluations: Ref<IManagementCapacityEvaluation[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveManagementCapacityEvaluations = async () => {
      isFetching.value = true;
      try {
        const res = await managementCapacityEvaluationService().retrieve();
        managementCapacityEvaluations.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveManagementCapacityEvaluations();
    };

    onMounted(async () => {
      await retrieveManagementCapacityEvaluations();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IManagementCapacityEvaluation) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeManagementCapacityEvaluation = async () => {
      try {
        await managementCapacityEvaluationService().delete(removeId.value);
        const message = t$('jHipster3App.managementCapacityEvaluation.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveManagementCapacityEvaluations();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      managementCapacityEvaluations,
      handleSyncList,
      isFetching,
      retrieveManagementCapacityEvaluations,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeManagementCapacityEvaluation,
      t$,
    };
  },
});
