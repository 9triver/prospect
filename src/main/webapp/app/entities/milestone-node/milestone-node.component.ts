import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import MilestoneNodeService from './milestone-node.service';
import { type IMilestoneNode } from '@/shared/model/milestone-node.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MilestoneNode',
  setup() {
    const { t: t$ } = useI18n();
    const milestoneNodeService = inject('milestoneNodeService', () => new MilestoneNodeService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const milestoneNodes: Ref<IMilestoneNode[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveMilestoneNodes = async () => {
      isFetching.value = true;
      try {
        const res = await milestoneNodeService().retrieve();
        milestoneNodes.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveMilestoneNodes();
    };

    onMounted(async () => {
      await retrieveMilestoneNodes();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IMilestoneNode) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeMilestoneNode = async () => {
      try {
        await milestoneNodeService().delete(removeId.value);
        const message = t$('jy1App.milestoneNode.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveMilestoneNodes();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      milestoneNodes,
      handleSyncList,
      isFetching,
      retrieveMilestoneNodes,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeMilestoneNode,
      t$,
    };
  },
});
