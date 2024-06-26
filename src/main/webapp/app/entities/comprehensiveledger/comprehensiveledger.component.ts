import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ComprehensiveledgerService from './comprehensiveledger.service';
import { type IComprehensiveledger } from '@/shared/model/comprehensiveledger.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Comprehensiveledger',
  setup() {
    const { t: t$ } = useI18n();
    const comprehensiveledgerService = inject('comprehensiveledgerService', () => new ComprehensiveledgerService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const comprehensiveledgers: Ref<IComprehensiveledger[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveComprehensiveledgers = async () => {
      isFetching.value = true;
      try {
        const res = await comprehensiveledgerService().retrieve();
        comprehensiveledgers.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveComprehensiveledgers();
    };

    onMounted(async () => {
      await retrieveComprehensiveledgers();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IComprehensiveledger) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeComprehensiveledger = async () => {
      try {
        await comprehensiveledgerService().delete(removeId.value);
        const message = t$('jHipster0App.comprehensiveledger.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveComprehensiveledgers();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      comprehensiveledgers,
      handleSyncList,
      isFetching,
      retrieveComprehensiveledgers,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeComprehensiveledger,
      t$,
    };
  },
});
