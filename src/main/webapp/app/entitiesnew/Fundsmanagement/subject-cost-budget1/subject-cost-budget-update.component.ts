import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import SubjectCostBudgetService from './subject-cost-budget.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProjectBudgetService from '@/entities/project-budget/project-budget.service';
import { type IProjectBudget } from '@/shared/model/project-budget.model';
import HrManagementService from '@/entities/hr-management/hr-management.service';
import { type IHrManagement } from '@/shared/model/hr-management.model';
import { type ISubjectCostBudget, SubjectCostBudget } from '@/shared/model/subject-cost-budget.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SubjectCostBudgetUpdate',
  setup() {
    const subjectCostBudgetService = inject('subjectCostBudgetService', () => new SubjectCostBudgetService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const subjectCostBudget: Ref<ISubjectCostBudget> = ref(new SubjectCostBudget());

    const projectBudgetService = inject('projectBudgetService', () => new ProjectBudgetService());

    const projectBudgets: Ref<IProjectBudget[]> = ref([]);

    const hrManagementService = inject('hrManagementService', () => new HrManagementService());

    const hrManagements: Ref<IHrManagement[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveSubjectCostBudget = async subjectCostBudgetId => {
      try {
        const res = await subjectCostBudgetService().find(subjectCostBudgetId);
        subjectCostBudget.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.subjectCostBudgetId) {
      retrieveSubjectCostBudget(route.params.subjectCostBudgetId);
    }

    const initRelationships = () => {
      projectBudgetService()
        .retrieve()
        .then(res => {
          projectBudgets.value = res.data;
        });
      hrManagementService()
        .retrieve()
        .then(res => {
          hrManagements.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      contractid: {
        required: validations.required(t$('entity.validation.required').toString()),
      },
      subjectid: {
        required: validations.required(t$('entity.validation.required').toString()),
        integer: validations.integer(t$('entity.validation.number').toString()),
      },
      subjectname: {},
      budgetamount: {},
      estimatedamount: {},
      implementedamount: {},
      difference: {},
      percentage: {},
      projectBudget: {},
      responsibleperson: {},
      auditorid: {},
    };
    const v$ = useVuelidate(validationRules, subjectCostBudget as any);
    v$.value.$validate();

    return {
      subjectCostBudgetService,
      alertService,
      subjectCostBudget,
      previousState,
      isSaving,
      currentLanguage,
      projectBudgets,
      hrManagements,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.subjectCostBudget.id) {
        this.subjectCostBudgetService()
          .update(this.subjectCostBudget)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.subjectCostBudget.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.subjectCostBudgetService()
          .create(this.subjectCostBudget)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.subjectCostBudget.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
