import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ProjectSecrecyService from './project-secrecy.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import SecrecymanagementService from '@/entities/secrecymanagement/secrecymanagement.service';
import { type ISecrecymanagement } from '@/shared/model/secrecymanagement.model';
import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import ProjectService from '@/entities/project/project.service';
import { type IProject } from '@/shared/model/project.model';
import { type IProjectSecrecy, ProjectSecrecy } from '@/shared/model/project-secrecy.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectSecrecyUpdate',
  setup() {
    const projectSecrecyService = inject('projectSecrecyService', () => new ProjectSecrecyService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const projectSecrecy: Ref<IProjectSecrecy> = ref(new ProjectSecrecy());

    const secrecymanagementService = inject('secrecymanagementService', () => new SecrecymanagementService());

    const secrecymanagements: Ref<ISecrecymanagement[]> = ref([]);

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);

    const projectService = inject('projectService', () => new ProjectService());

    const projects: Ref<IProject[]> = ref([]);
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveProjectSecrecy = async projectSecrecyId => {
      try {
        const res = await projectSecrecyService().find(projectSecrecyId);
        projectSecrecy.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.projectSecrecyId) {
      retrieveProjectSecrecy(route.params.projectSecrecyId);
    }

    const initRelationships = () => {
      secrecymanagementService()
        .retrieve()
        .then(res => {
          secrecymanagements.value = res.data;
        });
      officersService()
        .retrieve()
        .then(res => {
          officers.value = res.data;
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
      projectname: {},
      description: {},
      createtime: {},
      auditStatus: {},
      secrecymanagement: {},
      creatorid: {},
      auditorid: {},
      projectid: {},
    };
    const v$ = useVuelidate(validationRules, projectSecrecy as any);
    v$.value.$validate();

    return {
      projectSecrecyService,
      alertService,
      projectSecrecy,
      previousState,
      auditStatusValues,
      isSaving,
      currentLanguage,
      secrecymanagements,
      officers,
      projects,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.projectSecrecy.id) {
        this.projectSecrecyService()
          .update(this.projectSecrecy)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster3App.projectSecrecy.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.projectSecrecyService()
          .create(this.projectSecrecy)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster3App.projectSecrecy.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
