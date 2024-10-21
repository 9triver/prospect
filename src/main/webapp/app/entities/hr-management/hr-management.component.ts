import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import HrManagementService from './hr-management.service';
import { type IHrManagement } from '@/shared/model/hr-management.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'HrManagement',
  setup() {
    const { t: t$ } = useI18n();
    const hrManagementService = inject('hrManagementService', () => new HrManagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const hrManagements: Ref<IHrManagement[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveHrManagements = async () => {
      isFetching.value = true;
      try {
        const res = await hrManagementService().retrieve();
        hrManagements.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveHrManagements();
    };

    onMounted(async () => {
      await retrieveHrManagements();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IHrManagement) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeHrManagement = async () => {
      try {
        await hrManagementService().delete(removeId.value);
        const message = t$('jy1App.hrManagement.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveHrManagements();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      hrManagements,
      handleSyncList,
      isFetching,
      retrieveHrManagements,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeHrManagement,
      t$,
    };
  },
});
