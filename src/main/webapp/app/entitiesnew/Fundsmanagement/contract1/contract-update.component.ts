import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ContractService from './contract.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import CostControlSystemService from '@/entities/cost-control-system/cost-control-system.service';
import { type ICostControlSystem } from '@/shared/model/cost-control-system.model';
import { type IContract, Contract } from '@/shared/model/contract.model';
import { ContractType } from '@/shared/model/enumerations/contract-type.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { ContractStatus } from '@/shared/model/enumerations/contract-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ContractUpdate',
  setup() {
    const contractService = inject('contractService', () => new ContractService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const contract: Ref<IContract> = ref(new Contract());

    const costControlSystemService = inject('costControlSystemService', () => new CostControlSystemService());

    const costControlSystems: Ref<ICostControlSystem[]> = ref([]);
    const contractTypeValues: Ref<string[]> = ref(Object.keys(ContractType));
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const contractStatusValues: Ref<string[]> = ref(Object.keys(ContractStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveContract = async contractId => {
      try {
        const res = await contractService().find(contractId);
        contract.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.contractId) {
      retrieveContract(route.params.contractId);
    }

    const initRelationships = () => {
      costControlSystemService()
        .retrieve()
        .then(res => {
          costControlSystems.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      contractcode: {},
      contractname: {},
      projectid: {},
      projectname: {},
      contracttype: {},
      year: {},
      amount: {},
      starttime: {},
      endtime: {},
      secretlevel: {},
      status: {},
      budgetamount: {},
      estimatedamount: {},
      implementedamount: {},
      difference: {},
      costControlSystems: {},
    };
    const v$ = useVuelidate(validationRules, contract as any);
    v$.value.$validate();

    return {
      contractService,
      alertService,
      contract,
      previousState,
      contractTypeValues,
      secretlevelValues,
      contractStatusValues,
      isSaving,
      currentLanguage,
      costControlSystems,
      v$,
      t$,
    };
  },
  created(): void {
    this.contract.costControlSystems = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.contract.id) {
        this.contractService()
          .update(this.contract)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.contract.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.contractService()
          .create(this.contract)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.contract.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },

    getSelected(selectedVals, option, pkField = 'id'): any {
      if (selectedVals) {
        return selectedVals.find(value => option[pkField] === value[pkField]) ?? option;
      }
      return option;
    },
  },
});
