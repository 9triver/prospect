import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ProjectHumanresourcesplanService from './project-humanresourcesplan.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProjectService from '@/entities/project/project.service';
import { type IProject } from '@/shared/model/project.model';
import { type IProjectHumanresourcesplan, ProjectHumanresourcesplan } from '@/shared/model/project-humanresourcesplan.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectHumanresourcesplanUpdate',
  setup() {
    const projectHumanresourcesplanService = inject('projectHumanresourcesplanService', () => new ProjectHumanresourcesplanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const projectHumanresourcesplan: Ref<IProjectHumanresourcesplan> = ref(new ProjectHumanresourcesplan());

    const projectService = inject('projectService', () => new ProjectService());

    const projects: Ref<IProject[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveProjectHumanresourcesplan = async projectHumanresourcesplanId => {
      try {
        const res = await projectHumanresourcesplanService().find(projectHumanresourcesplanId);
        projectHumanresourcesplan.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.projectHumanresourcesplanId) {
      retrieveProjectHumanresourcesplan(route.params.projectHumanresourcesplanId);
    }

    const initRelationships = () => {
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
      projectname: {},
      clientname: {},
      plandate: {},
      secretlevel: {},
      auditStatus: {},
      project: {},
    };
    const v$ = useVuelidate(validationRules, projectHumanresourcesplan as any);
    v$.value.$validate();

    return {
      projectHumanresourcesplanService,
      alertService,
      projectHumanresourcesplan,
      previousState,
      secretlevelValues,
      auditStatusValues,
      isSaving,
      currentLanguage,
      projects,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.projectHumanresourcesplan.id) {
        this.projectHumanresourcesplanService()
          .update(this.projectHumanresourcesplan)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.projectHumanresourcesplan.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.projectHumanresourcesplanService()
          .create(this.projectHumanresourcesplan)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.projectHumanresourcesplan.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
