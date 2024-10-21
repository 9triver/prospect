import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import DeliveryContentService from './delivery-content.service';
import { type IDeliveryContent } from '@/shared/model/delivery-content.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'DeliveryContent',
  setup() {
    const { t: t$ } = useI18n();
    const deliveryContentService = inject('deliveryContentService', () => new DeliveryContentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const deliveryContents: Ref<IDeliveryContent[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveDeliveryContents = async () => {
      isFetching.value = true;
      try {
        const res = await deliveryContentService().retrieve();
        deliveryContents.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveDeliveryContents();
    };

    onMounted(async () => {
      await retrieveDeliveryContents();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IDeliveryContent) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeDeliveryContent = async () => {
      try {
        await deliveryContentService().delete(removeId.value);
        const message = t$('jy1App.deliveryContent.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveDeliveryContents();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      deliveryContents,
      handleSyncList,
      isFetching,
      retrieveDeliveryContents,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeDeliveryContent,
      t$,
    };
  },
});
