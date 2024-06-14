import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import UnitbudgetService from './unitbudget.service';
import { type IUnitbudget } from '@/shared/model/unitbudget.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Unitbudget',
  setup() {
    const { t: t$ } = useI18n();
    const unitbudgetService = inject('unitbudgetService', () => new UnitbudgetService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const unitbudgets: Ref<IUnitbudget[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveUnitbudgets = async () => {
      isFetching.value = true;
      try {
        const res = await unitbudgetService().retrieve();
        unitbudgets.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveUnitbudgets();
    };

    onMounted(async () => {
      await retrieveUnitbudgets();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IUnitbudget) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeUnitbudget = async () => {
      try {
        await unitbudgetService().delete(removeId.value);
        const message = t$('jHipster3App.unitbudget.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveUnitbudgets();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      unitbudgets,
      handleSyncList,
      isFetching,
      retrieveUnitbudgets,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeUnitbudget,
      t$,
    };
  },
});
