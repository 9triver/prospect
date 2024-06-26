import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import RoleService from './role.service';
import { type IRole } from '@/shared/model/role.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Role',
  setup() {
    const { t: t$ } = useI18n();
    const roleService = inject('roleService', () => new RoleService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const roles: Ref<IRole[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveRoles = async () => {
      isFetching.value = true;
      try {
        const res = await roleService().retrieve();
        roles.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveRoles();
    };

    onMounted(async () => {
      await retrieveRoles();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IRole) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeRole = async () => {
      try {
        await roleService().delete(removeId.value);
        const message = t$('jHipster0App.role.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveRoles();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      roles,
      handleSyncList,
      isFetching,
      retrieveRoles,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeRole,
      t$,
    };
  },
});
