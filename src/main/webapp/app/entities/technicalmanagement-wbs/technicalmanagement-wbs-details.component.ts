import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import TechnicalmanagementWbsService from './technicalmanagement-wbs.service';
import { type ITechnicalmanagementWbs } from '@/shared/model/technicalmanagement-wbs.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TechnicalmanagementWbsDetails',
  setup() {
    const technicalmanagementWbsService = inject('technicalmanagementWbsService', () => new TechnicalmanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const technicalmanagementWbs: Ref<ITechnicalmanagementWbs> = ref({});

    const retrieveTechnicalmanagementWbs = async technicalmanagementWbsId => {
      try {
        const res = await technicalmanagementWbsService().find(technicalmanagementWbsId);
        technicalmanagementWbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.technicalmanagementWbsId) {
      retrieveTechnicalmanagementWbs(route.params.technicalmanagementWbsId);
    }

    return {
      alertService,
      technicalmanagementWbs,

      previousState,
      t$: useI18n().t,
    };
  },
});
