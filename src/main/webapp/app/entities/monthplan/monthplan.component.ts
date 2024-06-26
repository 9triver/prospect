import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import MonthplanService from './monthplan.service';
import { type IMonthplan } from '@/shared/model/monthplan.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Monthplan',
  setup() {
    const { t: t$ } = useI18n();
    const monthplanService = inject('monthplanService', () => new MonthplanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const monthplans: Ref<IMonthplan[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveMonthplans = async () => {
      isFetching.value = true;
      try {
        const res = await monthplanService().retrieve();
        monthplans.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveMonthplans();
    };

    onMounted(async () => {
      await retrieveMonthplans();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IMonthplan) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeMonthplan = async () => {
      try {
        await monthplanService().delete(removeId.value);
        const message = t$('jHipster0App.monthplan.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveMonthplans();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      monthplans,
      handleSyncList,
      isFetching,
      retrieveMonthplans,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeMonthplan,
      t$,
    };
  },
});
