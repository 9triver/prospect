import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import MonthplanService from './monthplan.service';
import { type IMonthplan } from '@/shared/model/monthplan.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MonthplanDetails',
  setup() {
    const monthplanService = inject('monthplanService', () => new MonthplanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const monthplan: Ref<IMonthplan> = ref({});

    const retrieveMonthplan = async monthplanId => {
      try {
        const res = await monthplanService().find(monthplanId);
        monthplan.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.monthplanId) {
      retrieveMonthplan(route.params.monthplanId);
    }

    return {
      alertService,
      monthplan,

      previousState,
      t$: useI18n().t,
    };
  },
});
