import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import LedgerService from './ledger.service';
import { type ILedger } from '@/shared/model/ledger.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'LedgerDetails',
  setup() {
    const ledgerService = inject('ledgerService', () => new LedgerService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const ledger: Ref<ILedger> = ref({});

    const retrieveLedger = async ledgerId => {
      try {
        const res = await ledgerService().find(ledgerId);
        ledger.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.ledgerId) {
      retrieveLedger(route.params.ledgerId);
    }

    return {
      alertService,
      ledger,

      previousState,
      t$: useI18n().t,
    };
  },
});
