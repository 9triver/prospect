import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import PlanReturnsService from './plan-returns.service';
import { type IPlanReturns } from '@/shared/model/plan-returns.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PlanReturnsDetails',
  setup() {
    const planReturnsService = inject('planReturnsService', () => new PlanReturnsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const planReturns: Ref<IPlanReturns> = ref({});

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

    return {
      alertService,
      planReturns,

      previousState,
      t$: useI18n().t,
    };
  },
});
