import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import DocumentService from './document.service';
import { type IDocument } from '@/shared/model/document.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Document',
  setup() {
    const { t: t$ } = useI18n();
    const documentService = inject('documentService', () => new DocumentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const documents: Ref<IDocument[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveDocuments = async () => {
      isFetching.value = true;
      try {
        const res = await documentService().retrieve();
        documents.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveDocuments();
    };

    onMounted(async () => {
      await retrieveDocuments();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IDocument) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeDocument = async () => {
      try {
        await documentService().delete(removeId.value);
        const message = t$('jHipster3App.document.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveDocuments();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      documents,
      handleSyncList,
      isFetching,
      retrieveDocuments,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeDocument,
      t$,
    };
  },
});
