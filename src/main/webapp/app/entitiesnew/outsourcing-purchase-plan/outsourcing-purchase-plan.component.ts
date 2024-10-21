import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import OutsourcingPurchasePlanService from './outsourcing-purchase-plan.service';
import { type IOutsourcingPurchasePlan } from '@/shared/model/outsourcing-purchase-plan.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OutsourcingPurchasePlan',
  setup() {
    const { t: t$ } = useI18n();
    const outsourcingPurchasePlanService = inject('outsourcingPurchasePlanService', () => new OutsourcingPurchasePlanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const outsourcingPurchasePlans: Ref<IOutsourcingPurchasePlan[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveOutsourcingPurchasePlans = async () => {
      isFetching.value = true;
      try {
        const res = await outsourcingPurchasePlanService().retrieve();
        outsourcingPurchasePlans.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveOutsourcingPurchasePlans();
    };

    onMounted(async () => {
      await retrieveOutsourcingPurchasePlans();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IOutsourcingPurchasePlan) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeOutsourcingPurchasePlan = async () => {
      try {
        await outsourcingPurchasePlanService().delete(removeId.value);
        const message = t$('jy1App.outsourcingPurchasePlan.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveOutsourcingPurchasePlans();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      outsourcingPurchasePlans,
      handleSyncList,
      isFetching,
      retrieveOutsourcingPurchasePlans,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeOutsourcingPurchasePlan,
      t$,
    };
  },
});
