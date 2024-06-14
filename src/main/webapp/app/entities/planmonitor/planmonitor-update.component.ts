import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import PlanmonitorService from './planmonitor.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IPlanmonitor, Planmonitor } from '@/shared/model/planmonitor.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PlanmonitorUpdate',
  setup() {
    const planmonitorService = inject('planmonitorService', () => new PlanmonitorService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const planmonitor: Ref<IPlanmonitor> = ref(new Planmonitor());
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrievePlanmonitor = async planmonitorId => {
      try {
        const res = await planmonitorService().find(planmonitorId);
        planmonitor.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.planmonitorId) {
      retrievePlanmonitor(route.params.planmonitorId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      month: {},
      type: {},
      year: {},
      secretlevel: {},
      status: {},
    };
    const v$ = useVuelidate(validationRules, planmonitor as any);
    v$.value.$validate();

    return {
      planmonitorService,
      alertService,
      planmonitor,
      previousState,
      secretlevelValues,
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
      if (this.planmonitor.id) {
        this.planmonitorService()
          .update(this.planmonitor)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster3App.planmonitor.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.planmonitorService()
          .create(this.planmonitor)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster3App.planmonitor.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
