import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import CycleplanService from './cycleplan.service';
import { type ICycleplan } from '@/shared/model/cycleplan.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Cycleplan',
  setup() {
    const { t: t$ } = useI18n();
    const cycleplanService = inject('cycleplanService', () => new CycleplanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const cycleplans: Ref<ICycleplan[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveCycleplans = async () => {
      isFetching.value = true;
      try {
        const res = await cycleplanService().retrieve();
        cycleplans.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveCycleplans();
    };

    onMounted(async () => {
      await retrieveCycleplans();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ICycleplan) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeCycleplan = async () => {
      try {
        await cycleplanService().delete(removeId.value);
        const message = t$('jHipster3App.cycleplan.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveCycleplans();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      cycleplans,
      handleSyncList,
      isFetching,
      retrieveCycleplans,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeCycleplan,
      t$,
    };
  },
});
