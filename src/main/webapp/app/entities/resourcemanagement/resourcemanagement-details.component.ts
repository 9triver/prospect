import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ResourcemanagementService from './resourcemanagement.service';
import { type IResourcemanagement } from '@/shared/model/resourcemanagement.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ResourcemanagementDetails',
  setup() {
    const resourcemanagementService = inject('resourcemanagementService', () => new ResourcemanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const resourcemanagement: Ref<IResourcemanagement> = ref({});

    const retrieveResourcemanagement = async resourcemanagementId => {
      try {
        const res = await resourcemanagementService().find(resourcemanagementId);
        resourcemanagement.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.resourcemanagementId) {
      retrieveResourcemanagement(route.params.resourcemanagementId);
    }

    return {
      alertService,
      resourcemanagement,

      previousState,
      t$: useI18n().t,
    };
  },
});
