import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import IntegratedmanagementWbsService from './integratedmanagement-wbs.service';
import { type IIntegratedmanagementWbs } from '@/shared/model/integratedmanagement-wbs.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'IntegratedmanagementWbsDetails',
  setup() {
    const integratedmanagementWbsService = inject('integratedmanagementWbsService', () => new IntegratedmanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const integratedmanagementWbs: Ref<IIntegratedmanagementWbs> = ref({});

    const retrieveIntegratedmanagementWbs = async integratedmanagementWbsId => {
      try {
        const res = await integratedmanagementWbsService().find(integratedmanagementWbsId);
        integratedmanagementWbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.integratedmanagementWbsId) {
      retrieveIntegratedmanagementWbs(route.params.integratedmanagementWbsId);
    }

    return {
      alertService,
      integratedmanagementWbs,

      previousState,
      t$: useI18n().t,
    };
  },
});
