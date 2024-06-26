import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import OutsourcingPurchaseExecuteService from './outsourcing-purchase-execute.service';
import { type IOutsourcingPurchaseExecute } from '@/shared/model/outsourcing-purchase-execute.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OutsourcingPurchaseExecute',
  setup() {
    const { t: t$ } = useI18n();
    const outsourcingPurchaseExecuteService = inject('outsourcingPurchaseExecuteService', () => new OutsourcingPurchaseExecuteService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const outsourcingPurchaseExecutes: Ref<IOutsourcingPurchaseExecute[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveOutsourcingPurchaseExecutes = async () => {
      isFetching.value = true;
      try {
        const res = await outsourcingPurchaseExecuteService().retrieve();
        outsourcingPurchaseExecutes.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveOutsourcingPurchaseExecutes();
    };

    onMounted(async () => {
      await retrieveOutsourcingPurchaseExecutes();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IOutsourcingPurchaseExecute) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeOutsourcingPurchaseExecute = async () => {
      try {
        await outsourcingPurchaseExecuteService().delete(removeId.value);
        const message = t$('jHipster0App.outsourcingPurchaseExecute.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveOutsourcingPurchaseExecutes();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      outsourcingPurchaseExecutes,
      handleSyncList,
      isFetching,
      retrieveOutsourcingPurchaseExecutes,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeOutsourcingPurchaseExecute,
      t$,
    };
  },
});
