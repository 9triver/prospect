import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import RoleService from './role.service';
import { type IRole } from '@/shared/model/role.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RoleDetails',
  setup() {
    const roleService = inject('roleService', () => new RoleService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const role: Ref<IRole> = ref({});

    const retrieveRole = async roleId => {
      try {
        const res = await roleService().find(roleId);
        role.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.roleId) {
      retrieveRole(route.params.roleId);
    }

    return {
      alertService,
      role,

      previousState,
      t$: useI18n().t,
    };
  },
});
