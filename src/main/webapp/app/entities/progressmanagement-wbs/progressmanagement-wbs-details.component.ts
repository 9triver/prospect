import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ProgressmanagementWbsService from './progressmanagement-wbs.service';
import { type IProgressmanagementWbs } from '@/shared/model/progressmanagement-wbs.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProgressmanagementWbsDetails',
  setup() {
    const progressmanagementWbsService = inject('progressmanagementWbsService', () => new ProgressmanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const progressmanagementWbs: Ref<IProgressmanagementWbs> = ref({});

    const retrieveProgressmanagementWbs = async progressmanagementWbsId => {
      try {
        const res = await progressmanagementWbsService().find(progressmanagementWbsId);
        progressmanagementWbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.progressmanagementWbsId) {
      retrieveProgressmanagementWbs(route.params.progressmanagementWbsId);
    }

    return {
      alertService,
      progressmanagementWbs,

      previousState,
      t$: useI18n().t,
    };
  },
});
