import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import QualitytozeroService from './qualitytozero.service';
import { type IQualitytozero } from '@/shared/model/qualitytozero.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QualitytozeroDetails',
  setup() {
    const qualitytozeroService = inject('qualitytozeroService', () => new QualitytozeroService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const qualitytozero: Ref<IQualitytozero> = ref({});

    const retrieveQualitytozero = async qualitytozeroId => {
      try {
        const res = await qualitytozeroService().find(qualitytozeroId);
        qualitytozero.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.qualitytozeroId) {
      retrieveQualitytozero(route.params.qualitytozeroId);
    }

    return {
      alertService,
      qualitytozero,

      previousState,
      t$: useI18n().t,
    };
  },
});
