import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import SharePaymentService from './share-payment.service';
import { type ISharePayment } from '@/shared/model/share-payment.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SharePaymentDetails',
  setup() {
    const sharePaymentService = inject('sharePaymentService', () => new SharePaymentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const sharePayment: Ref<ISharePayment> = ref({});

    const retrieveSharePayment = async sharePaymentId => {
      try {
        const res = await sharePaymentService().find(sharePaymentId);
        sharePayment.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.sharePaymentId) {
      retrieveSharePayment(route.params.sharePaymentId);
    }

    return {
      alertService,
      sharePayment,

      previousState,
      t$: useI18n().t,
    };
  },
});
