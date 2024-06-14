import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import PlanstrategyService from './planstrategy.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import DepartmentService from '@/entities/department/department.service';
import { type IDepartment } from '@/shared/model/department.model';
import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IPlanstrategy, Planstrategy } from '@/shared/model/planstrategy.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PlanstrategyUpdate',
  setup() {
    const planstrategyService = inject('planstrategyService', () => new PlanstrategyService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const planstrategy: Ref<IPlanstrategy> = ref(new Planstrategy());

    const departmentService = inject('departmentService', () => new DepartmentService());

    const departments: Ref<IDepartment[]> = ref([]);

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrievePlanstrategy = async planstrategyId => {
      try {
        const res = await planstrategyService().find(planstrategyId);
        planstrategy.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.planstrategyId) {
      retrievePlanstrategy(route.params.planstrategyId);
    }

    const initRelationships = () => {
      departmentService()
        .retrieve()
        .then(res => {
          departments.value = res.data;
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
      strategyid: {},
      strategyname: {},
      number: {},
      type: {},
      decument: {},
      responsibleid: {},
      auditorid: {},
    };
    const v$ = useVuelidate(validationRules, planstrategy as any);
    v$.value.$validate();

    return {
      planstrategyService,
      alertService,
      planstrategy,
      previousState,
      isSaving,
      currentLanguage,
      departments,
      officers,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.planstrategy.id) {
        this.planstrategyService()
          .update(this.planstrategy)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster3App.planstrategy.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.planstrategyService()
          .create(this.planstrategy)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster3App.planstrategy.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
