import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ProjectBudgetService from './project-budget.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import HrManagementService from '@/entities/hr-management/hr-management.service';
import { type IHrManagement } from '@/shared/model/hr-management.model';
import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { type IProjectBudget, ProjectBudget } from '@/shared/model/project-budget.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectBudgetUpdate',
  setup() {
    const projectBudgetService = inject('projectBudgetService', () => new ProjectBudgetService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const projectBudget: Ref<IProjectBudget> = ref(new ProjectBudget());

    const hrManagementService = inject('hrManagementService', () => new HrManagementService());

    const hrManagements: Ref<IHrManagement[]> = ref([]);

    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());

    const projectwbs: Ref<IProjectwbs[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveProjectBudget = async projectBudgetId => {
      try {
        const res = await projectBudgetService().find(projectBudgetId);
        projectBudget.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.projectBudgetId) {
      retrieveProjectBudget(route.params.projectBudgetId);
    }

    const initRelationships = () => {
      hrManagementService()
        .retrieve()
        .then(res => {
          hrManagements.value = res.data;
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
      wbsid: {
        required: validations.required(t$('entity.validation.required').toString()),
      },
      wbsname: {},
      parentwbsid: {},
      subjectid: {},
      subjectname: {},
      contractid: {
        required: validations.required(t$('entity.validation.required').toString()),
      },
      contractname: {},
      year: {},
      auxiliaryitem: {},
      unit: {},
      number: {},
      unitprice: {},
      budgetamount: {},
      estimatedamount: {},
      implementedamount: {},
      difference: {},
      remark: {},
      responsibleperson: {},
      auditorid: {},
      projectwbs: {},
    };
    const v$ = useVuelidate(validationRules, projectBudget as any);
    v$.value.$validate();

    return {
      projectBudgetService,
      alertService,
      projectBudget,
      previousState,
      isSaving,
      currentLanguage,
      hrManagements,
      projectwbs,
      v$,
      t$,
    };
  },
  created(): void {
    this.projectBudget.projectwbs = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.projectBudget.id) {
        this.projectBudgetService()
          .update(this.projectBudget)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.projectBudget.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.projectBudgetService()
          .create(this.projectBudget)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.projectBudget.created', { param: param.id }).toString());
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
