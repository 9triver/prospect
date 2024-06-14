import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import SafetycheckService from './safetycheck.service';
import { type ISafetycheck } from '@/shared/model/safetycheck.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SafetycheckDetails',
  setup() {
    const safetycheckService = inject('safetycheckService', () => new SafetycheckService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const safetycheck: Ref<ISafetycheck> = ref({});

    const retrieveSafetycheck = async safetycheckId => {
      try {
        const res = await safetycheckService().find(safetycheckId);
        safetycheck.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.safetycheckId) {
      retrieveSafetycheck(route.params.safetycheckId);
    }

    return {
      alertService,
      safetycheck,

      previousState,
      t$: useI18n().t,
    };
  },
});
