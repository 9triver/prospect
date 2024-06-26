import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ContractualfundsService from './contractualfunds.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IContractualfunds, Contractualfunds } from '@/shared/model/contractualfunds.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ContractualfundsUpdate',
  setup() {
    const contractualfundsService = inject('contractualfundsService', () => new ContractualfundsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const contractualfunds: Ref<IContractualfunds> = ref(new Contractualfunds());

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveContractualfunds = async contractualfundsId => {
      try {
        const res = await contractualfundsService().find(contractualfundsId);
        contractualfunds.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.contractualfundsId) {
      retrieveContractualfunds(route.params.contractualfundsId);
    }

    const initRelationships = () => {
      officersService()
        .retrieve()
        .then(res => {
          officers.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      department: {},
      year: {},
      starttime: {},
      endtime: {},
      status: {},
      secretlevel: {},
      foreigncurrency: {},
      totalbudget: {},
      fundsinplace: {},
      responsibleunitname: {},
      audittime: {},
      accountbank: {},
      creatorid: {},
      auditorid: {},
    };
    const v$ = useVuelidate(validationRules, contractualfunds as any);
    v$.value.$validate();

    return {
      contractualfundsService,
      alertService,
      contractualfunds,
      previousState,
      secretlevelValues,
      isSaving,
      currentLanguage,
      officers,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.contractualfunds.id) {
        this.contractualfundsService()
          .update(this.contractualfunds)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.contractualfunds.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.contractualfundsService()
          .create(this.contractualfunds)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.contractualfunds.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
