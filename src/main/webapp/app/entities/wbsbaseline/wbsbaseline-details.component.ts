import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import WbsbaselineService from './wbsbaseline.service';
import { type IWbsbaseline } from '@/shared/model/wbsbaseline.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'WbsbaselineDetails',
  setup() {
    const wbsbaselineService = inject('wbsbaselineService', () => new WbsbaselineService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const wbsbaseline: Ref<IWbsbaseline> = ref({});

    const retrieveWbsbaseline = async wbsbaselineId => {
      try {
        const res = await wbsbaselineService().find(wbsbaselineId);
        wbsbaseline.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.wbsbaselineId) {
      retrieveWbsbaseline(route.params.wbsbaselineId);
    }

    return {
      alertService,
      wbsbaseline,

      previousState,
      t$: useI18n().t,
    };
  },
});
