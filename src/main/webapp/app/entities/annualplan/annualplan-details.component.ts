import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import AnnualplanService from './annualplan.service';
import { type IAnnualplan } from '@/shared/model/annualplan.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'AnnualplanDetails',
  setup() {
    const annualplanService = inject('annualplanService', () => new AnnualplanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const annualplan: Ref<IAnnualplan> = ref({});

    const retrieveAnnualplan = async annualplanId => {
      try {
        const res = await annualplanService().find(annualplanId);
        annualplan.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.annualplanId) {
      retrieveAnnualplan(route.params.annualplanId);
    }

    return {
      alertService,
      annualplan,

      previousState,
      t$: useI18n().t,
    };
  },
});
