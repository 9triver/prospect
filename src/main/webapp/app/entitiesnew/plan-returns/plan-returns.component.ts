import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import PlanReturnsService from './plan-returns.service';
import { type IPlanReturns } from '@/shared/model/plan-returns.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PlanReturns',
  setup() {
    const { t: t$ } = useI18n();
    const planReturnsService = inject('planReturnsService', () => new PlanReturnsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const planReturns: Ref<IPlanReturns[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrievePlanReturnss = async () => {
      isFetching.value = true;
      try {
        const res = await planReturnsService().retrieve();
        planReturns.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrievePlanReturnss();
    };

    onMounted(async () => {
      await retrievePlanReturnss();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IPlanReturns) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removePlanReturns = async () => {
      try {
        await planReturnsService().delete(removeId.value);
        const message = t$('jy1App.planReturns.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrievePlanReturnss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      planReturns,
      handleSyncList,
      isFetching,
      retrievePlanReturnss,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removePlanReturns,
      t$,
    };
  },
});
