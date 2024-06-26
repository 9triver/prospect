import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import FundsmanagementWbsService from './fundsmanagement-wbs.service';
import { type IFundsmanagementWbs } from '@/shared/model/fundsmanagement-wbs.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'FundsmanagementWbs',
  setup() {
    const { t: t$ } = useI18n();
    const fundsmanagementWbsService = inject('fundsmanagementWbsService', () => new FundsmanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const fundsmanagementWbs: Ref<IFundsmanagementWbs[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveFundsmanagementWbss = async () => {
      isFetching.value = true;
      try {
        const res = await fundsmanagementWbsService().retrieve();
        fundsmanagementWbs.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveFundsmanagementWbss();
    };

    onMounted(async () => {
      await retrieveFundsmanagementWbss();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IFundsmanagementWbs) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeFundsmanagementWbs = async () => {
      try {
        await fundsmanagementWbsService().delete(removeId.value);
        const message = t$('jHipster0App.fundsmanagementWbs.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveFundsmanagementWbss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      fundsmanagementWbs,
      handleSyncList,
      isFetching,
      retrieveFundsmanagementWbss,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeFundsmanagementWbs,
      t$,
    };
  },
});
