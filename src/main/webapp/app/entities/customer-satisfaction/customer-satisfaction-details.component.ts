import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import CustomerSatisfactionService from './customer-satisfaction.service';
import { type ICustomerSatisfaction } from '@/shared/model/customer-satisfaction.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CustomerSatisfactionDetails',
  setup() {
    const customerSatisfactionService = inject('customerSatisfactionService', () => new CustomerSatisfactionService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const customerSatisfaction: Ref<ICustomerSatisfaction> = ref({});

    const retrieveCustomerSatisfaction = async customerSatisfactionId => {
      try {
        const res = await customerSatisfactionService().find(customerSatisfactionId);
        customerSatisfaction.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.customerSatisfactionId) {
      retrieveCustomerSatisfaction(route.params.customerSatisfactionId);
    }

    return {
      alertService,
      customerSatisfaction,

      previousState,
      t$: useI18n().t,
    };
  },
});
