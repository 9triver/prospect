import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ProjectremitService from './projectremit.service';
import { type IProjectremit } from '@/shared/model/projectremit.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Projectremit',
  setup() {
    const { t: t$ } = useI18n();
    const projectremitService = inject('projectremitService', () => new ProjectremitService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const projectremits: Ref<IProjectremit[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveProjectremits = async () => {
      isFetching.value = true;
      try {
        const res = await projectremitService().retrieve();
        projectremits.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveProjectremits();
    };

    onMounted(async () => {
      await retrieveProjectremits();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IProjectremit) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeProjectremit = async () => {
      try {
        await projectremitService().delete(removeId.value);
        const message = t$('jy1App.projectremit.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveProjectremits();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      projectremits,
      handleSyncList,
      isFetching,
      retrieveProjectremits,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeProjectremit,
      t$,
    };
  },
});
