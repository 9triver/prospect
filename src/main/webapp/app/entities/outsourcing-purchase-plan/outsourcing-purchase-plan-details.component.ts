import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import OutsourcingPurchasePlanService from './outsourcing-purchase-plan.service';
import { type IOutsourcingPurchasePlan } from '@/shared/model/outsourcing-purchase-plan.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OutsourcingPurchasePlanDetails',
  setup() {
    const outsourcingPurchasePlanService = inject('outsourcingPurchasePlanService', () => new OutsourcingPurchasePlanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const outsourcingPurchasePlan: Ref<IOutsourcingPurchasePlan> = ref({});

    const retrieveOutsourcingPurchasePlan = async outsourcingPurchasePlanId => {
      try {
        const res = await outsourcingPurchasePlanService().find(outsourcingPurchasePlanId);
        outsourcingPurchasePlan.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.outsourcingPurchasePlanId) {
      retrieveOutsourcingPurchasePlan(route.params.outsourcingPurchasePlanId);
    }

    return {
      alertService,
      outsourcingPurchasePlan,

      previousState,
      t$: useI18n().t,
    };
  },
});
