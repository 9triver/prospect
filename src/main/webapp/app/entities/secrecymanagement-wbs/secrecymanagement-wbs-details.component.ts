import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import SecrecymanagementWbsService from './secrecymanagement-wbs.service';
import { type ISecrecymanagementWbs } from '@/shared/model/secrecymanagement-wbs.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SecrecymanagementWbsDetails',
  setup() {
    const secrecymanagementWbsService = inject('secrecymanagementWbsService', () => new SecrecymanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const secrecymanagementWbs: Ref<ISecrecymanagementWbs> = ref({});

    const retrieveSecrecymanagementWbs = async secrecymanagementWbsId => {
      try {
        const res = await secrecymanagementWbsService().find(secrecymanagementWbsId);
        secrecymanagementWbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.secrecymanagementWbsId) {
      retrieveSecrecymanagementWbs(route.params.secrecymanagementWbsId);
    }

    return {
      alertService,
      secrecymanagementWbs,

      previousState,
      t$: useI18n().t,
    };
  },
});
