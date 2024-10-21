import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import DeliveryContentService from './delivery-content.service';
import { type IDeliveryContent } from '@/shared/model/delivery-content.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'DeliveryContentDetails',
  setup() {
    const deliveryContentService = inject('deliveryContentService', () => new DeliveryContentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const deliveryContent: Ref<IDeliveryContent> = ref({});

    const retrieveDeliveryContent = async deliveryContentId => {
      try {
        const res = await deliveryContentService().find(deliveryContentId);
        deliveryContent.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.deliveryContentId) {
      retrieveDeliveryContent(route.params.deliveryContentId);
    }

    return {
      alertService,
      deliveryContent,

      previousState,
      t$: useI18n().t,
    };
  },
});
