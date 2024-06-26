import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import OfficersService from './officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Officers',
  setup() {
    const { t: t$ } = useI18n();
    const officersService = inject('officersService', () => new OfficersService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const officers: Ref<IOfficers[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveOfficerss = async () => {
      isFetching.value = true;
      try {
        const res = await officersService().retrieve();
        officers.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveOfficerss();
    };

    onMounted(async () => {
      await retrieveOfficerss();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IOfficers) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeOfficers = async () => {
      try {
        await officersService().delete(removeId.value);
        const message = t$('jHipster0App.officers.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveOfficerss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      officers,
      handleSyncList,
      isFetching,
      retrieveOfficerss,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeOfficers,
      t$,
    };
  },
});
