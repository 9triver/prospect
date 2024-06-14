import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ManagementCapacityEvaluationService from './management-capacity-evaluation.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import EvaluationCriteriaService from '@/entities/evaluation-criteria/evaluation-criteria.service';
import { type IEvaluationCriteria } from '@/shared/model/evaluation-criteria.model';
import ProjectService from '@/entities/project/project.service';
import { type IProject } from '@/shared/model/project.model';
import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IManagementCapacityEvaluation, ManagementCapacityEvaluation } from '@/shared/model/management-capacity-evaluation.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ManagementCapacityEvaluationUpdate',
  setup() {
    const managementCapacityEvaluationService = inject(
      'managementCapacityEvaluationService',
      () => new ManagementCapacityEvaluationService(),
    );
    const alertService = inject('alertService', () => useAlertService(), true);

    const managementCapacityEvaluation: Ref<IManagementCapacityEvaluation> = ref(new ManagementCapacityEvaluation());

    const evaluationCriteriaService = inject('evaluationCriteriaService', () => new EvaluationCriteriaService());

    const evaluationCriteria: Ref<IEvaluationCriteria[]> = ref([]);

    const projectService = inject('projectService', () => new ProjectService());

    const projects: Ref<IProject[]> = ref([]);

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveManagementCapacityEvaluation = async managementCapacityEvaluationId => {
      try {
        const res = await managementCapacityEvaluationService().find(managementCapacityEvaluationId);
        managementCapacityEvaluation.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.managementCapacityEvaluationId) {
      retrieveManagementCapacityEvaluation(route.params.managementCapacityEvaluationId);
    }

    const initRelationships = () => {
      evaluationCriteriaService()
        .retrieve()
        .then(res => {
          evaluationCriteria.value = res.data;
        });
      projectService()
        .retrieve()
        .then(res => {
          projects.value = res.data;
        });
      officersService()
        .retrieve()
        .then(res => {
          officers.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      year: {},
      deprotment: {},
      createtime: {},
      status: {},
      totalmark: {},
      mark: {},
      ratingpersonname: {},
      ratingdepartment: {},
      ratingtimg: {},
      evaluationCriteria: {},
      project: {},
      creatorid: {},
      responsibleid: {},
      ratingperson: {},
    };
    const v$ = useVuelidate(validationRules, managementCapacityEvaluation as any);
    v$.value.$validate();

    return {
      managementCapacityEvaluationService,
      alertService,
      managementCapacityEvaluation,
      previousState,
      isSaving,
      currentLanguage,
      evaluationCriteria,
      projects,
      officers,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.managementCapacityEvaluation.id) {
        this.managementCapacityEvaluationService()
          .update(this.managementCapacityEvaluation)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster3App.managementCapacityEvaluation.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.managementCapacityEvaluationService()
          .create(this.managementCapacityEvaluation)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster3App.managementCapacityEvaluation.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
