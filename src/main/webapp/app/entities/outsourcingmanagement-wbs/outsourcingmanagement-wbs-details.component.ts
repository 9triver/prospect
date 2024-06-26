import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import OutsourcingmanagementWbsService from './outsourcingmanagement-wbs.service';
import { type IOutsourcingmanagementWbs } from '@/shared/model/outsourcingmanagement-wbs.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OutsourcingmanagementWbsDetails',
  setup() {
    const outsourcingmanagementWbsService = inject('outsourcingmanagementWbsService', () => new OutsourcingmanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const outsourcingmanagementWbs: Ref<IOutsourcingmanagementWbs> = ref({});

    const retrieveOutsourcingmanagementWbs = async outsourcingmanagementWbsId => {
      try {
        const res = await outsourcingmanagementWbsService().find(outsourcingmanagementWbsId);
        outsourcingmanagementWbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.outsourcingmanagementWbsId) {
      retrieveOutsourcingmanagementWbs(route.params.outsourcingmanagementWbsId);
    }

    return {
      alertService,
      outsourcingmanagementWbs,

      previousState,
      t$: useI18n().t,
    };
  },
});
