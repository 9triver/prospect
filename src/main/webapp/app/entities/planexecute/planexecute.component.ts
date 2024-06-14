import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import PlanexecuteService from './planexecute.service';
import { type IPlanexecute } from '@/shared/model/planexecute.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Planexecute',
  setup() {
    const { t: t$ } = useI18n();
    const planexecuteService = inject('planexecuteService', () => new PlanexecuteService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const planexecutes: Ref<IPlanexecute[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrievePlanexecutes = async () => {
      isFetching.value = true;
      try {
        const res = await planexecuteService().retrieve();
        planexecutes.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrievePlanexecutes();
    };

    onMounted(async () => {
      await retrievePlanexecutes();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IPlanexecute) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removePlanexecute = async () => {
      try {
        await planexecuteService().delete(removeId.value);
        const message = t$('jHipster3App.planexecute.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrievePlanexecutes();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      planexecutes,
      handleSyncList,
      isFetching,
      retrievePlanexecutes,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removePlanexecute,
      t$,
    };
  },
});
