import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import RegularInspectionService from './regular-inspection.service';
import { type IRegularInspection } from '@/shared/model/regular-inspection.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RegularInspection',
  setup() {
    const { t: t$ } = useI18n();
    const regularInspectionService = inject('regularInspectionService', () => new RegularInspectionService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const regularInspections: Ref<IRegularInspection[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveRegularInspections = async () => {
      isFetching.value = true;
      try {
        const res = await regularInspectionService().retrieve();
        regularInspections.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveRegularInspections();
    };

    onMounted(async () => {
      await retrieveRegularInspections();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IRegularInspection) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeRegularInspection = async () => {
      try {
        await regularInspectionService().delete(removeId.value);
        const message = t$('jy1App.regularInspection.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveRegularInspections();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      regularInspections,
      handleSyncList,
      isFetching,
      retrieveRegularInspections,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeRegularInspection,
      t$,
    };
  },
});
