import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import PlanReturnsService from './plan-returns.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IPlanReturns, PlanReturns } from '@/shared/model/plan-returns.model';
import { PlanLevel } from '@/shared/model/enumerations/plan-level.model';
import { Planstatus } from '@/shared/model/enumerations/planstatus.model';
import { ReturnsStatus } from '@/shared/model/enumerations/returns-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PlanReturnsUpdate',
  setup() {
    const planReturnsService = inject('planReturnsService', () => new PlanReturnsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const planReturns: Ref<IPlanReturns> = ref(new PlanReturns());

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const planLevelValues: Ref<string[]> = ref(Object.keys(PlanLevel));
    const planstatusValues: Ref<string[]> = ref(Object.keys(Planstatus));
    const returnsStatusValues: Ref<string[]> = ref(Object.keys(ReturnsStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrievePlanReturns = async planReturnsId => {
      try {
        const res = await planReturnsService().find(planReturnsId);
        planReturns.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.planReturnsId) {
      retrievePlanReturns(route.params.planReturnsId);
    }

    const initRelationships = () => {
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
      planreturnsname: {},
      plantype: {},
      planlevel: {},
      description: {},
      actualstarttime: {},
      actualendtime: {},
      deliverables: {},
      progress: {},
      status: {},
      impactanalysis: {},
      returnstime: {},
      rejectionreason: {},
      returnsstatus: {},
      responsibleperson: {},
      auditorid: {},
    };
    const v$ = useVuelidate(validationRules, planReturns as any);
    v$.value.$validate();

    return {
      planReturnsService,
      alertService,
      planReturns,
      previousState,
      planLevelValues,
      planstatusValues,
      returnsStatusValues,
      isSaving,
      currentLanguage,
      officers,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.planReturns.id) {
        this.planReturnsService()
          .update(this.planReturns)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.planReturns.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.planReturnsService()
          .create(this.planReturns)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.planReturns.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
