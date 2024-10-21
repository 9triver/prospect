import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import TechnicalConditionService from './technical-condition.service';
import { type ITechnicalCondition } from '@/shared/model/technical-condition.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TechnicalConditionDetails',
  setup() {
    const technicalConditionService = inject('technicalConditionService', () => new TechnicalConditionService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const technicalCondition: Ref<ITechnicalCondition> = ref({});

    const retrieveTechnicalCondition = async technicalConditionId => {
      try {
        const res = await technicalConditionService().find(technicalConditionId);
        technicalCondition.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.technicalConditionId) {
      retrieveTechnicalCondition(route.params.technicalConditionId);
    }

    return {
      alertService,
      technicalCondition,

      previousState,
      t$: useI18n().t,
    };
  },
});
