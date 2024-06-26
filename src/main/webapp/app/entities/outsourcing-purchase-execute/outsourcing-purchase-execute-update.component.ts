import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import OutsourcingPurchaseExecuteService from './outsourcing-purchase-execute.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OutsourcingPurchasePlanService from '@/entities/outsourcing-purchase-plan/outsourcing-purchase-plan.service';
import { type IOutsourcingPurchasePlan } from '@/shared/model/outsourcing-purchase-plan.model';
import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IOutsourcingPurchaseExecute, OutsourcingPurchaseExecute } from '@/shared/model/outsourcing-purchase-execute.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OutsourcingPurchaseExecuteUpdate',
  setup() {
    const outsourcingPurchaseExecuteService = inject('outsourcingPurchaseExecuteService', () => new OutsourcingPurchaseExecuteService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const outsourcingPurchaseExecute: Ref<IOutsourcingPurchaseExecute> = ref(new OutsourcingPurchaseExecute());

    const outsourcingPurchasePlanService = inject('outsourcingPurchasePlanService', () => new OutsourcingPurchasePlanService());

    const outsourcingPurchasePlans: Ref<IOutsourcingPurchasePlan[]> = ref([]);

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveOutsourcingPurchaseExecute = async outsourcingPurchaseExecuteId => {
      try {
        const res = await outsourcingPurchaseExecuteService().find(outsourcingPurchaseExecuteId);
        outsourcingPurchaseExecute.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.outsourcingPurchaseExecuteId) {
      retrieveOutsourcingPurchaseExecute(route.params.outsourcingPurchaseExecuteId);
    }

    const initRelationships = () => {
      outsourcingPurchasePlanService()
        .retrieve()
        .then(res => {
          outsourcingPurchasePlans.value = res.data;
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
      matarialname: {},
      purchasingmethod: {},
      budgit: {},
      needtime: {},
      planusetime: {},
      supplierid: {},
      price: {},
      secretlevel: {},
      outsourcingplanid: {},
      responsibleid: {},
    };
    const v$ = useVuelidate(validationRules, outsourcingPurchaseExecute as any);
    v$.value.$validate();

    return {
      outsourcingPurchaseExecuteService,
      alertService,
      outsourcingPurchaseExecute,
      previousState,
      secretlevelValues,
      isSaving,
      currentLanguage,
      outsourcingPurchasePlans,
      officers,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.outsourcingPurchaseExecute.id) {
        this.outsourcingPurchaseExecuteService()
          .update(this.outsourcingPurchaseExecute)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.outsourcingPurchaseExecute.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.outsourcingPurchaseExecuteService()
          .create(this.outsourcingPurchaseExecute)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.outsourcingPurchaseExecute.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
