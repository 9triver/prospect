import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import LetterService from './letter.service';
import { type ILetter } from '@/shared/model/letter.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Letter',
  setup() {
    const { t: t$ } = useI18n();
    const letterService = inject('letterService', () => new LetterService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const letters: Ref<ILetter[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveLetters = async () => {
      isFetching.value = true;
      try {
        const res = await letterService().retrieve();
        letters.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveLetters();
    };

    onMounted(async () => {
      await retrieveLetters();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ILetter) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeLetter = async () => {
      try {
        await letterService().delete(removeId.value);
        const message = t$('jy1App.letter.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveLetters();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      letters,
      handleSyncList,
      isFetching,
      retrieveLetters,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeLetter,
      t$,
    };
  },
});
