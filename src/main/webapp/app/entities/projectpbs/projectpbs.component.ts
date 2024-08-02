import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ProjectpbsService from './projectpbs.service';
import { type IProjectpbs } from '@/shared/model/projectpbs.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Projectpbs',
  setup() {
    const { t: t$ } = useI18n();
    const projectpbsService = inject('projectpbsService', () => new ProjectpbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const projectpbs: Ref<IProjectpbs[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveProjectpbss = async () => {
      isFetching.value = true;
      try {
        const res = await projectpbsService().retrieve();
        projectpbs.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveProjectpbss();
    };

    // const filterProjectpbsLikeName(likeName: String){
    //   this.searchTerms.next(likeName)
    // }

    onMounted(async () => {
      await retrieveProjectpbss();
    });

    //删除
    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IProjectpbs) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeProjectpbs = async () => {
      try {
        await projectpbsService().delete(removeId.value);
        const message = t$('jy1App.projectpbs.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveProjectpbss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      projectpbs,
      handleSyncList,
      isFetching,
      retrieveProjectpbss,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeProjectpbs,
      t$,
    };
  },
});
