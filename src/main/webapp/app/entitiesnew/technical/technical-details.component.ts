import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import TechnicalService from './technical.service';
import { type ITechnical } from '@/shared/model/technical.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TechnicalDetails',
  setup() {
    const technicalService = inject('technicalService', () => new TechnicalService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const technical: Ref<ITechnical> = ref({});

    const retrieveTechnical = async technicalId => {
      try {
        const res = await technicalService().find(technicalId);
        technical.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.technicalId) {
      retrieveTechnical(route.params.technicalId);
    }

    return {
      alertService,
      technical,

      previousState,
      t$: useI18n().t,
    };
  },
});
