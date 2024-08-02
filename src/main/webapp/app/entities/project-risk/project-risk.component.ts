import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ProjectRiskService from './project-risk.service';
import { type IProjectRisk } from '@/shared/model/project-risk.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectRisk',
  setup() {
    const { t: t$ } = useI18n();
    const projectRiskService = inject('projectRiskService', () => new ProjectRiskService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const projectRisks: Ref<IProjectRisk[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveProjectRisks = async () => {
      isFetching.value = true;
      try {
        const res = await projectRiskService().retrieve();
        projectRisks.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveProjectRisks();
    };

    onMounted(async () => {
      await retrieveProjectRisks();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IProjectRisk) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeProjectRisk = async () => {
      try {
        await projectRiskService().delete(removeId.value);
        const message = t$('jy1App.projectRisk.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveProjectRisks();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      projectRisks,
      handleSyncList,
      isFetching,
      retrieveProjectRisks,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeProjectRisk,
      t$,
    };
  },
});
