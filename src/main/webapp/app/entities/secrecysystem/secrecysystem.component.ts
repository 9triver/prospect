import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import SecrecysystemService from './secrecysystem.service';
import { type ISecrecysystem } from '@/shared/model/secrecysystem.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Secrecysystem',
  setup() {
    const { t: t$ } = useI18n();
    const secrecysystemService = inject('secrecysystemService', () => new SecrecysystemService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const secrecysystems: Ref<ISecrecysystem[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveSecrecysystems = async () => {
      isFetching.value = true;
      try {
        const res = await secrecysystemService().retrieve();
        secrecysystems.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveSecrecysystems();
    };

    onMounted(async () => {
      await retrieveSecrecysystems();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ISecrecysystem) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeSecrecysystem = async () => {
      try {
        await secrecysystemService().delete(removeId.value);
        const message = t$('jHipster0App.secrecysystem.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveSecrecysystems();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      secrecysystems,
      handleSyncList,
      isFetching,
      retrieveSecrecysystems,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeSecrecysystem,
      t$,
    };
  },
});
