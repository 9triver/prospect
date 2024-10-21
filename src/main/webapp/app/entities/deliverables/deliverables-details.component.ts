import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import DeliverablesService from './deliverables.service';
import { type IDeliverables } from '@/shared/model/deliverables.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'DeliverablesDetails',
  setup() {
    const deliverablesService = inject('deliverablesService', () => new DeliverablesService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const deliverables: Ref<IDeliverables> = ref({});

    const retrieveDeliverables = async deliverablesId => {
      try {
        const res = await deliverablesService().find(deliverablesId);
        deliverables.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.deliverablesId) {
      retrieveDeliverables(route.params.deliverablesId);
    }

    return {
      alertService,
      deliverables,

      previousState,
      t$: useI18n().t,
    };
  },
});
