import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import DeliverablesService from './deliverables.service';
import { type IDeliverables } from '@/shared/model/deliverables.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Deliverables',
  setup() {
    const { t: t$ } = useI18n();
    const deliverablesService = inject('deliverablesService', () => new DeliverablesService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const deliverables: Ref<IDeliverables[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveDeliverabless = async () => {
      isFetching.value = true;
      try {
        const res = await deliverablesService().retrieve();
        deliverables.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveDeliverabless();
    };

    onMounted(async () => {
      await retrieveDeliverabless();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IDeliverables) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeDeliverables = async () => {
      try {
        await deliverablesService().delete(removeId.value);
        const message = t$('jy1App.deliverables.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveDeliverabless();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      deliverables,
      handleSyncList,
      isFetching,
      retrieveDeliverabless,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeDeliverables,
      t$,
    };
  },
});
