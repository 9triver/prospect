import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import OtherPaymentService from './other-payment.service';
import { type IOtherPayment } from '@/shared/model/other-payment.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OtherPaymentDetails',
  setup() {
    const otherPaymentService = inject('otherPaymentService', () => new OtherPaymentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const otherPayment: Ref<IOtherPayment> = ref({});

    const retrieveOtherPayment = async otherPaymentId => {
      try {
        const res = await otherPaymentService().find(otherPaymentId);
        otherPayment.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.otherPaymentId) {
      retrieveOtherPayment(route.params.otherPaymentId);
    }

    return {
      alertService,
      otherPayment,

      previousState,
      t$: useI18n().t,
    };
  },
});
