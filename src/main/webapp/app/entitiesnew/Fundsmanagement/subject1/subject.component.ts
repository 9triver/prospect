import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import SubjectService from './subject.service';
import { type ISubject } from '@/shared/model/subject.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Subject',
  setup() {
    const { t: t$ } = useI18n();
    const subjectService = inject('subjectService', () => new SubjectService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const subjects: Ref<ISubject[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveSubjects = async () => {
      isFetching.value = true;
      try {
        const res = await subjectService().retrieve();
        subjects.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveSubjects();
    };

    onMounted(async () => {
      await retrieveSubjects();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ISubject) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeSubject = async () => {
      try {
        await subjectService().delete(removeId.value);
        const message = t$('jy1App.subject.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveSubjects();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      subjects,
      handleSyncList,
      isFetching,
      retrieveSubjects,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeSubject,
      t$,
    };
  },
});
