import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import RiskreportService from './riskreport.service';
import { type IRiskreport } from '@/shared/model/riskreport.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RiskreportDetails',
  setup() {
    const riskreportService = inject('riskreportService', () => new RiskreportService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const riskreport: Ref<IRiskreport> = ref({});

    const retrieveRiskreport = async riskreportId => {
      try {
        const res = await riskreportService().find(riskreportId);
        riskreport.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.riskreportId) {
      retrieveRiskreport(route.params.riskreportId);
    }

    return {
      alertService,
      riskreport,

      previousState,
      t$: useI18n().t,
    };
  },
});
