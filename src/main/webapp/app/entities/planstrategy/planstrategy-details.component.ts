import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import PlanstrategyService from './planstrategy.service';
import { type IPlanstrategy } from '@/shared/model/planstrategy.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PlanstrategyDetails',
  setup() {
    const planstrategyService = inject('planstrategyService', () => new PlanstrategyService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const planstrategy: Ref<IPlanstrategy> = ref({});

    const retrievePlanstrategy = async planstrategyId => {
      try {
        const res = await planstrategyService().find(planstrategyId);
        planstrategy.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.planstrategyId) {
      retrievePlanstrategy(route.params.planstrategyId);
    }

    return {
      alertService,
      planstrategy,

      previousState,
      t$: useI18n().t,
    };
  },
});
