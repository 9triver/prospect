import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import CostControlSystemService from './cost-control-system.service';
import { type ICostControlSystem } from '@/shared/model/cost-control-system.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CostControlSystem',
  setup() {
    const { t: t$ } = useI18n();
    const costControlSystemService = inject('costControlSystemService', () => new CostControlSystemService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const costControlSystems: Ref<ICostControlSystem[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveCostControlSystems = async () => {
      isFetching.value = true;
      try {
        const res = await costControlSystemService().retrieve();
        costControlSystems.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveCostControlSystems();
    };

    onMounted(async () => {
      await retrieveCostControlSystems();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ICostControlSystem) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeCostControlSystem = async () => {
      try {
        await costControlSystemService().delete(removeId.value);
        const message = t$('jy1App.costControlSystem.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveCostControlSystems();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      costControlSystems,
      handleSyncList,
      isFetching,
      retrieveCostControlSystems,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeCostControlSystem,
      t$,
    };
  },
});
