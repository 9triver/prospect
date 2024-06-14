import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import PbsmanageService from './pbsmanage.service';
import { type IPbsmanage } from '@/shared/model/pbsmanage.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PbsmanageDetails',
  setup() {
    const pbsmanageService = inject('pbsmanageService', () => new PbsmanageService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const pbsmanage: Ref<IPbsmanage> = ref({});

    const retrievePbsmanage = async pbsmanageId => {
      try {
        const res = await pbsmanageService().find(pbsmanageId);
        pbsmanage.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.pbsmanageId) {
      retrievePbsmanage(route.params.pbsmanageId);
    }

    return {
      alertService,
      pbsmanage,

      previousState,
      t$: useI18n().t,
    };
  },
});
