import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import DeviationPermitApplicationService from './deviation-permit-application.service';
import { type IDeviationPermitApplication } from '@/shared/model/deviation-permit-application.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'DeviationPermitApplication',
  setup() {
    const { t: t$ } = useI18n();
    const deviationPermitApplicationService = inject('deviationPermitApplicationService', () => new DeviationPermitApplicationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const deviationPermitApplications: Ref<IDeviationPermitApplication[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveDeviationPermitApplications = async () => {
      isFetching.value = true;
      try {
        const res = await deviationPermitApplicationService().retrieve();
        deviationPermitApplications.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveDeviationPermitApplications();
    };

    onMounted(async () => {
      await retrieveDeviationPermitApplications();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IDeviationPermitApplication) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeDeviationPermitApplication = async () => {
      try {
        await deviationPermitApplicationService().delete(removeId.value);
        const message = t$('jy1App.deviationPermitApplication.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveDeviationPermitApplications();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      deviationPermitApplications,
      handleSyncList,
      isFetching,
      retrieveDeviationPermitApplications,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeDeviationPermitApplication,
      t$,
    };
  },
});
