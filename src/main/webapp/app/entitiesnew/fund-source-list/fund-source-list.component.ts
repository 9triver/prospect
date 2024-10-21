import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import FundSourceListService from './fund-source-list.service';
import { type IFundSourceList } from '@/shared/model/fund-source-list.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'FundSourceList',
  setup() {
    const { t: t$ } = useI18n();
    const fundSourceListService = inject('fundSourceListService', () => new FundSourceListService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const fundSourceLists: Ref<IFundSourceList[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveFundSourceLists = async () => {
      isFetching.value = true;
      try {
        const res = await fundSourceListService().retrieve();
        fundSourceLists.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveFundSourceLists();
    };

    onMounted(async () => {
      await retrieveFundSourceLists();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IFundSourceList) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeFundSourceList = async () => {
      try {
        await fundSourceListService().delete(removeId.value);
        const message = t$('jy1App.fundSourceList.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveFundSourceLists();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      fundSourceLists,
      handleSyncList,
      isFetching,
      retrieveFundSourceLists,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeFundSourceList,
      t$,
    };
  },
});
