import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import QualityPlanService from './quality-plan.service';
import { type IQualityPlan } from '@/shared/model/quality-plan.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QualityPlan',
  setup() {
    const { t: t$ } = useI18n();
    const qualityPlanService = inject('qualityPlanService', () => new QualityPlanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const qualityPlans: Ref<IQualityPlan[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveQualityPlans = async () => {
      isFetching.value = true;
      try {
        const res = await qualityPlanService().retrieve();
        qualityPlans.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveQualityPlans();
    };

    onMounted(async () => {
      await retrieveQualityPlans();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IQualityPlan) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeQualityPlan = async () => {
      try {
        await qualityPlanService().delete(removeId.value);
        const message = t$('jy1App.qualityPlan.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveQualityPlans();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      qualityPlans,
      handleSyncList,
      isFetching,
      retrieveQualityPlans,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeQualityPlan,
      t$,
    };
  },
});
