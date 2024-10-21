import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import CommunicationFormDictionaryService from './communication-form-dictionary.service';
import { type ICommunicationFormDictionary } from '@/shared/model/communication-form-dictionary.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CommunicationFormDictionary',
  setup() {
    const { t: t$ } = useI18n();
    const communicationFormDictionaryService = inject('communicationFormDictionaryService', () => new CommunicationFormDictionaryService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const communicationFormDictionaries: Ref<ICommunicationFormDictionary[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveCommunicationFormDictionarys = async () => {
      isFetching.value = true;
      try {
        const res = await communicationFormDictionaryService().retrieve();
        communicationFormDictionaries.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveCommunicationFormDictionarys();
    };

    onMounted(async () => {
      await retrieveCommunicationFormDictionarys();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ICommunicationFormDictionary) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeCommunicationFormDictionary = async () => {
      try {
        await communicationFormDictionaryService().delete(removeId.value);
        const message = t$('jy1App.communicationFormDictionary.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveCommunicationFormDictionarys();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      communicationFormDictionaries,
      handleSyncList,
      isFetching,
      retrieveCommunicationFormDictionarys,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeCommunicationFormDictionary,
      t$,
    };
  },
});
