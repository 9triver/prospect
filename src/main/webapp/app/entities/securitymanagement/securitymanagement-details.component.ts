import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import SecuritymanagementService from './securitymanagement.service';
import { type ISecuritymanagement } from '@/shared/model/securitymanagement.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SecuritymanagementDetails',
  setup() {
    const securitymanagementService = inject('securitymanagementService', () => new SecuritymanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const securitymanagement: Ref<ISecuritymanagement> = ref({});

    const retrieveSecuritymanagement = async securitymanagementId => {
      try {
        const res = await securitymanagementService().find(securitymanagementId);
        securitymanagement.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.securitymanagementId) {
      retrieveSecuritymanagement(route.params.securitymanagementId);
    }

    return {
      alertService,
      securitymanagement,

      previousState,
      t$: useI18n().t,
    };
  },
});
