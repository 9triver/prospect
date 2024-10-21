import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import WorkbagService from './workbag.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import HrManagementService from '@/entities/hr-management/hr-management.service';
import { type IHrManagement } from '@/shared/model/hr-management.model';
import DepartmentService from '@/entities/department/department.service';
import { type IDepartment } from '@/shared/model/department.model';
import ProjectdeliverablesService from '@/entities/projectdeliverables/projectdeliverables.service';
import { type IProjectdeliverables } from '@/shared/model/projectdeliverables.model';
import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import WorkService from '@/entities/work/work.service';
import { type IWork } from '@/shared/model/work.model';
import { type IWorkbag, Workbag } from '@/shared/model/workbag.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'WorkbagUpdate',
  setup() {
    const workbagService = inject('workbagService', () => new WorkbagService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const workbag: Ref<IWorkbag> = ref(new Workbag());

    const hrManagementService = inject('hrManagementService', () => new HrManagementService());

    const hrManagements: Ref<IHrManagement[]> = ref([]);

    const departmentService = inject('departmentService', () => new DepartmentService());

    const departments: Ref<IDepartment[]> = ref([]);

    const projectdeliverablesService = inject('projectdeliverablesService', () => new ProjectdeliverablesService());

    const projectdeliverables: Ref<IProjectdeliverables[]> = ref([]);

    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());

    const projectwbs: Ref<IProjectwbs[]> = ref([]);

    const workService = inject('workService', () => new WorkService());

    const works: Ref<IWork[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveWorkbag = async workbagId => {
      try {
        const res = await workbagService().find(workbagId);
        workbag.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.workbagId) {
      retrieveWorkbag(route.params.workbagId);
    }

    const initRelationships = () => {
      hrManagementService()
        .retrieve()
        .then(res => {
          hrManagements.value = res.data;
        });
      departmentService()
        .retrieve()
        .then(res => {
          departments.value = res.data;
        });
      projectdeliverablesService()
        .retrieve()
        .then(res => {
          projectdeliverables.value = res.data;
        });
      projectwbsService()
        .retrieve()
        .then(res => {
          projectwbs.value = res.data;
        });
      workService()
        .retrieve()
        .then(res => {
          works.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      name: {},
      pbsid: {},
      workbagtype: {},
      supplier: {},
      iskeyimportant: {},
      keypbsname: {},
      importantpbsname: {},
      secretlevel: {},
      description: {},
      starttime: {},
      endtime: {},
      estimatedpurchasingtime: {},
      progress: {},
      issafetywork: {},
      remark: {},
      auditStatus: {},
      responsibleperson: {},
      projectmanager: {},
      knowingpeople: {},
      auditorid: {},
      responsibledepartment: {},
      department: {},
      projectdeliverables: {},
      relevantdepartments: {},
      wbsids: {},
      works: {},
    };
    const v$ = useVuelidate(validationRules, workbag as any);
    v$.value.$validate();

    return {
      workbagService,
      alertService,
      workbag,
      previousState,
      secretlevelValues,
      auditStatusValues,
      isSaving,
      currentLanguage,
      hrManagements,
      departments,
      projectdeliverables,
      projectwbs,
      works,
      v$,
      t$,
    };
  },
  created(): void {
    this.workbag.projectdeliverables = [];
    this.workbag.relevantdepartments = [];
    this.workbag.wbsids = [];
    this.workbag.works = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.workbag.id) {
        this.workbagService()
          .update(this.workbag)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.workbag.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.workbagService()
          .create(this.workbag)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.workbag.created', { param: param.id }).toString());
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
