import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import DocumentmenuService from './documentmenu.service';
import { type IDocumentmenu } from '@/shared/model/documentmenu.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Documentmenu',
  setup() {
    const { t: t$ } = useI18n();
    const documentmenuService = inject('documentmenuService', () => new DocumentmenuService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const documentmenus: Ref<IDocumentmenu[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveDocumentmenus = async () => {
      isFetching.value = true;
      try {
        const res = await documentmenuService().retrieve();
        documentmenus.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveDocumentmenus();
    };

    onMounted(async () => {
      await retrieveDocumentmenus();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IDocumentmenu) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeDocumentmenu = async () => {
      try {
        await documentmenuService().delete(removeId.value);
        const message = t$('jy1App.documentmenu.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveDocumentmenus();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      documentmenus,
      handleSyncList,
      isFetching,
      retrieveDocumentmenus,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeDocumentmenu,
      t$,
    };
  },
});
