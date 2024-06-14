import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import SecrecymanagementService from './secrecymanagement.service';
import { type ISecrecymanagement } from '@/shared/model/secrecymanagement.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SecrecymanagementDetails',
  setup() {
    const secrecymanagementService = inject('secrecymanagementService', () => new SecrecymanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const secrecymanagement: Ref<ISecrecymanagement> = ref({});

    const retrieveSecrecymanagement = async secrecymanagementId => {
      try {
        const res = await secrecymanagementService().find(secrecymanagementId);
        secrecymanagement.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.secrecymanagementId) {
      retrieveSecrecymanagement(route.params.secrecymanagementId);
    }

    return {
      alertService,
      secrecymanagement,

      previousState,
      t$: useI18n().t,
    };
  },
});
