import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import RoleService from './role.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import PermissionService from '@/entities/permission/permission.service';
import { type IPermission } from '@/shared/model/permission.model';
import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IRole, Role } from '@/shared/model/role.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RoleUpdate',
  setup() {
    const roleService = inject('roleService', () => new RoleService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const role: Ref<IRole> = ref(new Role());

    const permissionService = inject('permissionService', () => new PermissionService());

    const permissions: Ref<IPermission[]> = ref([]);

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

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

    const initRelationships = () => {
      permissionService()
        .retrieve()
        .then(res => {
          permissions.value = res.data;
        });
      officersService()
        .retrieve()
        .then(res => {
          officers.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      rolename: {},
      description: {},
      permissions: {},
      officers: {},
    };
    const v$ = useVuelidate(validationRules, role as any);
    v$.value.$validate();

    return {
      roleService,
      alertService,
      role,
      previousState,
      isSaving,
      currentLanguage,
      permissions,
      officers,
      v$,
      t$,
    };
  },
  created(): void {
    this.role.permissions = [];
    this.role.officers = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.role.id) {
        this.roleService()
          .update(this.role)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.role.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.roleService()
          .create(this.role)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.role.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },

    getSelected(selectedVals, option, pkField = 'id'): any {
      if (selectedVals) {
        return selectedVals.find(value => option[pkField] === value[pkField]) ?? option;
      }
      return option;
    },
  },
});
