import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ProgressplanreturnsService from './progressplanreturns.service';
import { type IProgressplanreturns } from '@/shared/model/progressplanreturns.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProgressplanreturnsDetails',
  setup() {
    const progressplanreturnsService = inject('progressplanreturnsService', () => new ProgressplanreturnsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const progressplanreturns: Ref<IProgressplanreturns> = ref({});

    const retrieveProgressplanreturns = async progressplanreturnsId => {
      try {
        const res = await progressplanreturnsService().find(progressplanreturnsId);
        progressplanreturns.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.progressplanreturnsId) {
      retrieveProgressplanreturns(route.params.progressplanreturnsId);
    }

    return {
      alertService,
      progressplanreturns,

      previousState,
      t$: useI18n().t,
    };
  },
});
