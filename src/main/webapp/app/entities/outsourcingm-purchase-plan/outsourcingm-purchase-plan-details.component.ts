import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import OutsourcingmPurchasePlanService from './outsourcingm-purchase-plan.service';
import { type IOutsourcingmPurchasePlan } from '@/shared/model/outsourcingm-purchase-plan.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OutsourcingmPurchasePlanDetails',
  setup() {
    const outsourcingmPurchasePlanService = inject('outsourcingmPurchasePlanService', () => new OutsourcingmPurchasePlanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const outsourcingmPurchasePlan: Ref<IOutsourcingmPurchasePlan> = ref({});

    const retrieveOutsourcingmPurchasePlan = async outsourcingmPurchasePlanId => {
      try {
        const res = await outsourcingmPurchasePlanService().find(outsourcingmPurchasePlanId);
        outsourcingmPurchasePlan.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.outsourcingmPurchasePlanId) {
      retrieveOutsourcingmPurchasePlan(route.params.outsourcingmPurchasePlanId);
    }

    return {
      alertService,
      outsourcingmPurchasePlan,

      previousState,
      t$: useI18n().t,
    };
  },
});
