import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import SecuritymanagementWbsService from './securitymanagement-wbs.service';
import { type ISecuritymanagementWbs } from '@/shared/model/securitymanagement-wbs.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SecuritymanagementWbsDetails',
  setup() {
    const securitymanagementWbsService = inject('securitymanagementWbsService', () => new SecuritymanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const securitymanagementWbs: Ref<ISecuritymanagementWbs> = ref({});

    const retrieveSecuritymanagementWbs = async securitymanagementWbsId => {
      try {
        const res = await securitymanagementWbsService().find(securitymanagementWbsId);
        securitymanagementWbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.securitymanagementWbsId) {
      retrieveSecuritymanagementWbs(route.params.securitymanagementWbsId);
    }

    return {
      alertService,
      securitymanagementWbs,

      previousState,
      t$: useI18n().t,
    };
  },
});
