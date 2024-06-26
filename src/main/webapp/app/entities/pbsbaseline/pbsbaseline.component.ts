import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import PbsbaselineService from './pbsbaseline.service';
import { type IPbsbaseline } from '@/shared/model/pbsbaseline.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Pbsbaseline',
  setup() {
    const { t: t$ } = useI18n();
    const pbsbaselineService = inject('pbsbaselineService', () => new PbsbaselineService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const pbsbaselines: Ref<IPbsbaseline[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrievePbsbaselines = async () => {
      isFetching.value = true;
      try {
        const res = await pbsbaselineService().retrieve();
        pbsbaselines.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrievePbsbaselines();
    };

    onMounted(async () => {
      await retrievePbsbaselines();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IPbsbaseline) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removePbsbaseline = async () => {
      try {
        await pbsbaselineService().delete(removeId.value);
        const message = t$('jHipster0App.pbsbaseline.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrievePbsbaselines();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      pbsbaselines,
      handleSyncList,
      isFetching,
      retrievePbsbaselines,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removePbsbaseline,
      t$,
    };
  },
});
