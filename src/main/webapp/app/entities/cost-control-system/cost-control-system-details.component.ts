import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import CostControlSystemService from './cost-control-system.service';
import { type ICostControlSystem } from '@/shared/model/cost-control-system.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CostControlSystemDetails',
  setup() {
    const costControlSystemService = inject('costControlSystemService', () => new CostControlSystemService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const costControlSystem: Ref<ICostControlSystem> = ref({});

    const retrieveCostControlSystem = async costControlSystemId => {
      try {
        const res = await costControlSystemService().find(costControlSystemId);
        costControlSystem.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.costControlSystemId) {
      retrieveCostControlSystem(route.params.costControlSystemId);
    }

    return {
      alertService,
      costControlSystem,

      previousState,
      t$: useI18n().t,
    };
  },
});
