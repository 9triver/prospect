import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import FrontlineService from './frontline.service';
import { type IFrontline } from '@/shared/model/frontline.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Frontline',
  setup() {
    const { t: t$ } = useI18n();
    const frontlineService = inject('frontlineService', () => new FrontlineService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const frontlines: Ref<IFrontline[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveFrontlines = async () => {
      isFetching.value = true;
      try {
        const res = await frontlineService().retrieve();
        frontlines.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveFrontlines();
    };

    onMounted(async () => {
      await retrieveFrontlines();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IFrontline) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeFrontline = async () => {
      try {
        await frontlineService().delete(removeId.value);
        const message = t$('jy1App.frontline.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveFrontlines();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      frontlines,
      handleSyncList,
      isFetching,
      retrieveFrontlines,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeFrontline,
      t$,
    };
  },
});
