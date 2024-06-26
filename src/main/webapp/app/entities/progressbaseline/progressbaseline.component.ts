import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ProgressbaselineService from './progressbaseline.service';
import { type IProgressbaseline } from '@/shared/model/progressbaseline.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Progressbaseline',
  setup() {
    const { t: t$ } = useI18n();
    const progressbaselineService = inject('progressbaselineService', () => new ProgressbaselineService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const progressbaselines: Ref<IProgressbaseline[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveProgressbaselines = async () => {
      isFetching.value = true;
      try {
        const res = await progressbaselineService().retrieve();
        progressbaselines.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveProgressbaselines();
    };

    onMounted(async () => {
      await retrieveProgressbaselines();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IProgressbaseline) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeProgressbaseline = async () => {
      try {
        await progressbaselineService().delete(removeId.value);
        const message = t$('jHipster0App.progressbaseline.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveProgressbaselines();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      progressbaselines,
      handleSyncList,
      isFetching,
      retrieveProgressbaselines,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeProgressbaseline,
      t$,
    };
  },
});
