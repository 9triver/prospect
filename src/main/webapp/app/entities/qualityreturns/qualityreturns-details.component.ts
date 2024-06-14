import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import QualityreturnsService from './qualityreturns.service';
import { type IQualityreturns } from '@/shared/model/qualityreturns.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QualityreturnsDetails',
  setup() {
    const qualityreturnsService = inject('qualityreturnsService', () => new QualityreturnsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const qualityreturns: Ref<IQualityreturns> = ref({});

    const retrieveQualityreturns = async qualityreturnsId => {
      try {
        const res = await qualityreturnsService().find(qualityreturnsId);
        qualityreturns.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.qualityreturnsId) {
      retrieveQualityreturns(route.params.qualityreturnsId);
    }

    return {
      alertService,
      qualityreturns,

      previousState,
      t$: useI18n().t,
    };
  },
});
