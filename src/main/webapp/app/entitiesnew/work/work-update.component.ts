import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import WorkService from './work.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import WorkbagService from '@/entities/workbag/workbag.service';
import { type IWorkbag } from '@/shared/model/workbag.model';
import { type IWork, Work } from '@/shared/model/work.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'WorkUpdate',
  setup() {
    const workService = inject('workService', () => new WorkService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const work: Ref<IWork> = ref(new Work());

    const workbagService = inject('workbagService', () => new WorkbagService());

    const workbags: Ref<IWorkbag[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveWork = async workId => {
      try {
        const res = await workService().find(workId);
        work.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.workId) {
      retrieveWork(route.params.workId);
    }

    const initRelationships = () => {
      workbagService()
        .retrieve()
        .then(res => {
          workbags.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      name: {},
      secretlevel: {},
      description: {},
      auditStatus: {},
      workbags: {},
    };
    const v$ = useVuelidate(validationRules, work as any);
    v$.value.$validate();

    return {
      workService,
      alertService,
      work,
      previousState,
      secretlevelValues,
      auditStatusValues,
      isSaving,
      currentLanguage,
      workbags,
      v$,
      t$,
    };
  },
  created(): void {
    this.work.workbags = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.work.id) {
        this.workService()
          .update(this.work)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.work.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.workService()
          .create(this.work)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.work.created', { param: param.id }).toString());
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
