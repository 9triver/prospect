import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import RegularInspectionService from './regular-inspection.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import HrManagementService from '@/entities/hr-management/hr-management.service';
import { type IHrManagement } from '@/shared/model/hr-management.model';
import WorkbagService from '@/entities/workbag/workbag.service';
import { type IWorkbag } from '@/shared/model/workbag.model';
import { type IRegularInspection, RegularInspection } from '@/shared/model/regular-inspection.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RegularInspectionUpdate',
  setup() {
    const regularInspectionService = inject('regularInspectionService', () => new RegularInspectionService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const regularInspection: Ref<IRegularInspection> = ref(new RegularInspection());

    const hrManagementService = inject('hrManagementService', () => new HrManagementService());

    const hrManagements: Ref<IHrManagement[]> = ref([]);

    const workbagService = inject('workbagService', () => new WorkbagService());

    const workbags: Ref<IWorkbag[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveRegularInspection = async regularInspectionId => {
      try {
        const res = await regularInspectionService().find(regularInspectionId);
        regularInspection.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.regularInspectionId) {
      retrieveRegularInspection(route.params.regularInspectionId);
    }

    const initRelationships = () => {
      hrManagementService()
        .retrieve()
        .then(res => {
          hrManagements.value = res.data;
        });
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
      workbagid: {},
      workbagname: {},
      type: {},
      secretlevel: {},
      standard: {},
      measurementmethod: {},
      checkresult: {},
      checktarget: {},
      checktime: {},
      checkcompletion: {},
      checkstatus: {},
      auditStatus: {},
      responsibleperson: {},
      designer: {},
      checkperson: {},
      workbag: {},
    };
    const v$ = useVuelidate(validationRules, regularInspection as any);
    v$.value.$validate();

    return {
      regularInspectionService,
      alertService,
      regularInspection,
      previousState,
      secretlevelValues,
      auditStatusValues,
      isSaving,
      currentLanguage,
      hrManagements,
      workbags,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.regularInspection.id) {
        this.regularInspectionService()
          .update(this.regularInspection)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.regularInspection.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.regularInspectionService()
          .create(this.regularInspection)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.regularInspection.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
