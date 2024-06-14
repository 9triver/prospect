import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import UnitbudgetService from './unitbudget.service';
import { type IUnitbudget } from '@/shared/model/unitbudget.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'UnitbudgetDetails',
  setup() {
    const unitbudgetService = inject('unitbudgetService', () => new UnitbudgetService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const unitbudget: Ref<IUnitbudget> = ref({});

    const retrieveUnitbudget = async unitbudgetId => {
      try {
        const res = await unitbudgetService().find(unitbudgetId);
        unitbudget.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.unitbudgetId) {
      retrieveUnitbudget(route.params.unitbudgetId);
    }

    return {
      alertService,
      unitbudget,

      previousState,
      t$: useI18n().t,
    };
  },
});
