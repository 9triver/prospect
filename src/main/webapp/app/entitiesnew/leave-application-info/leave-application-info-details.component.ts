import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import LeaveApplicationInfoService from './leave-application-info.service';
import { type ILeaveApplicationInfo } from '@/shared/model/leave-application-info.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'LeaveApplicationInfoDetails',
  setup() {
    const leaveApplicationInfoService = inject('leaveApplicationInfoService', () => new LeaveApplicationInfoService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const leaveApplicationInfo: Ref<ILeaveApplicationInfo> = ref({});

    const retrieveLeaveApplicationInfo = async leaveApplicationInfoId => {
      try {
        const res = await leaveApplicationInfoService().find(leaveApplicationInfoId);
        leaveApplicationInfo.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.leaveApplicationInfoId) {
      retrieveLeaveApplicationInfo(route.params.leaveApplicationInfoId);
    }

    return {
      alertService,
      leaveApplicationInfo,

      previousState,
      t$: useI18n().t,
    };
  },
});
