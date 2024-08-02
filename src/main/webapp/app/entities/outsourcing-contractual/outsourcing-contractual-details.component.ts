import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import OutsourcingContractualService from './outsourcing-contractual.service';
import { type IOutsourcingContractual } from '@/shared/model/outsourcing-contractual.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OutsourcingContractualDetails',
  setup() {
    const outsourcingContractualService = inject('outsourcingContractualService', () => new OutsourcingContractualService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const outsourcingContractual: Ref<IOutsourcingContractual> = ref({});

    const retrieveOutsourcingContractual = async outsourcingContractualId => {
      try {
        const res = await outsourcingContractualService().find(outsourcingContractualId);
        outsourcingContractual.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.outsourcingContractualId) {
      retrieveOutsourcingContractual(route.params.outsourcingContractualId);
    }

    return {
      alertService,
      outsourcingContractual,

      previousState,
      t$: useI18n().t,
    };
  },
});
