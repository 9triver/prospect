import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import QualitymanagementWbsService from './qualitymanagement-wbs.service';
import { type IQualitymanagementWbs } from '@/shared/model/qualitymanagement-wbs.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QualitymanagementWbsDetails',
  setup() {
    const qualitymanagementWbsService = inject('qualitymanagementWbsService', () => new QualitymanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const qualitymanagementWbs: Ref<IQualitymanagementWbs> = ref({});

    const retrieveQualitymanagementWbs = async qualitymanagementWbsId => {
      try {
        const res = await qualitymanagementWbsService().find(qualitymanagementWbsId);
        qualitymanagementWbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.qualitymanagementWbsId) {
      retrieveQualitymanagementWbs(route.params.qualitymanagementWbsId);
    }

    return {
      alertService,
      qualitymanagementWbs,

      previousState,
      t$: useI18n().t,
    };
  },
});
