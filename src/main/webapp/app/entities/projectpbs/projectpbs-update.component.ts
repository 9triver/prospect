import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ProjectpbsService from './projectpbs.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import HrManagementService from '@/entities/hr-management/hr-management.service';
import { type IHrManagement } from '@/shared/model/hr-management.model';
import DepartmentService from '@/entities/department/department.service';
import { type IDepartment } from '@/shared/model/department.model';
import ProjectService from '@/entities/project/project.service';
import { type IProject } from '@/shared/model/project.model';
import { type IProjectpbs, Projectpbs } from '@/shared/model/projectpbs.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { ProjectStatus } from '@/shared/model/enumerations/project-status.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectpbsUpdate',
  setup() {
    const projectpbsService = inject('projectpbsService', () => new ProjectpbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const projectpbs: Ref<IProjectpbs> = ref(new Projectpbs());

    const hrManagementService = inject('hrManagementService', () => new HrManagementService());

    const hrManagements: Ref<IHrManagement[]> = ref([]);

    const departmentService = inject('departmentService', () => new DepartmentService());

    const departments: Ref<IDepartment[]> = ref([]);

    const projectService = inject('projectService', () => new ProjectService());

    const projects: Ref<IProject[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const projectStatusValues: Ref<string[]> = ref(Object.keys(ProjectStatus));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveProjectpbs = async projectpbsId => {
      try {
        const res = await projectpbsService().find(projectpbsId);
        projectpbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.projectpbsId) {
      retrieveProjectpbs(route.params.projectpbsId);
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
      projectService()
        .retrieve()
        .then(res => {
          projects.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      pbsname: {},
      parentpbsid: {},
      secretlevel: {},
      starttime: {},
      endtime: {},
      productlevel: {},
      iskey: {},
      isimportant: {},
      description: {},
      progress: {},
      type: {},
      priorty: {},
      wbsid: {},
      status: {},
      auditStatus: {},
      technicaldirector: {},
      administrativedirector: {},
      knowingpeople: {},
      auditorid: {},
      responsibledepartment: {},
      relevantdepartments: {},
      projects: {},
    };
    const v$ = useVuelidate(validationRules, projectpbs as any);
    v$.value.$validate();

    return {
      projectpbsService,
      alertService,
      projectpbs,
      previousState,
      secretlevelValues,
      projectStatusValues,
      auditStatusValues,
      isSaving,
      currentLanguage,
      hrManagements,
      departments,
      projects,
      v$,
      t$,
    };
  },
  created(): void {
    this.projectpbs.relevantdepartments = [];
    this.projectpbs.projects = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.projectpbs.id) {
        this.projectpbsService()
          .update(this.projectpbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.projectpbs.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.projectpbsService()
          .create(this.projectpbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.projectpbs.created', { param: param.id }).toString());
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
