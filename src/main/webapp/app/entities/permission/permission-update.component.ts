import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import PermissionService from './permission.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IPermission, Permission } from '@/shared/model/permission.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PermissionUpdate',
  setup() {
    const permissionService = inject('permissionService', () => new PermissionService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const permission: Ref<IPermission> = ref(new Permission());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

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

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      permissionname: {},
      description: {},
    };
    const v$ = useVuelidate(validationRules, permission as any);
    v$.value.$validate();

    return {
      permissionService,
      alertService,
      permission,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.permission.id) {
        this.permissionService()
          .update(this.permission)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.permission.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.permissionService()
          .create(this.permission)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.permission.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
