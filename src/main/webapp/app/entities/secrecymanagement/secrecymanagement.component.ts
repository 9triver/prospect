import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import SecrecymanagementService from './secrecymanagement.service';
import { type ISecrecymanagement } from '@/shared/model/secrecymanagement.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Secrecymanagement',
  setup() {
    const { t: t$ } = useI18n();
    const secrecymanagementService = inject('secrecymanagementService', () => new SecrecymanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const secrecymanagements: Ref<ISecrecymanagement[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveSecrecymanagements = async () => {
      isFetching.value = true;
      try {
        const res = await secrecymanagementService().retrieve();
        secrecymanagements.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveSecrecymanagements();
    };

    onMounted(async () => {
      await retrieveSecrecymanagements();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ISecrecymanagement) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeSecrecymanagement = async () => {
      try {
        await secrecymanagementService().delete(removeId.value);
        const message = t$('jHipster3App.secrecymanagement.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveSecrecymanagements();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      secrecymanagements,
      handleSyncList,
      isFetching,
      retrieveSecrecymanagements,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeSecrecymanagement,
      t$,
    };
  },
});
