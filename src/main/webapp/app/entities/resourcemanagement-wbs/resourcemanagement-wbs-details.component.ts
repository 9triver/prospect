import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ResourcemanagementWbsService from './resourcemanagement-wbs.service';
import { type IResourcemanagementWbs } from '@/shared/model/resourcemanagement-wbs.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ResourcemanagementWbsDetails',
  setup() {
    const resourcemanagementWbsService = inject('resourcemanagementWbsService', () => new ResourcemanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const resourcemanagementWbs: Ref<IResourcemanagementWbs> = ref({});

    const retrieveResourcemanagementWbs = async resourcemanagementWbsId => {
      try {
        const res = await resourcemanagementWbsService().find(resourcemanagementWbsId);
        resourcemanagementWbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.resourcemanagementWbsId) {
      retrieveResourcemanagementWbs(route.params.resourcemanagementWbsId);
    }

    return {
      alertService,
      resourcemanagementWbs,

      previousState,
      t$: useI18n().t,
    };
  },
});
