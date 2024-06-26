import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import PbssubmanageService from './pbssubmanage.service';
import { type IPbssubmanage } from '@/shared/model/pbssubmanage.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Pbssubmanage',
  setup() {
    const { t: t$ } = useI18n();
    const pbssubmanageService = inject('pbssubmanageService', () => new PbssubmanageService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const pbssubmanages: Ref<IPbssubmanage[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrievePbssubmanages = async () => {
      isFetching.value = true;
      try {
        const res = await pbssubmanageService().retrieve();
        pbssubmanages.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrievePbssubmanages();
    };

    onMounted(async () => {
      await retrievePbssubmanages();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IPbssubmanage) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removePbssubmanage = async () => {
      try {
        await pbssubmanageService().delete(removeId.value);
        const message = t$('jHipster0App.pbssubmanage.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrievePbssubmanages();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      pbssubmanages,
      handleSyncList,
      isFetching,
      retrievePbssubmanages,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removePbssubmanage,
      t$,
    };
  },
});
