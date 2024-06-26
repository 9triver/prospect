import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import AuditedbudgetService from './auditedbudget.service';
import { type IAuditedbudget } from '@/shared/model/auditedbudget.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Auditedbudget',
  setup() {
    const { t: t$ } = useI18n();
    const auditedbudgetService = inject('auditedbudgetService', () => new AuditedbudgetService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const auditedbudgets: Ref<IAuditedbudget[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveAuditedbudgets = async () => {
      isFetching.value = true;
      try {
        const res = await auditedbudgetService().retrieve();
        auditedbudgets.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveAuditedbudgets();
    };

    onMounted(async () => {
      await retrieveAuditedbudgets();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IAuditedbudget) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeAuditedbudget = async () => {
      try {
        await auditedbudgetService().delete(removeId.value);
        const message = t$('jHipster0App.auditedbudget.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveAuditedbudgets();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      auditedbudgets,
      handleSyncList,
      isFetching,
      retrieveAuditedbudgets,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeAuditedbudget,
      t$,
    };
  },
});
