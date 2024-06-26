import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ProgressplanService from './progressplan.service';
import { type IProgressplan } from '@/shared/model/progressplan.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProgressplanDetails',
  setup() {
    const progressplanService = inject('progressplanService', () => new ProgressplanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const progressplan: Ref<IProgressplan> = ref({});

    const retrieveProgressplan = async progressplanId => {
      try {
        const res = await progressplanService().find(progressplanId);
        progressplan.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.progressplanId) {
      retrieveProgressplan(route.params.progressplanId);
    }

    return {
      alertService,
      progressplan,

      previousState,
      t$: useI18n().t,
    };
  },
});
