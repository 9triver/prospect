import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import PbssubmanageService from './pbssubmanage.service';
import { type IPbssubmanage } from '@/shared/model/pbssubmanage.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PbssubmanageDetails',
  setup() {
    const pbssubmanageService = inject('pbssubmanageService', () => new PbssubmanageService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const pbssubmanage: Ref<IPbssubmanage> = ref({});

    const retrievePbssubmanage = async pbssubmanageId => {
      try {
        const res = await pbssubmanageService().find(pbssubmanageId);
        pbssubmanage.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.pbssubmanageId) {
      retrievePbssubmanage(route.params.pbssubmanageId);
    }

    return {
      alertService,
      pbssubmanage,

      previousState,
      t$: useI18n().t,
    };
  },
});
