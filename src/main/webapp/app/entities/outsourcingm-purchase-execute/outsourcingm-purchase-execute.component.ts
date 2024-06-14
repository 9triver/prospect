import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import OutsourcingmPurchaseExecuteService from './outsourcingm-purchase-execute.service';
import { type IOutsourcingmPurchaseExecute } from '@/shared/model/outsourcingm-purchase-execute.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OutsourcingmPurchaseExecute',
  setup() {
    const { t: t$ } = useI18n();
    const outsourcingmPurchaseExecuteService = inject('outsourcingmPurchaseExecuteService', () => new OutsourcingmPurchaseExecuteService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const outsourcingmPurchaseExecutes: Ref<IOutsourcingmPurchaseExecute[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveOutsourcingmPurchaseExecutes = async () => {
      isFetching.value = true;
      try {
        const res = await outsourcingmPurchaseExecuteService().retrieve();
        outsourcingmPurchaseExecutes.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveOutsourcingmPurchaseExecutes();
    };

    onMounted(async () => {
      await retrieveOutsourcingmPurchaseExecutes();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IOutsourcingmPurchaseExecute) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeOutsourcingmPurchaseExecute = async () => {
      try {
        await outsourcingmPurchaseExecuteService().delete(removeId.value);
        const message = t$('jHipster3App.outsourcingmPurchaseExecute.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveOutsourcingmPurchaseExecutes();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      outsourcingmPurchaseExecutes,
      handleSyncList,
      isFetching,
      retrieveOutsourcingmPurchaseExecutes,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeOutsourcingmPurchaseExecute,
      t$,
    };
  },
});
