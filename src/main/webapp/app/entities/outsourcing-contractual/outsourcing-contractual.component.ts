import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import OutsourcingContractualService from './outsourcing-contractual.service';
import { type IOutsourcingContractual } from '@/shared/model/outsourcing-contractual.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OutsourcingContractual',
  setup() {
    const { t: t$ } = useI18n();
    const outsourcingContractualService = inject('outsourcingContractualService', () => new OutsourcingContractualService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const outsourcingContractuals: Ref<IOutsourcingContractual[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveOutsourcingContractuals = async () => {
      isFetching.value = true;
      try {
        const res = await outsourcingContractualService().retrieve();
        outsourcingContractuals.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveOutsourcingContractuals();
    };

    onMounted(async () => {
      await retrieveOutsourcingContractuals();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IOutsourcingContractual) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeOutsourcingContractual = async () => {
      try {
        await outsourcingContractualService().delete(removeId.value);
        const message = t$('jy1App.outsourcingContractual.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveOutsourcingContractuals();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      outsourcingContractuals,
      handleSyncList,
      isFetching,
      retrieveOutsourcingContractuals,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeOutsourcingContractual,
      t$,
    };
  },
});
