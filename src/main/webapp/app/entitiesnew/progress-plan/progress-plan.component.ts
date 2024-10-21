import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ProgressPlanService from './progress-plan.service';
import { type IProgressPlan } from '@/shared/model/progress-plan.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProgressPlan',
  setup() {
    const { t: t$ } = useI18n();
    const progressPlanService = inject('progressPlanService', () => new ProgressPlanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const progressPlans: Ref<IProgressPlan[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveProgressPlans = async () => {
      isFetching.value = true;
      try {
        const res = await progressPlanService().retrieve();
        progressPlans.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveProgressPlans();
    };

    onMounted(async () => {
      await retrieveProgressPlans();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IProgressPlan) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeProgressPlan = async () => {
      try {
        await progressPlanService().delete(removeId.value);
        const message = t$('jy1App.progressPlan.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveProgressPlans();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      progressPlans,
      handleSyncList,
      isFetching,
      retrieveProgressPlans,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeProgressPlan,
      t$,
    };
  },
});
