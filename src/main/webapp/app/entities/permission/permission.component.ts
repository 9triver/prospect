import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import PermissionService from './permission.service';
import { type IPermission } from '@/shared/model/permission.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Permission',
  setup() {
    const { t: t$ } = useI18n();
    const permissionService = inject('permissionService', () => new PermissionService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const permissions: Ref<IPermission[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrievePermissions = async () => {
      isFetching.value = true;
      try {
        const res = await permissionService().retrieve();
        permissions.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrievePermissions();
    };

    onMounted(async () => {
      await retrievePermissions();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IPermission) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removePermission = async () => {
      try {
        await permissionService().delete(removeId.value);
        const message = t$('jHipster3App.permission.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrievePermissions();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      permissions,
      handleSyncList,
      isFetching,
      retrievePermissions,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removePermission,
      t$,
    };
  },
});
