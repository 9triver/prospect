import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import IntegratedmanagementService from './integratedmanagement.service';
import { type IIntegratedmanagement } from '@/shared/model/integratedmanagement.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Integratedmanagement',
  setup() {
    const { t: t$ } = useI18n();
    const integratedmanagementService = inject('integratedmanagementService', () => new IntegratedmanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const integratedmanagements: Ref<IIntegratedmanagement[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveIntegratedmanagements = async () => {
      isFetching.value = true;
      try {
        const res = await integratedmanagementService().retrieve();
        integratedmanagements.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveIntegratedmanagements();
    };

    onMounted(async () => {
      await retrieveIntegratedmanagements();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IIntegratedmanagement) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeIntegratedmanagement = async () => {
      try {
        await integratedmanagementService().delete(removeId.value);
        const message = t$('jHipster0App.integratedmanagement.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveIntegratedmanagements();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      integratedmanagements,
      handleSyncList,
      isFetching,
      retrieveIntegratedmanagements,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeIntegratedmanagement,
      t$,
    };
  },
});
