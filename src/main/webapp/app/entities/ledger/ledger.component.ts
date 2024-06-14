import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import LedgerService from './ledger.service';
import { type ILedger } from '@/shared/model/ledger.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Ledger',
  setup() {
    const { t: t$ } = useI18n();
    const ledgerService = inject('ledgerService', () => new LedgerService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const ledgers: Ref<ILedger[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveLedgers = async () => {
      isFetching.value = true;
      try {
        const res = await ledgerService().retrieve();
        ledgers.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveLedgers();
    };

    onMounted(async () => {
      await retrieveLedgers();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ILedger) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeLedger = async () => {
      try {
        await ledgerService().delete(removeId.value);
        const message = t$('jHipster3App.ledger.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveLedgers();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      ledgers,
      handleSyncList,
      isFetching,
      retrieveLedgers,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeLedger,
      t$,
    };
  },
});
