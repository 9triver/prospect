import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import WbssubmanageService from './wbssubmanage.service';
import { type IWbssubmanage } from '@/shared/model/wbssubmanage.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'WbssubmanageDetails',
  setup() {
    const wbssubmanageService = inject('wbssubmanageService', () => new WbssubmanageService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const wbssubmanage: Ref<IWbssubmanage> = ref({});

    const retrieveWbssubmanage = async wbssubmanageId => {
      try {
        const res = await wbssubmanageService().find(wbssubmanageId);
        wbssubmanage.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.wbssubmanageId) {
      retrieveWbssubmanage(route.params.wbssubmanageId);
    }

    return {
      alertService,
      wbssubmanage,

      previousState,
      t$: useI18n().t,
    };
  },
});
