import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import SecuritymanagementWbsService from './securitymanagement-wbs.service';
import { type ISecuritymanagementWbs } from '@/shared/model/securitymanagement-wbs.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SecuritymanagementWbs',
  setup() {
    const { t: t$ } = useI18n();
    const securitymanagementWbsService = inject('securitymanagementWbsService', () => new SecuritymanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const securitymanagementWbs: Ref<ISecuritymanagementWbs[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveSecuritymanagementWbss = async () => {
      isFetching.value = true;
      try {
        const res = await securitymanagementWbsService().retrieve();
        securitymanagementWbs.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveSecuritymanagementWbss();
    };

    onMounted(async () => {
      await retrieveSecuritymanagementWbss();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ISecuritymanagementWbs) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeSecuritymanagementWbs = async () => {
      try {
        await securitymanagementWbsService().delete(removeId.value);
        const message = t$('jHipster0App.securitymanagementWbs.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveSecuritymanagementWbss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      securitymanagementWbs,
      handleSyncList,
      isFetching,
      retrieveSecuritymanagementWbss,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeSecuritymanagementWbs,
      t$,
    };
  },
});
