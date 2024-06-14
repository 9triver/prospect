import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import WbsbaselineService from './wbsbaseline.service';
import { type IWbsbaseline } from '@/shared/model/wbsbaseline.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Wbsbaseline',
  setup() {
    const { t: t$ } = useI18n();
    const wbsbaselineService = inject('wbsbaselineService', () => new WbsbaselineService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const wbsbaselines: Ref<IWbsbaseline[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveWbsbaselines = async () => {
      isFetching.value = true;
      try {
        const res = await wbsbaselineService().retrieve();
        wbsbaselines.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveWbsbaselines();
    };

    onMounted(async () => {
      await retrieveWbsbaselines();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IWbsbaseline) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeWbsbaseline = async () => {
      try {
        await wbsbaselineService().delete(removeId.value);
        const message = t$('jHipster3App.wbsbaseline.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveWbsbaselines();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      wbsbaselines,
      handleSyncList,
      isFetching,
      retrieveWbsbaselines,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeWbsbaseline,
      t$,
    };
  },
});
