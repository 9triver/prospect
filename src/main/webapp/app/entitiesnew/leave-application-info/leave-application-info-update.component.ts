import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import LeaveApplicationInfoService from './leave-application-info.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type ILeaveApplicationInfo, LeaveApplicationInfo } from '@/shared/model/leave-application-info.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'LeaveApplicationInfoUpdate',
  setup() {
    const leaveApplicationInfoService = inject('leaveApplicationInfoService', () => new LeaveApplicationInfoService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const leaveApplicationInfo: Ref<ILeaveApplicationInfo> = ref(new LeaveApplicationInfo());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

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

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      startDate: {},
      endDate: {},
      leaveType: {},
      reason: {},
      status: {},
    };
    const v$ = useVuelidate(validationRules, leaveApplicationInfo as any);
    v$.value.$validate();

    return {
      leaveApplicationInfoService,
      alertService,
      leaveApplicationInfo,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.leaveApplicationInfo.id) {
        this.leaveApplicationInfoService()
          .update(this.leaveApplicationInfo)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.leaveApplicationInfo.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.leaveApplicationInfoService()
          .create(this.leaveApplicationInfo)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.leaveApplicationInfo.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
