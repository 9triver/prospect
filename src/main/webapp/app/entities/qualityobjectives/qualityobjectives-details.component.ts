import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import QualityobjectivesService from './qualityobjectives.service';
import { type IQualityobjectives } from '@/shared/model/qualityobjectives.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QualityobjectivesDetails',
  setup() {
    const qualityobjectivesService = inject('qualityobjectivesService', () => new QualityobjectivesService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const qualityobjectives: Ref<IQualityobjectives> = ref({});

    const retrieveQualityobjectives = async qualityobjectivesId => {
      try {
        const res = await qualityobjectivesService().find(qualityobjectivesId);
        qualityobjectives.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.qualityobjectivesId) {
      retrieveQualityobjectives(route.params.qualityobjectivesId);
    }

    return {
      alertService,
      qualityobjectives,

      previousState,
      t$: useI18n().t,
    };
  },
});
