import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ProjectremitService from './projectremit.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IProjectremit, Projectremit } from '@/shared/model/projectremit.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectremitUpdate',
  setup() {
    const projectremitService = inject('projectremitService', () => new ProjectremitService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const projectremit: Ref<IProjectremit> = ref(new Projectremit());
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveProjectremit = async projectremitId => {
      try {
        const res = await projectremitService().find(projectremitId);
        projectremit.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.projectremitId) {
      retrieveProjectremit(route.params.projectremitId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      remit: {},
      outdeportment: {},
      indeportment: {},
      projectname: {},
      deportment: {},
      projectleader: {},
      secretlevel: {},
      auditStatus: {},
    };
    const v$ = useVuelidate(validationRules, projectremit as any);
    v$.value.$validate();

    return {
      projectremitService,
      alertService,
      projectremit,
      previousState,
      secretlevelValues,
      auditStatusValues,
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
      if (this.projectremit.id) {
        this.projectremitService()
          .update(this.projectremit)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.projectremit.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.projectremitService()
          .create(this.projectremit)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.projectremit.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
