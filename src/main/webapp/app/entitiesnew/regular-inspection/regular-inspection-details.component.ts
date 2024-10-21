import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import RegularInspectionService from './regular-inspection.service';
import { type IRegularInspection } from '@/shared/model/regular-inspection.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RegularInspectionDetails',
  setup() {
    const regularInspectionService = inject('regularInspectionService', () => new RegularInspectionService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const regularInspection: Ref<IRegularInspection> = ref({});

    const retrieveRegularInspection = async regularInspectionId => {
      try {
        const res = await regularInspectionService().find(regularInspectionId);
        regularInspection.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.regularInspectionId) {
      retrieveRegularInspection(route.params.regularInspectionId);
    }

    return {
      alertService,
      regularInspection,

      previousState,
      t$: useI18n().t,
    };
  },
});
