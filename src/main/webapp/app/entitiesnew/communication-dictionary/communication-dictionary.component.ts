import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import CommunicationDictionaryService from './communication-dictionary.service';
import { type ICommunicationDictionary } from '@/shared/model/communication-dictionary.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CommunicationDictionary',
  setup() {
    const { t: t$ } = useI18n();
    const communicationDictionaryService = inject('communicationDictionaryService', () => new CommunicationDictionaryService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const communicationDictionaries: Ref<ICommunicationDictionary[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveCommunicationDictionarys = async () => {
      isFetching.value = true;
      try {
        const res = await communicationDictionaryService().retrieve();
        communicationDictionaries.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveCommunicationDictionarys();
    };

    onMounted(async () => {
      await retrieveCommunicationDictionarys();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ICommunicationDictionary) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeCommunicationDictionary = async () => {
      try {
        await communicationDictionaryService().delete(removeId.value);
        const message = t$('jy1App.communicationDictionary.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveCommunicationDictionarys();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      communicationDictionaries,
      handleSyncList,
      isFetching,
      retrieveCommunicationDictionarys,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeCommunicationDictionary,
      t$,
    };
  },
});
