import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import WbsmanageService from './wbsmanage.service';
import { type IWbsmanage } from '@/shared/model/wbsmanage.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'WbsmanageDetails',
  setup() {
    const wbsmanageService = inject('wbsmanageService', () => new WbsmanageService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const wbsmanage: Ref<IWbsmanage> = ref({});

    const retrieveWbsmanage = async wbsmanageId => {
      try {
        const res = await wbsmanageService().find(wbsmanageId);
        wbsmanage.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.wbsmanageId) {
      retrieveWbsmanage(route.params.wbsmanageId);
    }

    return {
      alertService,
      wbsmanage,

      previousState,
      t$: useI18n().t,
    };
  },
});
