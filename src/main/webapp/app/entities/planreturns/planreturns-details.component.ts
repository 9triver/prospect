import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import PlanreturnsService from './planreturns.service';
import { type IPlanreturns } from '@/shared/model/planreturns.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PlanreturnsDetails',
  setup() {
    const planreturnsService = inject('planreturnsService', () => new PlanreturnsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const planreturns: Ref<IPlanreturns> = ref({});

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

    return {
      alertService,
      planreturns,

      previousState,
      t$: useI18n().t,
    };
  },
});
