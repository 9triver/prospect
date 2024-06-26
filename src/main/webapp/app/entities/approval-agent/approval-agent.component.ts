import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ApprovalAgentService from './approval-agent.service';
import { type IApprovalAgent } from '@/shared/model/approval-agent.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ApprovalAgent',
  setup() {
    const { t: t$ } = useI18n();
    const approvalAgentService = inject('approvalAgentService', () => new ApprovalAgentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const approvalAgents: Ref<IApprovalAgent[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveApprovalAgents = async () => {
      isFetching.value = true;
      try {
        const res = await approvalAgentService().retrieve();
        approvalAgents.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveApprovalAgents();
    };

    onMounted(async () => {
      await retrieveApprovalAgents();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IApprovalAgent) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeApprovalAgent = async () => {
      try {
        await approvalAgentService().delete(removeId.value);
        const message = t$('jHipster0App.approvalAgent.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveApprovalAgents();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      approvalAgents,
      handleSyncList,
      isFetching,
      retrieveApprovalAgents,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeApprovalAgent,
      t$,
    };
  },
});
