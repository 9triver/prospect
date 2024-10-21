import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import CommunicationFormDictionaryService from './communication-form-dictionary.service';
import { type ICommunicationFormDictionary } from '@/shared/model/communication-form-dictionary.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CommunicationFormDictionaryDetails',
  setup() {
    const communicationFormDictionaryService = inject('communicationFormDictionaryService', () => new CommunicationFormDictionaryService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const communicationFormDictionary: Ref<ICommunicationFormDictionary> = ref({});

    const retrieveCommunicationFormDictionary = async communicationFormDictionaryId => {
      try {
        const res = await communicationFormDictionaryService().find(communicationFormDictionaryId);
        communicationFormDictionary.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.communicationFormDictionaryId) {
      retrieveCommunicationFormDictionary(route.params.communicationFormDictionaryId);
    }

    return {
      alertService,
      communicationFormDictionary,

      previousState,
      t$: useI18n().t,
    };
  },
});
