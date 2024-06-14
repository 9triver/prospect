import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import OutsourcingmPurchaseExecuteService from './outsourcingm-purchase-execute.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OutsourcingmPurchasePlanService from '@/entities/outsourcingm-purchase-plan/outsourcingm-purchase-plan.service';
import { type IOutsourcingmPurchasePlan } from '@/shared/model/outsourcingm-purchase-plan.model';
import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IOutsourcingmPurchaseExecute, OutsourcingmPurchaseExecute } from '@/shared/model/outsourcingm-purchase-execute.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OutsourcingmPurchaseExecuteUpdate',
  setup() {
    const outsourcingmPurchaseExecuteService = inject('outsourcingmPurchaseExecuteService', () => new OutsourcingmPurchaseExecuteService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const outsourcingmPurchaseExecute: Ref<IOutsourcingmPurchaseExecute> = ref(new OutsourcingmPurchaseExecute());

    const outsourcingmPurchasePlanService = inject('outsourcingmPurchasePlanService', () => new OutsourcingmPurchasePlanService());

    const outsourcingmPurchasePlans: Ref<IOutsourcingmPurchasePlan[]> = ref([]);

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveOutsourcingmPurchaseExecute = async outsourcingmPurchaseExecuteId => {
      try {
        const res = await outsourcingmPurchaseExecuteService().find(outsourcingmPurchaseExecuteId);
        outsourcingmPurchaseExecute.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.outsourcingmPurchaseExecuteId) {
      retrieveOutsourcingmPurchaseExecute(route.params.outsourcingmPurchaseExecuteId);
    }

    const initRelationships = () => {
      outsourcingmPurchasePlanService()
        .retrieve()
        .then(res => {
          outsourcingmPurchasePlans.value = res.data;
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
      outsourcingexecuteid: {
        integer: validations.integer(t$('entity.validation.number').toString()),
      },
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
    const v$ = useVuelidate(validationRules, outsourcingmPurchaseExecute as any);
    v$.value.$validate();

    return {
      outsourcingmPurchaseExecuteService,
      alertService,
      outsourcingmPurchaseExecute,
      previousState,
      secretlevelValues,
      isSaving,
      currentLanguage,
      outsourcingmPurchasePlans,
      officers,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.outsourcingmPurchaseExecute.id) {
        this.outsourcingmPurchaseExecuteService()
          .update(this.outsourcingmPurchaseExecute)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster3App.outsourcingmPurchaseExecute.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.outsourcingmPurchaseExecuteService()
          .create(this.outsourcingmPurchaseExecute)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster3App.outsourcingmPurchaseExecute.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
