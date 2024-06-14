import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import PlanmonitorService from './planmonitor.service';
import { type IPlanmonitor } from '@/shared/model/planmonitor.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PlanmonitorDetails',
  setup() {
    const planmonitorService = inject('planmonitorService', () => new PlanmonitorService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const planmonitor: Ref<IPlanmonitor> = ref({});

    const retrievePlanmonitor = async planmonitorId => {
      try {
        const res = await planmonitorService().find(planmonitorId);
        planmonitor.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.planmonitorId) {
      retrievePlanmonitor(route.params.planmonitorId);
    }

    return {
      alertService,
      planmonitor,

      previousState,
      t$: useI18n().t,
    };
  },
});
