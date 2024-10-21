import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import WorkService from './work.service';
import { type IWork } from '@/shared/model/work.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'WorkDetails',
  setup() {
    const workService = inject('workService', () => new WorkService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const work: Ref<IWork> = ref({});

    const retrieveWork = async workId => {
      try {
        const res = await workService().find(workId);
        work.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.workId) {
      retrieveWork(route.params.workId);
    }

    return {
      alertService,
      work,

      previousState,
      t$: useI18n().t,
    };
  },
});
