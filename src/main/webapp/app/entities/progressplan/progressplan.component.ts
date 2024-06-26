import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ProgressplanService from './progressplan.service';
import { type IProgressplan } from '@/shared/model/progressplan.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Progressplan',
  setup() {
    const { t: t$ } = useI18n();
    const progressplanService = inject('progressplanService', () => new ProgressplanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const progressplans: Ref<IProgressplan[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveProgressplans = async () => {
      isFetching.value = true;
      try {
        const res = await progressplanService().retrieve();
        progressplans.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveProgressplans();
    };

    onMounted(async () => {
      await retrieveProgressplans();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IProgressplan) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeProgressplan = async () => {
      try {
        await progressplanService().delete(removeId.value);
        const message = t$('jHipster0App.progressplan.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveProgressplans();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      progressplans,
      handleSyncList,
      isFetching,
      retrieveProgressplans,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeProgressplan,
      t$,
    };
  },
});
