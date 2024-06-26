import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ProgressbaselineService from './progressbaseline.service';
import { type IProgressbaseline } from '@/shared/model/progressbaseline.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProgressbaselineDetails',
  setup() {
    const progressbaselineService = inject('progressbaselineService', () => new ProgressbaselineService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const progressbaseline: Ref<IProgressbaseline> = ref({});

    const retrieveProgressbaseline = async progressbaselineId => {
      try {
        const res = await progressbaselineService().find(progressbaselineId);
        progressbaseline.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.progressbaselineId) {
      retrieveProgressbaseline(route.params.progressbaselineId);
    }

    return {
      alertService,
      progressbaseline,

      previousState,
      t$: useI18n().t,
    };
  },
});
