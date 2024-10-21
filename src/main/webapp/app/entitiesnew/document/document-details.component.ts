import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import DocumentService from './document.service';
import { type IDocument } from '@/shared/model/document.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'DocumentDetails',
  setup() {
    const documentService = inject('documentService', () => new DocumentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const document: Ref<IDocument> = ref({});

    const retrieveDocument = async documentId => {
      try {
        const res = await documentService().find(documentId);
        document.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.documentId) {
      retrieveDocument(route.params.documentId);
    }

    return {
      alertService,
      document,

      previousState,
      t$: useI18n().t,
    };
  },
});
