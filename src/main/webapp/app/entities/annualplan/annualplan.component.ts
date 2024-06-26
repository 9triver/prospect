import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import AnnualplanService from './annualplan.service';
import { type IAnnualplan } from '@/shared/model/annualplan.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Annualplan',
  setup() {
    const { t: t$ } = useI18n();
    const annualplanService = inject('annualplanService', () => new AnnualplanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const annualplans: Ref<IAnnualplan[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveAnnualplans = async () => {
      isFetching.value = true;
      try {
        const res = await annualplanService().retrieve();
        annualplans.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveAnnualplans();
    };

    onMounted(async () => {
      await retrieveAnnualplans();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IAnnualplan) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeAnnualplan = async () => {
      try {
        await annualplanService().delete(removeId.value);
        const message = t$('jHipster0App.annualplan.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveAnnualplans();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      annualplans,
      handleSyncList,
      isFetching,
      retrieveAnnualplans,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeAnnualplan,
      t$,
    };
  },
});
