import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ArchivesService from './archives.service';
import { type IArchives } from '@/shared/model/archives.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Archives',
  setup() {
    const { t: t$ } = useI18n();
    const archivesService = inject('archivesService', () => new ArchivesService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const archives: Ref<IArchives[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveArchivess = async () => {
      isFetching.value = true;
      try {
        const res = await archivesService().retrieve();
        archives.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveArchivess();
    };

    onMounted(async () => {
      await retrieveArchivess();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IArchives) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeArchives = async () => {
      try {
        await archivesService().delete(removeId.value);
        const message = t$('jy1App.archives.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveArchivess();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      archives,
      handleSyncList,
      isFetching,
      retrieveArchivess,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeArchives,
      t$,
    };
  },
});
