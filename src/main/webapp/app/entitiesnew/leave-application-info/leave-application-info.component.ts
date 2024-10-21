import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import LeaveApplicationInfoService from './leave-application-info.service';
import { type ILeaveApplicationInfo } from '@/shared/model/leave-application-info.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'LeaveApplicationInfo',
  setup() {
    const { t: t$ } = useI18n();
    const leaveApplicationInfoService = inject('leaveApplicationInfoService', () => new LeaveApplicationInfoService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const leaveApplicationInfos: Ref<ILeaveApplicationInfo[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveLeaveApplicationInfos = async () => {
      isFetching.value = true;
      try {
        const res = await leaveApplicationInfoService().retrieve();
        leaveApplicationInfos.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveLeaveApplicationInfos();
    };

    onMounted(async () => {
      await retrieveLeaveApplicationInfos();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ILeaveApplicationInfo) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeLeaveApplicationInfo = async () => {
      try {
        await leaveApplicationInfoService().delete(removeId.value);
        const message = t$('jy1App.leaveApplicationInfo.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveLeaveApplicationInfos();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      leaveApplicationInfos,
      handleSyncList,
      isFetching,
      retrieveLeaveApplicationInfos,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeLeaveApplicationInfo,
      t$,
    };
  },
});
