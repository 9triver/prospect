import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import FundsmanagementWbsService from './fundsmanagement-wbs.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import AuditedbudgetService from '@/entities/auditedbudget/auditedbudget.service';
import { type IAuditedbudget } from '@/shared/model/auditedbudget.model';
import TotalbudgetService from '@/entities/totalbudget/totalbudget.service';
import { type ITotalbudget } from '@/shared/model/totalbudget.model';
import UnitbudgetService from '@/entities/unitbudget/unitbudget.service';
import { type IUnitbudget } from '@/shared/model/unitbudget.model';
import FundsavailabilityService from '@/entities/fundsavailability/fundsavailability.service';
import { type IFundsavailability } from '@/shared/model/fundsavailability.model';
import ContractualfundsService from '@/entities/contractualfunds/contractualfunds.service';
import { type IContractualfunds } from '@/shared/model/contractualfunds.model';
import { type IFundsmanagementWbs, FundsmanagementWbs } from '@/shared/model/fundsmanagement-wbs.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'FundsmanagementWbsUpdate',
  setup() {
    const fundsmanagementWbsService = inject('fundsmanagementWbsService', () => new FundsmanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const fundsmanagementWbs: Ref<IFundsmanagementWbs> = ref(new FundsmanagementWbs());

    const auditedbudgetService = inject('auditedbudgetService', () => new AuditedbudgetService());

    const auditedbudgets: Ref<IAuditedbudget[]> = ref([]);

    const totalbudgetService = inject('totalbudgetService', () => new TotalbudgetService());

    const totalbudgets: Ref<ITotalbudget[]> = ref([]);

    const unitbudgetService = inject('unitbudgetService', () => new UnitbudgetService());

    const unitbudgets: Ref<IUnitbudget[]> = ref([]);

    const fundsavailabilityService = inject('fundsavailabilityService', () => new FundsavailabilityService());

    const fundsavailabilities: Ref<IFundsavailability[]> = ref([]);

    const contractualfundsService = inject('contractualfundsService', () => new ContractualfundsService());

    const contractualfunds: Ref<IContractualfunds[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveFundsmanagementWbs = async fundsmanagementWbsId => {
      try {
        const res = await fundsmanagementWbsService().find(fundsmanagementWbsId);
        fundsmanagementWbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.fundsmanagementWbsId) {
      retrieveFundsmanagementWbs(route.params.fundsmanagementWbsId);
    }

    const initRelationships = () => {
      auditedbudgetService()
        .retrieve()
        .then(res => {
          auditedbudgets.value = res.data;
        });
      totalbudgetService()
        .retrieve()
        .then(res => {
          totalbudgets.value = res.data;
        });
      unitbudgetService()
        .retrieve()
        .then(res => {
          unitbudgets.value = res.data;
        });
      fundsavailabilityService()
        .retrieve()
        .then(res => {
          fundsavailabilities.value = res.data;
        });
      contractualfundsService()
        .retrieve()
        .then(res => {
          contractualfunds.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      wbsspare1: {},
      wbsspare2: {},
      wbsspare3: {},
      wbsspare4: {},
      wbsspare5: {},
      auditedbudget: {},
      totalbudget: {},
      unitbudget: {},
      fundsavailability: {},
      contractualfunds: {},
    };
    const v$ = useVuelidate(validationRules, fundsmanagementWbs as any);
    v$.value.$validate();

    return {
      fundsmanagementWbsService,
      alertService,
      fundsmanagementWbs,
      previousState,
      isSaving,
      currentLanguage,
      auditedbudgets,
      totalbudgets,
      unitbudgets,
      fundsavailabilities,
      contractualfunds,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.fundsmanagementWbs.id) {
        this.fundsmanagementWbsService()
          .update(this.fundsmanagementWbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.fundsmanagementWbs.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.fundsmanagementWbsService()
          .create(this.fundsmanagementWbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.fundsmanagementWbs.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
