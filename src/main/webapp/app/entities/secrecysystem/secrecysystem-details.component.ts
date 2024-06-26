import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import SecrecysystemService from './secrecysystem.service';
import { type ISecrecysystem } from '@/shared/model/secrecysystem.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SecrecysystemDetails',
  setup() {
    const secrecysystemService = inject('secrecysystemService', () => new SecrecysystemService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const secrecysystem: Ref<ISecrecysystem> = ref({});

    const retrieveSecrecysystem = async secrecysystemId => {
      try {
        const res = await secrecysystemService().find(secrecysystemId);
        secrecysystem.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.secrecysystemId) {
      retrieveSecrecysystem(route.params.secrecysystemId);
    }

    return {
      alertService,
      secrecysystem,

      previousState,
      t$: useI18n().t,
    };
  },
});
