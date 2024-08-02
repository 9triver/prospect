import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import CostControlSystemService from './cost-control-system.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import ContractService from '@/entities/contract/contract.service';
import { type IContract } from '@/shared/model/contract.model';
import { type ICostControlSystem, CostControlSystem } from '@/shared/model/cost-control-system.model';
import { ContractSubject } from '@/shared/model/enumerations/contract-subject.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CostControlSystemUpdate',
  setup() {
    const costControlSystemService = inject('costControlSystemService', () => new CostControlSystemService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const costControlSystem: Ref<ICostControlSystem> = ref(new CostControlSystem());

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);

    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());

    const projectwbs: Ref<IProjectwbs[]> = ref([]);

    const contractService = inject('contractService', () => new ContractService());

    const contracts: Ref<IContract[]> = ref([]);
    const contractSubjectValues: Ref<string[]> = ref(Object.keys(ContractSubject));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveCostControlSystem = async costControlSystemId => {
      try {
        const res = await costControlSystemService().find(costControlSystemId);
        costControlSystem.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.costControlSystemId) {
      retrieveCostControlSystem(route.params.costControlSystemId);
    }

    const initRelationships = () => {
      officersService()
        .retrieve()
        .then(res => {
          officers.value = res.data;
        });
      projectwbsService()
        .retrieve()
        .then(res => {
          projectwbs.value = res.data;
        });
      contractService()
        .retrieve()
        .then(res => {
          contracts.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      type: {},
      subject: {},
      implementedamount: {},
      approvedamount: {},
      pendingimplementationamount: {},
      contractpaymentamount: {},
      managementregistrationnumber: {},
      financialregistrationnumber: {},
      contractbudgetamount: {},
      contractsigningamount: {},
      contractsettlementamount: {},
      unforeseeableamount: {},
      invoicepaymentamount: {},
      loanpaymentamount: {},
      accountoutstandingamount: {},
      pendingpaymentamount: {},
      pendinginvoiceamount: {},
      responsibleperson: {},
      auditorid: {},
      projectwbs: {},
      contracts: {},
    };
    const v$ = useVuelidate(validationRules, costControlSystem as any);
    v$.value.$validate();

    return {
      costControlSystemService,
      alertService,
      costControlSystem,
      previousState,
      contractSubjectValues,
      isSaving,
      currentLanguage,
      officers,
      projectwbs,
      contracts,
      v$,
      t$,
    };
  },
  created(): void {
    this.costControlSystem.projectwbs = [];
    this.costControlSystem.contracts = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.costControlSystem.id) {
        this.costControlSystemService()
          .update(this.costControlSystem)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.costControlSystem.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.costControlSystemService()
          .create(this.costControlSystem)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.costControlSystem.created', { param: param.id }).toString());
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
