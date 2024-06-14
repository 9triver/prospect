import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import PlanstrategyService from './planstrategy.service';
import { type IPlanstrategy } from '@/shared/model/planstrategy.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Planstrategy',
  setup() {
    const { t: t$ } = useI18n();
    const planstrategyService = inject('planstrategyService', () => new PlanstrategyService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const planstrategies: Ref<IPlanstrategy[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrievePlanstrategys = async () => {
      isFetching.value = true;
      try {
        const res = await planstrategyService().retrieve();
        planstrategies.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrievePlanstrategys();
    };

    onMounted(async () => {
      await retrievePlanstrategys();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IPlanstrategy) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removePlanstrategy = async () => {
      try {
        await planstrategyService().delete(removeId.value);
        const message = t$('jHipster3App.planstrategy.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrievePlanstrategys();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      planstrategies,
      handleSyncList,
      isFetching,
      retrievePlanstrategys,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removePlanstrategy,
      t$,
    };
  },
});
