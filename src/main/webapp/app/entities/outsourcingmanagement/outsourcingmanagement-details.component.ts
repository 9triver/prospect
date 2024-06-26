import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import OutsourcingmanagementService from './outsourcingmanagement.service';
import { type IOutsourcingmanagement } from '@/shared/model/outsourcingmanagement.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OutsourcingmanagementDetails',
  setup() {
    const outsourcingmanagementService = inject('outsourcingmanagementService', () => new OutsourcingmanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const outsourcingmanagement: Ref<IOutsourcingmanagement> = ref({});

    const retrieveOutsourcingmanagement = async outsourcingmanagementId => {
      try {
        const res = await outsourcingmanagementService().find(outsourcingmanagementId);
        outsourcingmanagement.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.outsourcingmanagementId) {
      retrieveOutsourcingmanagement(route.params.outsourcingmanagementId);
    }

    return {
      alertService,
      outsourcingmanagement,

      previousState,
      t$: useI18n().t,
    };
  },
});
