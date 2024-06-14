import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import AnnualSecurityPlanService from './annual-security-plan.service';
import { type IAnnualSecurityPlan } from '@/shared/model/annual-security-plan.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'AnnualSecurityPlan',
  setup() {
    const { t: t$ } = useI18n();
    const annualSecurityPlanService = inject('annualSecurityPlanService', () => new AnnualSecurityPlanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const annualSecurityPlans: Ref<IAnnualSecurityPlan[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveAnnualSecurityPlans = async () => {
      isFetching.value = true;
      try {
        const res = await annualSecurityPlanService().retrieve();
        annualSecurityPlans.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveAnnualSecurityPlans();
    };

    onMounted(async () => {
      await retrieveAnnualSecurityPlans();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IAnnualSecurityPlan) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeAnnualSecurityPlan = async () => {
      try {
        await annualSecurityPlanService().delete(removeId.value);
        const message = t$('jHipster3App.annualSecurityPlan.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveAnnualSecurityPlans();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      annualSecurityPlans,
      handleSyncList,
      isFetching,
      retrieveAnnualSecurityPlans,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeAnnualSecurityPlan,
      t$,
    };
  },
});
