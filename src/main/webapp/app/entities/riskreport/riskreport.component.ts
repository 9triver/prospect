import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import RiskreportService from './riskreport.service';
import { type IRiskreport } from '@/shared/model/riskreport.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Riskreport',
  setup() {
    const { t: t$ } = useI18n();
    const riskreportService = inject('riskreportService', () => new RiskreportService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const riskreports: Ref<IRiskreport[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveRiskreports = async () => {
      isFetching.value = true;
      try {
        const res = await riskreportService().retrieve();
        riskreports.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveRiskreports();
    };

    onMounted(async () => {
      await retrieveRiskreports();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IRiskreport) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeRiskreport = async () => {
      try {
        await riskreportService().delete(removeId.value);
        const message = t$('jHipster3App.riskreport.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveRiskreports();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      riskreports,
      handleSyncList,
      isFetching,
      retrieveRiskreports,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeRiskreport,
      t$,
    };
  },
});
