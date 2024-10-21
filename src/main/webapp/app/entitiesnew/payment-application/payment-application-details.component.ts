import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import PaymentApplicationService from './payment-application.service';
import { type IPaymentApplication } from '@/shared/model/payment-application.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PaymentApplicationDetails',
  setup() {
    const paymentApplicationService = inject('paymentApplicationService', () => new PaymentApplicationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const paymentApplication: Ref<IPaymentApplication> = ref({});

    const retrievePaymentApplication = async paymentApplicationId => {
      try {
        const res = await paymentApplicationService().find(paymentApplicationId);
        paymentApplication.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.paymentApplicationId) {
      retrievePaymentApplication(route.params.paymentApplicationId);
    }

    return {
      alertService,
      paymentApplication,

      previousState,
      t$: useI18n().t,
    };
  },
});
