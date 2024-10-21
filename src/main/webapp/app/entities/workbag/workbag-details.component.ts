import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import WorkbagService from './workbag.service';
import { type IWorkbag } from '@/shared/model/workbag.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'WorkbagDetails',
  setup() {
    const workbagService = inject('workbagService', () => new WorkbagService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const workbag: Ref<IWorkbag> = ref({});

    const retrieveWorkbag = async workbagId => {
      try {
        const res = await workbagService().find(workbagId);
        workbag.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.workbagId) {
      retrieveWorkbag(route.params.workbagId);
    }

    return {
      alertService,
      workbag,

      previousState,
      t$: useI18n().t,
    };
  },
});
