import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import PlanexecuteService from './planexecute.service';
import { type IPlanexecute } from '@/shared/model/planexecute.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PlanexecuteDetails',
  setup() {
    const planexecuteService = inject('planexecuteService', () => new PlanexecuteService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const planexecute: Ref<IPlanexecute> = ref({});

    const retrievePlanexecute = async planexecuteId => {
      try {
        const res = await planexecuteService().find(planexecuteId);
        planexecute.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.planexecuteId) {
      retrievePlanexecute(route.params.planexecuteId);
    }

    return {
      alertService,
      planexecute,

      previousState,
      t$: useI18n().t,
    };
  },
});
