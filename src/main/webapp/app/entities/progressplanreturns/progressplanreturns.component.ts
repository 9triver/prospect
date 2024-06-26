import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ProgressplanreturnsService from './progressplanreturns.service';
import { type IProgressplanreturns } from '@/shared/model/progressplanreturns.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Progressplanreturns',
  setup() {
    const { t: t$ } = useI18n();
    const progressplanreturnsService = inject('progressplanreturnsService', () => new ProgressplanreturnsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const progressplanreturns: Ref<IProgressplanreturns[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveProgressplanreturnss = async () => {
      isFetching.value = true;
      try {
        const res = await progressplanreturnsService().retrieve();
        progressplanreturns.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveProgressplanreturnss();
    };

    onMounted(async () => {
      await retrieveProgressplanreturnss();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IProgressplanreturns) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeProgressplanreturns = async () => {
      try {
        await progressplanreturnsService().delete(removeId.value);
        const message = t$('jHipster0App.progressplanreturns.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveProgressplanreturnss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      progressplanreturns,
      handleSyncList,
      isFetching,
      retrieveProgressplanreturnss,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeProgressplanreturns,
      t$,
    };
  },
});
