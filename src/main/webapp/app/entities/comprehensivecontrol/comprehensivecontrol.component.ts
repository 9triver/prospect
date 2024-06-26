import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ComprehensivecontrolService from './comprehensivecontrol.service';
import { type IComprehensivecontrol } from '@/shared/model/comprehensivecontrol.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Comprehensivecontrol',
  setup() {
    const { t: t$ } = useI18n();
    const comprehensivecontrolService = inject('comprehensivecontrolService', () => new ComprehensivecontrolService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const comprehensivecontrols: Ref<IComprehensivecontrol[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveComprehensivecontrols = async () => {
      isFetching.value = true;
      try {
        const res = await comprehensivecontrolService().retrieve();
        comprehensivecontrols.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveComprehensivecontrols();
    };

    onMounted(async () => {
      await retrieveComprehensivecontrols();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IComprehensivecontrol) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeComprehensivecontrol = async () => {
      try {
        await comprehensivecontrolService().delete(removeId.value);
        const message = t$('jHipster0App.comprehensivecontrol.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveComprehensivecontrols();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      comprehensivecontrols,
      handleSyncList,
      isFetching,
      retrieveComprehensivecontrols,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeComprehensivecontrol,
      t$,
    };
  },
});
