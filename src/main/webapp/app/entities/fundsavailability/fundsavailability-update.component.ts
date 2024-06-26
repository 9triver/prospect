import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import FundsavailabilityService from './fundsavailability.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import AuditedbudgetService from '@/entities/auditedbudget/auditedbudget.service';
import { type IAuditedbudget } from '@/shared/model/auditedbudget.model';
import { type IFundsavailability, Fundsavailability } from '@/shared/model/fundsavailability.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'FundsavailabilityUpdate',
  setup() {
    const fundsavailabilityService = inject('fundsavailabilityService', () => new FundsavailabilityService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const fundsavailability: Ref<IFundsavailability> = ref(new Fundsavailability());

    const auditedbudgetService = inject('auditedbudgetService', () => new AuditedbudgetService());

    const auditedbudgets: Ref<IAuditedbudget[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveFundsavailability = async fundsavailabilityId => {
      try {
        const res = await fundsavailabilityService().find(fundsavailabilityId);
        fundsavailability.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.fundsavailabilityId) {
      retrieveFundsavailability(route.params.fundsavailabilityId);
    }

    const initRelationships = () => {
      auditedbudgetService()
        .retrieve()
        .then(res => {
          auditedbudgets.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      fundsid: {},
      year: {},
      budgit: {},
      funding: {},
      auditedbudget: {},
    };
    const v$ = useVuelidate(validationRules, fundsavailability as any);
    v$.value.$validate();

    return {
      fundsavailabilityService,
      alertService,
      fundsavailability,
      previousState,
      isSaving,
      currentLanguage,
      auditedbudgets,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.fundsavailability.id) {
        this.fundsavailabilityService()
          .update(this.fundsavailability)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.fundsavailability.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.fundsavailabilityService()
          .create(this.fundsavailability)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.fundsavailability.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
