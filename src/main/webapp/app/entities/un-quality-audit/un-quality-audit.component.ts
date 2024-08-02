import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import UnQualityAuditService from './un-quality-audit.service';
import { type IUnQualityAudit } from '@/shared/model/un-quality-audit.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'UnQualityAudit',
  setup() {
    const { t: t$ } = useI18n();
    const unQualityAuditService = inject('unQualityAuditService', () => new UnQualityAuditService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const unQualityAudits: Ref<IUnQualityAudit[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveUnQualityAudits = async () => {
      isFetching.value = true;
      try {
        const res = await unQualityAuditService().retrieve();
        unQualityAudits.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveUnQualityAudits();
    };

    onMounted(async () => {
      await retrieveUnQualityAudits();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IUnQualityAudit) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeUnQualityAudit = async () => {
      try {
        await unQualityAuditService().delete(removeId.value);
        const message = t$('jy1App.unQualityAudit.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveUnQualityAudits();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      unQualityAudits,
      handleSyncList,
      isFetching,
      retrieveUnQualityAudits,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeUnQualityAudit,
      t$,
    };
  },
});
