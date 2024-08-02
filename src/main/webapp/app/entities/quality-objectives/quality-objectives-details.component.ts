import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import QualityObjectivesService from './quality-objectives.service';
import { type IQualityObjectives } from '@/shared/model/quality-objectives.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QualityObjectivesDetails',
  setup() {
    const qualityObjectivesService = inject('qualityObjectivesService', () => new QualityObjectivesService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const qualityObjectives: Ref<IQualityObjectives> = ref({});

    const retrieveQualityObjectives = async qualityObjectivesId => {
      try {
        const res = await qualityObjectivesService().find(qualityObjectivesId);
        qualityObjectives.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.qualityObjectivesId) {
      retrieveQualityObjectives(route.params.qualityObjectivesId);
    }

    return {
      alertService,
      qualityObjectives,

      previousState,
      t$: useI18n().t,
    };
  },
});
