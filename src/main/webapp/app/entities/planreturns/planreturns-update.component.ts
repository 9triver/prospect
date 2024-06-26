import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import PlanreturnsService from './planreturns.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IPlanreturns, Planreturns } from '@/shared/model/planreturns.model';
import { ReturnsStatus } from '@/shared/model/enumerations/returns-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PlanreturnsUpdate',
  setup() {
    const planreturnsService = inject('planreturnsService', () => new PlanreturnsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const planreturns: Ref<IPlanreturns> = ref(new Planreturns());
    const returnsStatusValues: Ref<string[]> = ref(Object.keys(ReturnsStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrievePlanreturns = async planreturnsId => {
      try {
        const res = await planreturnsService().find(planreturnsId);
        planreturns.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.planreturnsId) {
      retrievePlanreturns(route.params.planreturnsId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      planreturnsname: {},
      starttime: {},
      endtime: {},
      plantype: {},
      returnstime: {},
      returnsstatus: {},
    };
    const v$ = useVuelidate(validationRules, planreturns as any);
    v$.value.$validate();

    return {
      planreturnsService,
      alertService,
      planreturns,
      previousState,
      returnsStatusValues,
      isSaving,
      currentLanguage,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.planreturns.id) {
        this.planreturnsService()
          .update(this.planreturns)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.planreturns.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.planreturnsService()
          .create(this.planreturns)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.planreturns.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
