import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import QualityObjectivesDictionaryService from './quality-objectives-dictionary.service';
import { type IQualityObjectivesDictionary } from '@/shared/model/quality-objectives-dictionary.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QualityObjectivesDictionaryDetails',
  setup() {
    const qualityObjectivesDictionaryService = inject('qualityObjectivesDictionaryService', () => new QualityObjectivesDictionaryService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const qualityObjectivesDictionary: Ref<IQualityObjectivesDictionary> = ref({});

    const retrieveQualityObjectivesDictionary = async qualityObjectivesDictionaryId => {
      try {
        const res = await qualityObjectivesDictionaryService().find(qualityObjectivesDictionaryId);
        qualityObjectivesDictionary.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.qualityObjectivesDictionaryId) {
      retrieveQualityObjectivesDictionary(route.params.qualityObjectivesDictionaryId);
    }

    return {
      alertService,
      qualityObjectivesDictionary,

      previousState,
      t$: useI18n().t,
    };
  },
});
