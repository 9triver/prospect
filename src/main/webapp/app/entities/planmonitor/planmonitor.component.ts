import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import PlanmonitorService from './planmonitor.service';
import { type IPlanmonitor } from '@/shared/model/planmonitor.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Planmonitor',
  setup() {
    const { t: t$ } = useI18n();
    const planmonitorService = inject('planmonitorService', () => new PlanmonitorService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const planmonitors: Ref<IPlanmonitor[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrievePlanmonitors = async () => {
      isFetching.value = true;
      try {
        const res = await planmonitorService().retrieve();
        planmonitors.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrievePlanmonitors();
    };

    onMounted(async () => {
      await retrievePlanmonitors();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IPlanmonitor) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removePlanmonitor = async () => {
      try {
        await planmonitorService().delete(removeId.value);
        const message = t$('jHipster3App.planmonitor.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrievePlanmonitors();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      planmonitors,
      handleSyncList,
      isFetching,
      retrievePlanmonitors,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removePlanmonitor,
      t$,
    };
  },
});
