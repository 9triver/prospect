import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import CommunicationRecordService from './communication-record.service';
import { type ICommunicationRecord } from '@/shared/model/communication-record.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CommunicationRecordDetails',
  setup() {
    const communicationRecordService = inject('communicationRecordService', () => new CommunicationRecordService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const communicationRecord: Ref<ICommunicationRecord> = ref({});

    const retrieveCommunicationRecord = async communicationRecordId => {
      try {
        const res = await communicationRecordService().find(communicationRecordId);
        communicationRecord.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.communicationRecordId) {
      retrieveCommunicationRecord(route.params.communicationRecordId);
    }

    return {
      alertService,
      communicationRecord,

      previousState,
      t$: useI18n().t,
    };
  },
});
