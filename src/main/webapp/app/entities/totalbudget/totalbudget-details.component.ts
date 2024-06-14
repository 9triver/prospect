import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import TotalbudgetService from './totalbudget.service';
import { type ITotalbudget } from '@/shared/model/totalbudget.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TotalbudgetDetails',
  setup() {
    const totalbudgetService = inject('totalbudgetService', () => new TotalbudgetService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const totalbudget: Ref<ITotalbudget> = ref({});

    const retrieveTotalbudget = async totalbudgetId => {
      try {
        const res = await totalbudgetService().find(totalbudgetId);
        totalbudget.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.totalbudgetId) {
      retrieveTotalbudget(route.params.totalbudgetId);
    }

    return {
      alertService,
      totalbudget,

      previousState,
      t$: useI18n().t,
    };
  },
});
