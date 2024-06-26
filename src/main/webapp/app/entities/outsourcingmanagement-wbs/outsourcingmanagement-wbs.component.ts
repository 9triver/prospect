import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import OutsourcingmanagementWbsService from './outsourcingmanagement-wbs.service';
import { type IOutsourcingmanagementWbs } from '@/shared/model/outsourcingmanagement-wbs.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OutsourcingmanagementWbs',
  setup() {
    const { t: t$ } = useI18n();
    const outsourcingmanagementWbsService = inject('outsourcingmanagementWbsService', () => new OutsourcingmanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const outsourcingmanagementWbs: Ref<IOutsourcingmanagementWbs[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveOutsourcingmanagementWbss = async () => {
      isFetching.value = true;
      try {
        const res = await outsourcingmanagementWbsService().retrieve();
        outsourcingmanagementWbs.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveOutsourcingmanagementWbss();
    };

    onMounted(async () => {
      await retrieveOutsourcingmanagementWbss();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IOutsourcingmanagementWbs) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeOutsourcingmanagementWbs = async () => {
      try {
        await outsourcingmanagementWbsService().delete(removeId.value);
        const message = t$('jHipster0App.outsourcingmanagementWbs.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveOutsourcingmanagementWbss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      outsourcingmanagementWbs,
      handleSyncList,
      isFetching,
      retrieveOutsourcingmanagementWbss,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeOutsourcingmanagementWbs,
      t$,
    };
  },
});
