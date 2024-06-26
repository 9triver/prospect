import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ProjectchargeService from './projectcharge.service';
import { type IProjectcharge } from '@/shared/model/projectcharge.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Projectcharge',
  setup() {
    const { t: t$ } = useI18n();
    const projectchargeService = inject('projectchargeService', () => new ProjectchargeService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const projectcharges: Ref<IProjectcharge[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveProjectcharges = async () => {
      isFetching.value = true;
      try {
        const res = await projectchargeService().retrieve();
        projectcharges.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveProjectcharges();
    };

    onMounted(async () => {
      await retrieveProjectcharges();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IProjectcharge) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeProjectcharge = async () => {
      try {
        await projectchargeService().delete(removeId.value);
        const message = t$('jHipster0App.projectcharge.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveProjectcharges();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      projectcharges,
      handleSyncList,
      isFetching,
      retrieveProjectcharges,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeProjectcharge,
      t$,
    };
  },
});
