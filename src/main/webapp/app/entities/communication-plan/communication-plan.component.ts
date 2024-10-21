import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import CommunicationPlanService from './communication-plan.service';
import { type ICommunicationPlan } from '@/shared/model/communication-plan.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CommunicationPlan',
  setup() {
    const { t: t$ } = useI18n();
    const communicationPlanService = inject('communicationPlanService', () => new CommunicationPlanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const communicationPlans: Ref<ICommunicationPlan[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveCommunicationPlans = async () => {
      isFetching.value = true;
      try {
        const res = await communicationPlanService().retrieve();
        communicationPlans.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveCommunicationPlans();
    };

    onMounted(async () => {
      await retrieveCommunicationPlans();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ICommunicationPlan) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeCommunicationPlan = async () => {
      try {
        await communicationPlanService().delete(removeId.value);
        const message = t$('jy1App.communicationPlan.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveCommunicationPlans();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      communicationPlans,
      handleSyncList,
      isFetching,
      retrieveCommunicationPlans,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeCommunicationPlan,
      t$,
    };
  },
});
