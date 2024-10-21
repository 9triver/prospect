import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import SystemLevelService from './system-level.service';
import { type ISystemLevel } from '@/shared/model/system-level.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SystemLevelDetails',
  setup() {
    const systemLevelService = inject('systemLevelService', () => new SystemLevelService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const systemLevel: Ref<ISystemLevel> = ref({});

    const retrieveSystemLevel = async systemLevelId => {
      try {
        const res = await systemLevelService().find(systemLevelId);
        systemLevel.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.systemLevelId) {
      retrieveSystemLevel(route.params.systemLevelId);
    }

    return {
      alertService,
      systemLevel,

      previousState,
      t$: useI18n().t,
    };
  },
});
