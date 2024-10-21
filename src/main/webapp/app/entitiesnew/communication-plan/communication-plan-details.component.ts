import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import CommunicationPlanService from './communication-plan.service';
import { type ICommunicationPlan } from '@/shared/model/communication-plan.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CommunicationPlanDetails',
  setup() {
    const communicationPlanService = inject('communicationPlanService', () => new CommunicationPlanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const communicationPlan: Ref<ICommunicationPlan> = ref({});

    const retrieveCommunicationPlan = async communicationPlanId => {
      try {
        const res = await communicationPlanService().find(communicationPlanId);
        communicationPlan.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.communicationPlanId) {
      retrieveCommunicationPlan(route.params.communicationPlanId);
    }

    return {
      alertService,
      communicationPlan,

      previousState,
      t$: useI18n().t,
    };
  },
});
