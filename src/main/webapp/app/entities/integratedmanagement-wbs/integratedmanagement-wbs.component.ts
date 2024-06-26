import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import IntegratedmanagementWbsService from './integratedmanagement-wbs.service';
import { type IIntegratedmanagementWbs } from '@/shared/model/integratedmanagement-wbs.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'IntegratedmanagementWbs',
  setup() {
    const { t: t$ } = useI18n();
    const integratedmanagementWbsService = inject('integratedmanagementWbsService', () => new IntegratedmanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const integratedmanagementWbs: Ref<IIntegratedmanagementWbs[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveIntegratedmanagementWbss = async () => {
      isFetching.value = true;
      try {
        const res = await integratedmanagementWbsService().retrieve();
        integratedmanagementWbs.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveIntegratedmanagementWbss();
    };

    onMounted(async () => {
      await retrieveIntegratedmanagementWbss();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IIntegratedmanagementWbs) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeIntegratedmanagementWbs = async () => {
      try {
        await integratedmanagementWbsService().delete(removeId.value);
        const message = t$('jHipster0App.integratedmanagementWbs.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveIntegratedmanagementWbss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      integratedmanagementWbs,
      handleSyncList,
      isFetching,
      retrieveIntegratedmanagementWbss,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeIntegratedmanagementWbs,
      t$,
    };
  },
});
