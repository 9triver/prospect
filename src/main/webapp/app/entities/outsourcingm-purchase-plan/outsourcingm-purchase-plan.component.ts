import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import OutsourcingmPurchasePlanService from './outsourcingm-purchase-plan.service';
import { type IOutsourcingmPurchasePlan } from '@/shared/model/outsourcingm-purchase-plan.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OutsourcingmPurchasePlan',
  setup() {
    const { t: t$ } = useI18n();
    const outsourcingmPurchasePlanService = inject('outsourcingmPurchasePlanService', () => new OutsourcingmPurchasePlanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const outsourcingmPurchasePlans: Ref<IOutsourcingmPurchasePlan[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveOutsourcingmPurchasePlans = async () => {
      isFetching.value = true;
      try {
        const res = await outsourcingmPurchasePlanService().retrieve();
        outsourcingmPurchasePlans.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveOutsourcingmPurchasePlans();
    };

    onMounted(async () => {
      await retrieveOutsourcingmPurchasePlans();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IOutsourcingmPurchasePlan) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeOutsourcingmPurchasePlan = async () => {
      try {
        await outsourcingmPurchasePlanService().delete(removeId.value);
        const message = t$('jHipster3App.outsourcingmPurchasePlan.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveOutsourcingmPurchasePlans();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      outsourcingmPurchasePlans,
      handleSyncList,
      isFetching,
      retrieveOutsourcingmPurchasePlans,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeOutsourcingmPurchasePlan,
      t$,
    };
  },
});
