import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import TechnicalService from './technical.service';
import { type ITechnical } from '@/shared/model/technical.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Technical',
  setup() {
    const { t: t$ } = useI18n();
    const technicalService = inject('technicalService', () => new TechnicalService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const technicals: Ref<ITechnical[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveTechnicals = async () => {
      isFetching.value = true;
      try {
        const res = await technicalService().retrieve();
        technicals.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveTechnicals();
    };

    onMounted(async () => {
      await retrieveTechnicals();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ITechnical) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeTechnical = async () => {
      try {
        await technicalService().delete(removeId.value);
        const message = t$('jy1App.technical.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveTechnicals();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      technicals,
      handleSyncList,
      isFetching,
      retrieveTechnicals,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeTechnical,
      t$,
    };
  },
});
