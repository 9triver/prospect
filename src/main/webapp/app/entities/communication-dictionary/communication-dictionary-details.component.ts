import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import CommunicationDictionaryService from './communication-dictionary.service';
import { type ICommunicationDictionary } from '@/shared/model/communication-dictionary.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CommunicationDictionaryDetails',
  setup() {
    const communicationDictionaryService = inject('communicationDictionaryService', () => new CommunicationDictionaryService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const communicationDictionary: Ref<ICommunicationDictionary> = ref({});

    const retrieveCommunicationDictionary = async communicationDictionaryId => {
      try {
        const res = await communicationDictionaryService().find(communicationDictionaryId);
        communicationDictionary.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.communicationDictionaryId) {
      retrieveCommunicationDictionary(route.params.communicationDictionaryId);
    }

    return {
      alertService,
      communicationDictionary,

      previousState,
      t$: useI18n().t,
    };
  },
});
