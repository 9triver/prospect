import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import KeyNodeInspectionService from './key-node-inspection.service';
import { type IKeyNodeInspection } from '@/shared/model/key-node-inspection.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'KeyNodeInspectionDetails',
  setup() {
    const keyNodeInspectionService = inject('keyNodeInspectionService', () => new KeyNodeInspectionService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const keyNodeInspection: Ref<IKeyNodeInspection> = ref({});

    const retrieveKeyNodeInspection = async keyNodeInspectionId => {
      try {
        const res = await keyNodeInspectionService().find(keyNodeInspectionId);
        keyNodeInspection.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.keyNodeInspectionId) {
      retrieveKeyNodeInspection(route.params.keyNodeInspectionId);
    }

    return {
      alertService,
      keyNodeInspection,

      previousState,
      t$: useI18n().t,
    };
  },
});
