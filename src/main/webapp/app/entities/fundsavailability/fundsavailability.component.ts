import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import FundsavailabilityService from './fundsavailability.service';
import { type IFundsavailability } from '@/shared/model/fundsavailability.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Fundsavailability',
  setup() {
    const { t: t$ } = useI18n();
    const fundsavailabilityService = inject('fundsavailabilityService', () => new FundsavailabilityService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const fundsavailabilities: Ref<IFundsavailability[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveFundsavailabilitys = async () => {
      isFetching.value = true;
      try {
        const res = await fundsavailabilityService().retrieve();
        fundsavailabilities.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveFundsavailabilitys();
    };

    onMounted(async () => {
      await retrieveFundsavailabilitys();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IFundsavailability) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeFundsavailability = async () => {
      try {
        await fundsavailabilityService().delete(removeId.value);
        const message = t$('jHipster0App.fundsavailability.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveFundsavailabilitys();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      fundsavailabilities,
      handleSyncList,
      isFetching,
      retrieveFundsavailabilitys,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeFundsavailability,
      t$,
    };
  },
});
