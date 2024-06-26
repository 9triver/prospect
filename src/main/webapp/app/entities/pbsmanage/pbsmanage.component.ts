import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import PbsmanageService from './pbsmanage.service';
import { type IPbsmanage } from '@/shared/model/pbsmanage.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Pbsmanage',
  setup() {
    const { t: t$ } = useI18n();
    const pbsmanageService = inject('pbsmanageService', () => new PbsmanageService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const pbsmanages: Ref<IPbsmanage[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrievePbsmanages = async () => {
      isFetching.value = true;
      try {
        const res = await pbsmanageService().retrieve();
        pbsmanages.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrievePbsmanages();
    };

    onMounted(async () => {
      await retrievePbsmanages();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IPbsmanage) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removePbsmanage = async () => {
      try {
        await pbsmanageService().delete(removeId.value);
        const message = t$('jHipster0App.pbsmanage.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrievePbsmanages();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      pbsmanages,
      handleSyncList,
      isFetching,
      retrievePbsmanages,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removePbsmanage,
      t$,
    };
  },
});
