import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import DeliveryContentService from './delivery-content.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OutsourcingContractService from '@/entities/outsourcing-contract/outsourcing-contract.service';
import { type IOutsourcingContract } from '@/shared/model/outsourcing-contract.model';
import { type IDeliveryContent, DeliveryContent } from '@/shared/model/delivery-content.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'DeliveryContentUpdate',
  setup() {
    const deliveryContentService = inject('deliveryContentService', () => new DeliveryContentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const deliveryContent: Ref<IDeliveryContent> = ref(new DeliveryContent());

    const outsourcingContractService = inject('outsourcingContractService', () => new OutsourcingContractService());

    const outsourcingContracts: Ref<IOutsourcingContract[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveDeliveryContent = async deliveryContentId => {
      try {
        const res = await deliveryContentService().find(deliveryContentId);
        deliveryContent.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.deliveryContentId) {
      retrieveDeliveryContent(route.params.deliveryContentId);
    }

    const initRelationships = () => {
      outsourcingContractService()
        .retrieve()
        .then(res => {
          outsourcingContracts.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      warrantyrequirement: {},
      purchaseplanno: {},
      purchaseplandate: {},
      purchaseplanamount: {},
      purchasemethod: {},
      purchasesecretlevel: {},
      reviewmethod: {},
      requirementdepartment: {},
      requirementperson: {},
      undertaker: {},
      undertakingdepartment: {},
      workbagid: {},
      projectmanager: {},
      fundsource: {},
      thesisname: {},
      contractauxiliaryno: {},
      reasonfornosuppliers: {},
      reasonforchange: {},
      negotiationfiletime: {},
      bidopeningtime: {},
      judges: {},
      responsevendorname: {},
      finalquoteandscore: {},
      noticeofcompletiontime: {},
      signingdate: {},
      contractenddate: {},
      actualcompletiontime: {},
      issubmitsecrecyagreement: {},
      issubmitsecurityagreement: {},
      remark: {},
      outsourcingContract: {},
    };
    const v$ = useVuelidate(validationRules, deliveryContent as any);
    v$.value.$validate();

    return {
      deliveryContentService,
      alertService,
      deliveryContent,
      previousState,
      isSaving,
      currentLanguage,
      outsourcingContracts,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.deliveryContent.id) {
        this.deliveryContentService()
          .update(this.deliveryContent)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.deliveryContent.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.deliveryContentService()
          .create(this.deliveryContent)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.deliveryContent.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
