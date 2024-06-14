import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import PbsbaselineService from './pbsbaseline.service';
import { type IPbsbaseline } from '@/shared/model/pbsbaseline.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PbsbaselineDetails',
  setup() {
    const pbsbaselineService = inject('pbsbaselineService', () => new PbsbaselineService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const pbsbaseline: Ref<IPbsbaseline> = ref({});

    const retrievePbsbaseline = async pbsbaselineId => {
      try {
        const res = await pbsbaselineService().find(pbsbaselineId);
        pbsbaseline.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.pbsbaselineId) {
      retrievePbsbaseline(route.params.pbsbaselineId);
    }

    return {
      alertService,
      pbsbaseline,

      previousState,
      t$: useI18n().t,
    };
  },
});
