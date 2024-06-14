import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import CycleplanService from './cycleplan.service';
import { type ICycleplan } from '@/shared/model/cycleplan.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CycleplanDetails',
  setup() {
    const cycleplanService = inject('cycleplanService', () => new CycleplanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const cycleplan: Ref<ICycleplan> = ref({});

    const retrieveCycleplan = async cycleplanId => {
      try {
        const res = await cycleplanService().find(cycleplanId);
        cycleplan.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.cycleplanId) {
      retrieveCycleplan(route.params.cycleplanId);
    }

    return {
      alertService,
      cycleplan,

      previousState,
      t$: useI18n().t,
    };
  },
});
