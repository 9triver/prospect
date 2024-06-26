import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import SecuritymanagementService from './securitymanagement.service';
import { type ISecuritymanagement } from '@/shared/model/securitymanagement.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Securitymanagement',
  setup() {
    const { t: t$ } = useI18n();
    const securitymanagementService = inject('securitymanagementService', () => new SecuritymanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const securitymanagements: Ref<ISecuritymanagement[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveSecuritymanagements = async () => {
      isFetching.value = true;
      try {
        const res = await securitymanagementService().retrieve();
        securitymanagements.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveSecuritymanagements();
    };

    onMounted(async () => {
      await retrieveSecuritymanagements();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ISecuritymanagement) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeSecuritymanagement = async () => {
      try {
        await securitymanagementService().delete(removeId.value);
        const message = t$('jHipster0App.securitymanagement.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveSecuritymanagements();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      securitymanagements,
      handleSyncList,
      isFetching,
      retrieveSecuritymanagements,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeSecuritymanagement,
      t$,
    };
  },
});
