import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ComprehensiveledgerService from './comprehensiveledger.service';
import { type IComprehensiveledger } from '@/shared/model/comprehensiveledger.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ComprehensiveledgerDetails',
  setup() {
    const comprehensiveledgerService = inject('comprehensiveledgerService', () => new ComprehensiveledgerService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const comprehensiveledger: Ref<IComprehensiveledger> = ref({});

    const retrieveComprehensiveledger = async comprehensiveledgerId => {
      try {
        const res = await comprehensiveledgerService().find(comprehensiveledgerId);
        comprehensiveledger.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.comprehensiveledgerId) {
      retrieveComprehensiveledger(route.params.comprehensiveledgerId);
    }

    return {
      alertService,
      comprehensiveledger,

      previousState,
      t$: useI18n().t,
    };
  },
});
