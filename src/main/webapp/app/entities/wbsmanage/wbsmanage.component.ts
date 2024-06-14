import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import WbsmanageService from './wbsmanage.service';
import { type IWbsmanage } from '@/shared/model/wbsmanage.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Wbsmanage',
  setup() {
    const { t: t$ } = useI18n();
    const wbsmanageService = inject('wbsmanageService', () => new WbsmanageService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const wbsmanages: Ref<IWbsmanage[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveWbsmanages = async () => {
      isFetching.value = true;
      try {
        const res = await wbsmanageService().retrieve();
        wbsmanages.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveWbsmanages();
    };

    onMounted(async () => {
      await retrieveWbsmanages();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IWbsmanage) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeWbsmanage = async () => {
      try {
        await wbsmanageService().delete(removeId.value);
        const message = t$('jHipster3App.wbsmanage.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveWbsmanages();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      wbsmanages,
      handleSyncList,
      isFetching,
      retrieveWbsmanages,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeWbsmanage,
      t$,
    };
  },
});
