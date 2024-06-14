import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ProjectchargeService from './projectcharge.service';
import { type IProjectcharge } from '@/shared/model/projectcharge.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectchargeDetails',
  setup() {
    const projectchargeService = inject('projectchargeService', () => new ProjectchargeService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const projectcharge: Ref<IProjectcharge> = ref({});

    const retrieveProjectcharge = async projectchargeId => {
      try {
        const res = await projectchargeService().find(projectchargeId);
        projectcharge.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.projectchargeId) {
      retrieveProjectcharge(route.params.projectchargeId);
    }

    return {
      alertService,
      projectcharge,

      previousState,
      t$: useI18n().t,
    };
  },
});
