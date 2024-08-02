import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import PermissionService from './permission.service';
import { type IPermission } from '@/shared/model/permission.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PermissionDetails',
  setup() {
    const permissionService = inject('permissionService', () => new PermissionService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const permission: Ref<IPermission> = ref({});

    const retrievePermission = async permissionId => {
      try {
        const res = await permissionService().find(permissionId);
        permission.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.permissionId) {
      retrievePermission(route.params.permissionId);
    }

    return {
      alertService,
      permission,

      previousState,
      t$: useI18n().t,
    };
  },
});
