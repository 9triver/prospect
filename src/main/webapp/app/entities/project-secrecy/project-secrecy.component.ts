import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ProjectSecrecyService from './project-secrecy.service';
import { type IProjectSecrecy } from '@/shared/model/project-secrecy.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectSecrecy',
  setup() {
    const { t: t$ } = useI18n();
    const projectSecrecyService = inject('projectSecrecyService', () => new ProjectSecrecyService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const projectSecrecies: Ref<IProjectSecrecy[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveProjectSecrecys = async () => {
      isFetching.value = true;
      try {
        const res = await projectSecrecyService().retrieve();
        projectSecrecies.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveProjectSecrecys();
    };

    onMounted(async () => {
      await retrieveProjectSecrecys();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IProjectSecrecy) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeProjectSecrecy = async () => {
      try {
        await projectSecrecyService().delete(removeId.value);
        const message = t$('jHipster0App.projectSecrecy.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveProjectSecrecys();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      projectSecrecies,
      handleSyncList,
      isFetching,
      retrieveProjectSecrecys,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeProjectSecrecy,
      t$,
    };
  },
});
