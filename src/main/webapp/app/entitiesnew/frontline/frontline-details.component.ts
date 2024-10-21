import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import FrontlineService from './frontline.service';
import { type IFrontline } from '@/shared/model/frontline.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'FrontlineDetails',
  setup() {
    const frontlineService = inject('frontlineService', () => new FrontlineService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const frontline: Ref<IFrontline> = ref({});

    const retrieveFrontline = async frontlineId => {
      try {
        const res = await frontlineService().find(frontlineId);
        frontline.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.frontlineId) {
      retrieveFrontline(route.params.frontlineId);
    }

    return {
      alertService,
      frontline,

      previousState,
      t$: useI18n().t,
    };
  },
});
