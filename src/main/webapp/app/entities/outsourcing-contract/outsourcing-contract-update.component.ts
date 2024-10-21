import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import OutsourcingContractService from './outsourcing-contract.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import WorkbagService from '@/entities/workbag/workbag.service';
import { type IWorkbag } from '@/shared/model/workbag.model';
import { type IOutsourcingContract, OutsourcingContract } from '@/shared/model/outsourcing-contract.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OutsourcingContractUpdate',
  setup() {
    const outsourcingContractService = inject('outsourcingContractService', () => new OutsourcingContractService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const outsourcingContract: Ref<IOutsourcingContract> = ref(new OutsourcingContract());

    const workbagService = inject('workbagService', () => new WorkbagService());

    const workbags: Ref<IWorkbag[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveOutsourcingContract = async outsourcingContractId => {
      try {
        const res = await outsourcingContractService().find(outsourcingContractId);
        outsourcingContract.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.outsourcingContractId) {
      retrieveOutsourcingContract(route.params.outsourcingContractId);
    }

    const initRelationships = () => {
      workbagService()
        .retrieve()
        .then(res => {
          workbags.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      contractid: {},
      contractcode: {},
      contractname: {},
      contractqualityid: {},
      contractcostid: {},
      contractfinanceid: {},
      projectid: {},
      projectsecretlevel: {},
      counterpartyunit: {},
      negotiationdate: {},
      negotiationlocation: {},
      negotiator: {},
      budgetamount: {},
      contractamount: {},
      approver: {},
      approvaldate: {},
      contractsecretlevel: {},
      workbag: {},
    };
    const v$ = useVuelidate(validationRules, outsourcingContract as any);
    v$.value.$validate();

    return {
      outsourcingContractService,
      alertService,
      outsourcingContract,
      previousState,
      isSaving,
      currentLanguage,
      workbags,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.outsourcingContract.id) {
        this.outsourcingContractService()
          .update(this.outsourcingContract)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.outsourcingContract.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.outsourcingContractService()
          .create(this.outsourcingContract)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.outsourcingContract.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
