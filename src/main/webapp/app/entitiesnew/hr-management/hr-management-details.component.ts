import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import HrManagementService from './hr-management.service';
import { type IHrManagement } from '@/shared/model/hr-management.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'HrManagementDetails',
  setup() {
    const hrManagementService = inject('hrManagementService', () => new HrManagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const hrManagement: Ref<IHrManagement> = ref({});

    const retrieveHrManagement = async hrManagementId => {
      try {
        const res = await hrManagementService().find(hrManagementId);
        hrManagement.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.hrManagementId) {
      retrieveHrManagement(route.params.hrManagementId);
    }

    return {
      alertService,
      hrManagement,

      previousState,
      t$: useI18n().t,
    };
  },
});
