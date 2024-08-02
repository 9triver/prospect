import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import TechnicalConditionService from './technical-condition.service';
import { type ITechnicalCondition } from '@/shared/model/technical-condition.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TechnicalCondition',
  setup() {
    const { t: t$ } = useI18n();
    const technicalConditionService = inject('technicalConditionService', () => new TechnicalConditionService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const technicalConditions: Ref<ITechnicalCondition[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveTechnicalConditions = async () => {
      isFetching.value = true;
      try {
        const res = await technicalConditionService().retrieve();
        technicalConditions.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveTechnicalConditions();
    };

    onMounted(async () => {
      await retrieveTechnicalConditions();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ITechnicalCondition) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeTechnicalCondition = async () => {
      try {
        await technicalConditionService().delete(removeId.value);
        const message = t$('jy1App.technicalCondition.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveTechnicalConditions();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      technicalConditions,
      handleSyncList,
      isFetching,
      retrieveTechnicalConditions,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeTechnicalCondition,
      t$,
    };
  },
});
