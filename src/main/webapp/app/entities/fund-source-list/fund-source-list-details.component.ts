import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import FundSourceListService from './fund-source-list.service';
import { type IFundSourceList } from '@/shared/model/fund-source-list.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'FundSourceListDetails',
  setup() {
    const fundSourceListService = inject('fundSourceListService', () => new FundSourceListService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const fundSourceList: Ref<IFundSourceList> = ref({});

    const retrieveFundSourceList = async fundSourceListId => {
      try {
        const res = await fundSourceListService().find(fundSourceListId);
        fundSourceList.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.fundSourceListId) {
      retrieveFundSourceList(route.params.fundSourceListId);
    }

    return {
      alertService,
      fundSourceList,

      previousState,
      t$: useI18n().t,
    };
  },
});
