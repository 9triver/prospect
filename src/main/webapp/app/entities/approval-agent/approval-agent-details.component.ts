import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ApprovalAgentService from './approval-agent.service';
import { type IApprovalAgent } from '@/shared/model/approval-agent.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ApprovalAgentDetails',
  setup() {
    const approvalAgentService = inject('approvalAgentService', () => new ApprovalAgentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const approvalAgent: Ref<IApprovalAgent> = ref({});

    const retrieveApprovalAgent = async approvalAgentId => {
      try {
        const res = await approvalAgentService().find(approvalAgentId);
        approvalAgent.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.approvalAgentId) {
      retrieveApprovalAgent(route.params.approvalAgentId);
    }

    return {
      alertService,
      approvalAgent,

      previousState,
      t$: useI18n().t,
    };
  },
});
