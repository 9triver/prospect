import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ResourcemanagementWbsService from './resourcemanagement-wbs.service';
import { type IResourcemanagementWbs } from '@/shared/model/resourcemanagement-wbs.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ResourcemanagementWbs',
  setup() {
    const { t: t$ } = useI18n();
    const resourcemanagementWbsService = inject('resourcemanagementWbsService', () => new ResourcemanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const resourcemanagementWbs: Ref<IResourcemanagementWbs[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveResourcemanagementWbss = async () => {
      isFetching.value = true;
      try {
        const res = await resourcemanagementWbsService().retrieve();
        resourcemanagementWbs.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveResourcemanagementWbss();
    };

    onMounted(async () => {
      await retrieveResourcemanagementWbss();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IResourcemanagementWbs) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeResourcemanagementWbs = async () => {
      try {
        await resourcemanagementWbsService().delete(removeId.value);
        const message = t$('jHipster0App.resourcemanagementWbs.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveResourcemanagementWbss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      resourcemanagementWbs,
      handleSyncList,
      isFetching,
      retrieveResourcemanagementWbss,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeResourcemanagementWbs,
      t$,
    };
  },
});
