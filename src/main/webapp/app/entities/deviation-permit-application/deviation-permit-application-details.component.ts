import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import DeviationPermitApplicationService from './deviation-permit-application.service';
import { type IDeviationPermitApplication } from '@/shared/model/deviation-permit-application.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'DeviationPermitApplicationDetails',
  setup() {
    const deviationPermitApplicationService = inject('deviationPermitApplicationService', () => new DeviationPermitApplicationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const deviationPermitApplication: Ref<IDeviationPermitApplication> = ref({});

    const retrieveDeviationPermitApplication = async deviationPermitApplicationId => {
      try {
        const res = await deviationPermitApplicationService().find(deviationPermitApplicationId);
        deviationPermitApplication.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.deviationPermitApplicationId) {
      retrieveDeviationPermitApplication(route.params.deviationPermitApplicationId);
    }

    return {
      alertService,
      deviationPermitApplication,

      previousState,
      t$: useI18n().t,
    };
  },
});
