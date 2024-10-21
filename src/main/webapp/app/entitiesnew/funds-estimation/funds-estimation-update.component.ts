import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import FundsEstimationService from './funds-estimation.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { type IFundsEstimation, FundsEstimation } from '@/shared/model/funds-estimation.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'FundsEstimationUpdate',
  setup() {
    const fundsEstimationService = inject('fundsEstimationService', () => new FundsEstimationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const fundsEstimation: Ref<IFundsEstimation> = ref(new FundsEstimation());

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);

    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());

    const projectwbs: Ref<IProjectwbs[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveFundsEstimation = async fundsEstimationId => {
      try {
        const res = await fundsEstimationService().find(fundsEstimationId);
        fundsEstimation.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.fundsEstimationId) {
      retrieveFundsEstimation(route.params.fundsEstimationId);
    }

    const initRelationships = () => {
      officersService()
        .retrieve()
        .then(res => {
          officers.value = res.data;
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
      name: {},
      source: {},
      unit: {},
      number: {},
      unitprice: {},
      materialfee: {},
      specialfee: {},
      outsourcingfee: {},
      totalbudgetprice: {},
      responsibleperson: {},
      auditorid: {},
      projectwbs: {},
    };
    const v$ = useVuelidate(validationRules, fundsEstimation as any);
    v$.value.$validate();

    return {
      fundsEstimationService,
      alertService,
      fundsEstimation,
      previousState,
      isSaving,
      currentLanguage,
      officers,
      projectwbs,
      v$,
      t$,
    };
  },
  created(): void {
    this.fundsEstimation.projectwbs = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.fundsEstimation.id) {
        this.fundsEstimationService()
          .update(this.fundsEstimation)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.fundsEstimation.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.fundsEstimationService()
          .create(this.fundsEstimation)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.fundsEstimation.created', { param: param.id }).toString());
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
