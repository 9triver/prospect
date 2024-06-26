import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ProjectService from './project.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import ProjectSecrecyService from '@/entities/project-secrecy/project-secrecy.service';
import { type IProjectSecrecy } from '@/shared/model/project-secrecy.model';
import { type IProject, Project } from '@/shared/model/project.model';
import { ProjectStatus } from '@/shared/model/enumerations/project-status.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectUpdate',
  setup() {
    const projectService = inject('projectService', () => new ProjectService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const project: Ref<IProject> = ref(new Project());

    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());

    const projectwbs: Ref<IProjectwbs[]> = ref([]);

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);

    const projectSecrecyService = inject('projectSecrecyService', () => new ProjectSecrecyService());

    const projectSecrecies: Ref<IProjectSecrecy[]> = ref([]);
    const projectStatusValues: Ref<string[]> = ref(Object.keys(ProjectStatus));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveProject = async projectId => {
      try {
        const res = await projectService().find(projectId);
        project.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.projectId) {
      retrieveProject(route.params.projectId);
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
      projectSecrecyService()
        .retrieve()
        .then(res => {
          projectSecrecies.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      projectname: {},
      description: {},
      number: {},
      projecttype: {},
      responsiblename: {},
      duedate: {},
      priorty: {},
      status: {},
      auditStatus: {},
      projectwbs: {},
      responsibleid: {},
      auditorid: {},
      projectSecrecy: {},
    };
    const v$ = useVuelidate(validationRules, project as any);
    v$.value.$validate();

    return {
      projectService,
      alertService,
      project,
      previousState,
      projectStatusValues,
      auditStatusValues,
      isSaving,
      currentLanguage,
      projectwbs,
      officers,
      projectSecrecies,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.project.id) {
        this.projectService()
          .update(this.project)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.project.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.projectService()
          .create(this.project)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.project.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
