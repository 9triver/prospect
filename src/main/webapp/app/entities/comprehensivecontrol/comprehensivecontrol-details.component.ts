import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ComprehensivecontrolService from './comprehensivecontrol.service';
import { type IComprehensivecontrol } from '@/shared/model/comprehensivecontrol.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ComprehensivecontrolDetails',
  setup() {
    const comprehensivecontrolService = inject('comprehensivecontrolService', () => new ComprehensivecontrolService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const comprehensivecontrol: Ref<IComprehensivecontrol> = ref({});

    const retrieveComprehensivecontrol = async comprehensivecontrolId => {
      try {
        const res = await comprehensivecontrolService().find(comprehensivecontrolId);
        comprehensivecontrol.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.comprehensivecontrolId) {
      retrieveComprehensivecontrol(route.params.comprehensivecontrolId);
    }

    return {
      alertService,
      comprehensivecontrol,

      previousState,
      t$: useI18n().t,
    };
  },
});
