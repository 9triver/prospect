import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import OutsourcingContractualService from './outsourcing-contractual.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { type IOutsourcingContractual, OutsourcingContractual } from '@/shared/model/outsourcing-contractual.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OutsourcingContractualUpdate',
  setup() {
    const outsourcingContractualService = inject('outsourcingContractualService', () => new OutsourcingContractualService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const outsourcingContractual: Ref<IOutsourcingContractual> = ref(new OutsourcingContractual());

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);

    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());

    const projectwbs: Ref<IProjectwbs[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveOutsourcingContractual = async outsourcingContractualId => {
      try {
        const res = await outsourcingContractualService().find(outsourcingContractualId);
        outsourcingContractual.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.outsourcingContractualId) {
      retrieveOutsourcingContractual(route.params.outsourcingContractualId);
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
      projectwbs: {},
    };
    const v$ = useVuelidate(validationRules, outsourcingContractual as any);
    v$.value.$validate();

    return {
      outsourcingContractualService,
      alertService,
      outsourcingContractual,
      previousState,
      secretlevelValues,
      isSaving,
      currentLanguage,
      officers,
      projectwbs,
      v$,
      t$,
    };
  },
  created(): void {
    this.outsourcingContractual.projectwbs = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.outsourcingContractual.id) {
        this.outsourcingContractualService()
          .update(this.outsourcingContractual)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.outsourcingContractual.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.outsourcingContractualService()
          .create(this.outsourcingContractual)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.outsourcingContractual.created', { param: param.id }).toString());
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
