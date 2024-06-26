import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import HumanresourcesService from './humanresources.service';
import { type IHumanresources } from '@/shared/model/humanresources.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Humanresources',
  setup() {
    const { t: t$ } = useI18n();
    const humanresourcesService = inject('humanresourcesService', () => new HumanresourcesService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const humanresources: Ref<IHumanresources[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveHumanresourcess = async () => {
      isFetching.value = true;
      try {
        const res = await humanresourcesService().retrieve();
        humanresources.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveHumanresourcess();
    };

    onMounted(async () => {
      await retrieveHumanresourcess();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IHumanresources) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeHumanresources = async () => {
      try {
        await humanresourcesService().delete(removeId.value);
        const message = t$('jHipster0App.humanresources.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveHumanresourcess();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      humanresources,
      handleSyncList,
      isFetching,
      retrieveHumanresourcess,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeHumanresources,
      t$,
    };
  },
});
