import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import LetterService from './letter.service';
import { type ILetter } from '@/shared/model/letter.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'LetterDetails',
  setup() {
    const letterService = inject('letterService', () => new LetterService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const letter: Ref<ILetter> = ref({});

    const retrieveLetter = async letterId => {
      try {
        const res = await letterService().find(letterId);
        letter.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.letterId) {
      retrieveLetter(route.params.letterId);
    }

    return {
      alertService,
      letter,

      previousState,
      t$: useI18n().t,
    };
  },
});
