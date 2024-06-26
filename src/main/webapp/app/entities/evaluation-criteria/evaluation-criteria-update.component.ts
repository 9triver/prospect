import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import EvaluationCriteriaService from './evaluation-criteria.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import DepartmentService from '@/entities/department/department.service';
import { type IDepartment } from '@/shared/model/department.model';
import { type IEvaluationCriteria, EvaluationCriteria } from '@/shared/model/evaluation-criteria.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'EvaluationCriteriaUpdate',
  setup() {
    const evaluationCriteriaService = inject('evaluationCriteriaService', () => new EvaluationCriteriaService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const evaluationCriteria: Ref<IEvaluationCriteria> = ref(new EvaluationCriteria());

    const departmentService = inject('departmentService', () => new DepartmentService());

    const departments: Ref<IDepartment[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveEvaluationCriteria = async evaluationCriteriaId => {
      try {
        const res = await evaluationCriteriaService().find(evaluationCriteriaId);
        evaluationCriteria.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.evaluationCriteriaId) {
      retrieveEvaluationCriteria(route.params.evaluationCriteriaId);
    }

    const initRelationships = () => {
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
      standardtype: {},
      standardname: {},
      mark: {},
      department: {},
    };
    const v$ = useVuelidate(validationRules, evaluationCriteria as any);
    v$.value.$validate();

    return {
      evaluationCriteriaService,
      alertService,
      evaluationCriteria,
      previousState,
      isSaving,
      currentLanguage,
      departments,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.evaluationCriteria.id) {
        this.evaluationCriteriaService()
          .update(this.evaluationCriteria)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.evaluationCriteria.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.evaluationCriteriaService()
          .create(this.evaluationCriteria)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.evaluationCriteria.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
