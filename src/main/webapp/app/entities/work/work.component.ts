import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import WorkService from './work.service';
import { type IWork } from '@/shared/model/work.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Work',
  setup() {
    const { t: t$ } = useI18n();
    const workService = inject('workService', () => new WorkService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const works: Ref<IWork[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveWorks = async () => {
      isFetching.value = true;
      try {
        const res = await workService().retrieve();
        works.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveWorks();
    };

    onMounted(async () => {
      await retrieveWorks();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IWork) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeWork = async () => {
      try {
        await workService().delete(removeId.value);
        const message = t$('jy1App.work.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveWorks();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      works,
      handleSyncList,
      isFetching,
      retrieveWorks,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeWork,
      t$,
    };
  },
});
