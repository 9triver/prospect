import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ResourcemanagementService from './resourcemanagement.service';
import { type IResourcemanagement } from '@/shared/model/resourcemanagement.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Resourcemanagement',
  setup() {
    const { t: t$ } = useI18n();
    const resourcemanagementService = inject('resourcemanagementService', () => new ResourcemanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const resourcemanagements: Ref<IResourcemanagement[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveResourcemanagements = async () => {
      isFetching.value = true;
      try {
        const res = await resourcemanagementService().retrieve();
        resourcemanagements.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveResourcemanagements();
    };

    onMounted(async () => {
      await retrieveResourcemanagements();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IResourcemanagement) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeResourcemanagement = async () => {
      try {
        await resourcemanagementService().delete(removeId.value);
        const message = t$('jHipster0App.resourcemanagement.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveResourcemanagements();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      resourcemanagements,
      handleSyncList,
      isFetching,
      retrieveResourcemanagements,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeResourcemanagement,
      t$,
    };
  },
});
