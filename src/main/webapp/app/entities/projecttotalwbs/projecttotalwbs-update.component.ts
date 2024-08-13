import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ProjectTotalwbsService from './projecttotalwbs.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import DepartmentService from '@/entities/department/department.service';
import { type IDepartment } from '@/shared/model/department.model';
import { type IProjectTotalwbs, ProjectTotalwbs } from '@/shared/model/projecttotalwbs.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { ProjectStatus } from '@/shared/model/enumerations/project-status.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectTotalwbsUpdate',
  setup() {
    const projectTotalwbsService = inject('projectTotalwbsService', () => new ProjectTotalwbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const projectTotalwbs: Ref<IProjectTotalwbs> = ref(new ProjectTotalwbs());

    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());

    const projectwbs: Ref<IProjectwbs[]> = ref([]);

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);

    const departmentService = inject('departmentService', () => new DepartmentService());

    const departments: Ref<IDepartment[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const projectStatusValues: Ref<string[]> = ref(Object.keys(ProjectStatus));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveProjectTotalwbs = async projectTotalwbsId => {
      try {
        const res = await projectTotalwbsService().find(projectTotalwbsId);
        projectTotalwbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.projectTotalwbsId) {
      retrieveProjectTotalwbs(route.params.projectTotalwbsId);
    }

    const initRelationships = () => {
      projectwbsService()
        .retrieve()
        .then(res => {
          projectwbs.value = res.data;
        });
      officersService()
        .retrieve()
        .then(res => {
          officers.value = res.data;
        });
      departmentService()
        .retrieve()
        .then(res => {
          departments.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      wbsname: {},
      parentwbsid: {},
      pbsid: {},
      description: {},
      belongfront: {},
      starttime: {},
      endtime: {},
      progress: {},
      type: {},
      priorty: {},
      secretlevel: {},
      deliverables: {},
      status: {},
      auditStatus: {},
      workbag: {},
      projectwbs: {},
      responsibleperson: {},
      technicaldirector: {},
      administrativedirector: {},
      knowingpeople: {},
      auditorid: {},
      responsibledepartment: {},
      relevantdepartment: {},
      department: {},
    };
    const v$ = useVuelidate(validationRules, projectTotalwbs as any);
    v$.value.$validate();

    return {
      projectTotalwbsService,
      alertService,
      projectTotalwbs,
      previousState,
      secretlevelValues,
      projectStatusValues,
      auditStatusValues,
      isSaving,
      currentLanguage,
      projectwbs,
      officers,
      departments,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.projectTotalwbs.id) {
        this.projectTotalwbsService()
          .update(this.projectTotalwbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.projectTotalwbs.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.projectTotalwbsService()
          .create(this.projectTotalwbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.projectTotalwbs.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
