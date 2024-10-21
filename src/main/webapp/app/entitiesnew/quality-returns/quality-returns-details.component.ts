import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import QualityReturnsService from './quality-returns.service';
import { type IQualityReturns } from '@/shared/model/quality-returns.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QualityReturnsDetails',
  setup() {
    const qualityReturnsService = inject('qualityReturnsService', () => new QualityReturnsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const qualityReturns: Ref<IQualityReturns> = ref({});

    const retrieveQualityReturns = async qualityReturnsId => {
      try {
        const res = await qualityReturnsService().find(qualityReturnsId);
        qualityReturns.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.qualityReturnsId) {
      retrieveQualityReturns(route.params.qualityReturnsId);
    }

    return {
      alertService,
      qualityReturns,

      previousState,
      t$: useI18n().t,
    };
  },
});
