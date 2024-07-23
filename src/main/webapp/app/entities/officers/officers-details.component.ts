import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import OfficersService from './officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OfficersDetails',
  setup() {
    const officersService = inject('officersService', () => new OfficersService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const officers: Ref<IOfficers> = ref({});

    const retrieveOfficers = async officersId => {
      try {
        const res = await officersService().find(officersId);
        officers.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.officersId) {
      retrieveOfficers(route.params.officersId);
    }

    return {
      alertService,
      officers,

      previousState,
      t$: useI18n().t,
    };
  },
});
