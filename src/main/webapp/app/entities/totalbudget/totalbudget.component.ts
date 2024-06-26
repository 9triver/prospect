import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import TotalbudgetService from './totalbudget.service';
import { type ITotalbudget } from '@/shared/model/totalbudget.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Totalbudget',
  setup() {
    const { t: t$ } = useI18n();
    const totalbudgetService = inject('totalbudgetService', () => new TotalbudgetService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const totalbudgets: Ref<ITotalbudget[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveTotalbudgets = async () => {
      isFetching.value = true;
      try {
        const res = await totalbudgetService().retrieve();
        totalbudgets.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveTotalbudgets();
    };

    onMounted(async () => {
      await retrieveTotalbudgets();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ITotalbudget) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeTotalbudget = async () => {
      try {
        await totalbudgetService().delete(removeId.value);
        const message = t$('jHipster0App.totalbudget.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveTotalbudgets();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      totalbudgets,
      handleSyncList,
      isFetching,
      retrieveTotalbudgets,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeTotalbudget,
      t$,
    };
  },
});
