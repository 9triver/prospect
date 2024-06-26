import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import FundsmanagementService from './fundsmanagement.service';
import { type IFundsmanagement } from '@/shared/model/fundsmanagement.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Fundsmanagement',
  setup() {
    const { t: t$ } = useI18n();
    const fundsmanagementService = inject('fundsmanagementService', () => new FundsmanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const fundsmanagements: Ref<IFundsmanagement[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveFundsmanagements = async () => {
      isFetching.value = true;
      try {
        const res = await fundsmanagementService().retrieve();
        fundsmanagements.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveFundsmanagements();
    };

    onMounted(async () => {
      await retrieveFundsmanagements();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IFundsmanagement) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeFundsmanagement = async () => {
      try {
        await fundsmanagementService().delete(removeId.value);
        const message = t$('jHipster0App.fundsmanagement.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveFundsmanagements();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      fundsmanagements,
      handleSyncList,
      isFetching,
      retrieveFundsmanagements,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeFundsmanagement,
      t$,
    };
  },
});
