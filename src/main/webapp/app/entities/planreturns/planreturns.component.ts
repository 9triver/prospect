import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import PlanreturnsService from './planreturns.service';
import { type IPlanreturns } from '@/shared/model/planreturns.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Planreturns',
  setup() {
    const { t: t$ } = useI18n();
    const planreturnsService = inject('planreturnsService', () => new PlanreturnsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const planreturns: Ref<IPlanreturns[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrievePlanreturnss = async () => {
      isFetching.value = true;
      try {
        const res = await planreturnsService().retrieve();
        planreturns.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrievePlanreturnss();
    };

    onMounted(async () => {
      await retrievePlanreturnss();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IPlanreturns) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removePlanreturns = async () => {
      try {
        await planreturnsService().delete(removeId.value);
        const message = t$('jHipster0App.planreturns.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrievePlanreturnss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      planreturns,
      handleSyncList,
      isFetching,
      retrievePlanreturnss,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removePlanreturns,
      t$,
    };
  },
});
