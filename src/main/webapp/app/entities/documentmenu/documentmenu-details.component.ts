import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import DocumentmenuService from './documentmenu.service';
import { type IDocumentmenu } from '@/shared/model/documentmenu.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'DocumentmenuDetails',
  setup() {
    const documentmenuService = inject('documentmenuService', () => new DocumentmenuService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const documentmenu: Ref<IDocumentmenu> = ref({});

    const retrieveDocumentmenu = async documentmenuId => {
      try {
        const res = await documentmenuService().find(documentmenuId);
        documentmenu.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.documentmenuId) {
      retrieveDocumentmenu(route.params.documentmenuId);
    }

    return {
      alertService,
      documentmenu,

      previousState,
      t$: useI18n().t,
    };
  },
});
