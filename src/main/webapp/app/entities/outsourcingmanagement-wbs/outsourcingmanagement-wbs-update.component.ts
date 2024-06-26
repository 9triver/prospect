import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import OutsourcingmanagementWbsService from './outsourcingmanagement-wbs.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OutsourcingPurchasePlanService from '@/entities/outsourcing-purchase-plan/outsourcing-purchase-plan.service';
import { type IOutsourcingPurchasePlan } from '@/shared/model/outsourcing-purchase-plan.model';
import OutsourcingPurchaseExecuteService from '@/entities/outsourcing-purchase-execute/outsourcing-purchase-execute.service';
import { type IOutsourcingPurchaseExecute } from '@/shared/model/outsourcing-purchase-execute.model';
import { type IOutsourcingmanagementWbs, OutsourcingmanagementWbs } from '@/shared/model/outsourcingmanagement-wbs.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OutsourcingmanagementWbsUpdate',
  setup() {
    const outsourcingmanagementWbsService = inject('outsourcingmanagementWbsService', () => new OutsourcingmanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const outsourcingmanagementWbs: Ref<IOutsourcingmanagementWbs> = ref(new OutsourcingmanagementWbs());

    const outsourcingPurchasePlanService = inject('outsourcingPurchasePlanService', () => new OutsourcingPurchasePlanService());

    const outsourcingPurchasePlans: Ref<IOutsourcingPurchasePlan[]> = ref([]);

    const outsourcingPurchaseExecuteService = inject('outsourcingPurchaseExecuteService', () => new OutsourcingPurchaseExecuteService());

    const outsourcingPurchaseExecutes: Ref<IOutsourcingPurchaseExecute[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveOutsourcingmanagementWbs = async outsourcingmanagementWbsId => {
      try {
        const res = await outsourcingmanagementWbsService().find(outsourcingmanagementWbsId);
        outsourcingmanagementWbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.outsourcingmanagementWbsId) {
      retrieveOutsourcingmanagementWbs(route.params.outsourcingmanagementWbsId);
    }

    const initRelationships = () => {
      outsourcingPurchasePlanService()
        .retrieve()
        .then(res => {
          outsourcingPurchasePlans.value = res.data;
        });
      outsourcingPurchaseExecuteService()
        .retrieve()
        .then(res => {
          outsourcingPurchaseExecutes.value = res.data;
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
      outsourcingPurchasePlan: {},
      outsourcingPurchaseExecute: {},
    };
    const v$ = useVuelidate(validationRules, outsourcingmanagementWbs as any);
    v$.value.$validate();

    return {
      outsourcingmanagementWbsService,
      alertService,
      outsourcingmanagementWbs,
      previousState,
      isSaving,
      currentLanguage,
      outsourcingPurchasePlans,
      outsourcingPurchaseExecutes,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.outsourcingmanagementWbs.id) {
        this.outsourcingmanagementWbsService()
          .update(this.outsourcingmanagementWbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.outsourcingmanagementWbs.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.outsourcingmanagementWbsService()
          .create(this.outsourcingmanagementWbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.outsourcingmanagementWbs.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
