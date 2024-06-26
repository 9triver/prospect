import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import WbssubmanageService from './wbssubmanage.service';
import { type IWbssubmanage } from '@/shared/model/wbssubmanage.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Wbssubmanage',
  setup() {
    const { t: t$ } = useI18n();
    const wbssubmanageService = inject('wbssubmanageService', () => new WbssubmanageService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const wbssubmanages: Ref<IWbssubmanage[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveWbssubmanages = async () => {
      isFetching.value = true;
      try {
        const res = await wbssubmanageService().retrieve();
        wbssubmanages.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveWbssubmanages();
    };

    onMounted(async () => {
      await retrieveWbssubmanages();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IWbssubmanage) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeWbssubmanage = async () => {
      try {
        await wbssubmanageService().delete(removeId.value);
        const message = t$('jHipster0App.wbssubmanage.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveWbssubmanages();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      wbssubmanages,
      handleSyncList,
      isFetching,
      retrieveWbssubmanages,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeWbssubmanage,
      t$,
    };
  },
});
