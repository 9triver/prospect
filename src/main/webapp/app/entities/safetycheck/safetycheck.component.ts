import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import SafetycheckService from './safetycheck.service';
import { type ISafetycheck } from '@/shared/model/safetycheck.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Safetycheck',
  setup() {
    const { t: t$ } = useI18n();
    const safetycheckService = inject('safetycheckService', () => new SafetycheckService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const safetychecks: Ref<ISafetycheck[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveSafetychecks = async () => {
      isFetching.value = true;
      try {
        const res = await safetycheckService().retrieve();
        safetychecks.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveSafetychecks();
    };

    onMounted(async () => {
      await retrieveSafetychecks();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ISafetycheck) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeSafetycheck = async () => {
      try {
        await safetycheckService().delete(removeId.value);
        const message = t$('jHipster0App.safetycheck.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveSafetychecks();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      safetychecks,
      handleSyncList,
      isFetching,
      retrieveSafetychecks,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeSafetycheck,
      t$,
    };
  },
});
