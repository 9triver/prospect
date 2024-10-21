import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import SystemLevelService from './system-level.service';
import { type ISystemLevel } from '@/shared/model/system-level.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SystemLevel',
  setup() {
    const { t: t$ } = useI18n();
    const systemLevelService = inject('systemLevelService', () => new SystemLevelService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const systemLevels: Ref<ISystemLevel[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveSystemLevels = async () => {
      isFetching.value = true;
      try {
        const res = await systemLevelService().retrieve();
        systemLevels.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveSystemLevels();
    };

    onMounted(async () => {
      await retrieveSystemLevels();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ISystemLevel) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeSystemLevel = async () => {
      try {
        await systemLevelService().delete(removeId.value);
        const message = t$('jy1App.systemLevel.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveSystemLevels();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      systemLevels,
      handleSyncList,
      isFetching,
      retrieveSystemLevels,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeSystemLevel,
      t$,
    };
  },
});
