import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import HumanresourcesService from './humanresources.service';
import { type IHumanresources } from '@/shared/model/humanresources.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'HumanresourcesDetails',
  setup() {
    const humanresourcesService = inject('humanresourcesService', () => new HumanresourcesService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const humanresources: Ref<IHumanresources> = ref({});

    const retrieveHumanresources = async humanresourcesId => {
      try {
        const res = await humanresourcesService().find(humanresourcesId);
        humanresources.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.humanresourcesId) {
      retrieveHumanresources(route.params.humanresourcesId);
    }

    return {
      alertService,
      humanresources,

      previousState,
      t$: useI18n().t,
    };
  },
});
