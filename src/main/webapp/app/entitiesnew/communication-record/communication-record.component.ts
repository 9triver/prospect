import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import CommunicationRecordService from './communication-record.service';
import { type ICommunicationRecord } from '@/shared/model/communication-record.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CommunicationRecord',
  setup() {
    const { t: t$ } = useI18n();
    const communicationRecordService = inject('communicationRecordService', () => new CommunicationRecordService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const communicationRecords: Ref<ICommunicationRecord[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveCommunicationRecords = async () => {
      isFetching.value = true;
      try {
        const res = await communicationRecordService().retrieve();
        communicationRecords.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveCommunicationRecords();
    };

    onMounted(async () => {
      await retrieveCommunicationRecords();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ICommunicationRecord) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeCommunicationRecord = async () => {
      try {
        await communicationRecordService().delete(removeId.value);
        const message = t$('jy1App.communicationRecord.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveCommunicationRecords();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      communicationRecords,
      handleSyncList,
      isFetching,
      retrieveCommunicationRecords,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeCommunicationRecord,
      t$,
    };
  },
});
