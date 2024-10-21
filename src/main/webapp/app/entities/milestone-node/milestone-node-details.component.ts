import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import MilestoneNodeService from './milestone-node.service';
import { type IMilestoneNode } from '@/shared/model/milestone-node.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MilestoneNodeDetails',
  setup() {
    const milestoneNodeService = inject('milestoneNodeService', () => new MilestoneNodeService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const milestoneNode: Ref<IMilestoneNode> = ref({});

    const retrieveMilestoneNode = async milestoneNodeId => {
      try {
        const res = await milestoneNodeService().find(milestoneNodeId);
        milestoneNode.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.milestoneNodeId) {
      retrieveMilestoneNode(route.params.milestoneNodeId);
    }

    return {
      alertService,
      milestoneNode,

      previousState,
      t$: useI18n().t,
    };
  },
});
