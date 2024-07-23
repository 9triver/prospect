import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ProjectService from './project.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProjectpbsService from '@/entities/projectpbs/projectpbs.service';
import { type IProjectpbs } from '@/shared/model/projectpbs.model';
import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { type IProject, Project } from '@/shared/model/project.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { ProjectStatus } from '@/shared/model/enumerations/project-status.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectUpdate',
  setup() {
    const projectService = inject('projectService', () => new ProjectService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const project: Ref<IProject> = ref(new Project());

    const projectpbsService = inject('projectpbsService', () => new ProjectpbsService());

    const projectpbs: Ref<IProjectpbs[]> = ref([]);

    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());

    const projectwbs: Ref<IProjectwbs[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
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
      projectpbsService()
        .retrieve()
        .then(res => {
          projectpbs.value = res.data;
        });
      projectwbsService()
        .retrieve()
        .then(res => {
          projectwbs.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      projectname: {},
      parentid: {},
      pbsid: {},
      description: {},
      number: {},
      projecttype: {},
      priorty: {},
      createdate: {},
      secretlevel: {},
      status: {},
      auditStatus: {},
      progress: {},
      projectpbs: {},
      projectwbs: {},
    };
    const v$ = useVuelidate(validationRules, project as any);
    v$.value.$validate();

    return {
      projectService,
      alertService,
      project,
      previousState,
      secretlevelValues,
      projectStatusValues,
      auditStatusValues,
      isSaving,
      currentLanguage,
      projectpbs,
      projectwbs,
      v$,
      t$,
    };
  },
  created(): void {
    this.project.projectpbs = [];
    this.project.projectwbs = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.project.id) {
        this.projectService()
          .update(this.project)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.project.updated', { param: param.id }));
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
            this.alertService.showSuccess(this.t$('jy1App.project.created', { param: param.id }).toString());
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
