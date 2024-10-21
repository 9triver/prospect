import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import KeyNodeInspectionService from './key-node-inspection.service';
import { type IKeyNodeInspection } from '@/shared/model/key-node-inspection.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'KeyNodeInspection',
  setup() {
    const { t: t$ } = useI18n();
    const keyNodeInspectionService = inject('keyNodeInspectionService', () => new KeyNodeInspectionService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const keyNodeInspections: Ref<IKeyNodeInspection[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveKeyNodeInspections = async () => {
      isFetching.value = true;
      try {
        const res = await keyNodeInspectionService().retrieve();
        keyNodeInspections.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveKeyNodeInspections();
    };

    onMounted(async () => {
      await retrieveKeyNodeInspections();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IKeyNodeInspection) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeKeyNodeInspection = async () => {
      try {
        await keyNodeInspectionService().delete(removeId.value);
        const message = t$('jy1App.keyNodeInspection.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveKeyNodeInspections();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      keyNodeInspections,
      handleSyncList,
      isFetching,
      retrieveKeyNodeInspections,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeKeyNodeInspection,
      t$,
    };
  },
});
