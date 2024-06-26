import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import CycleplanService from './cycleplan.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import DocumentService from '@/entities/document/document.service';
import { type IDocument } from '@/shared/model/document.model';
import AnnualplanService from '@/entities/annualplan/annualplan.service';
import { type IAnnualplan } from '@/shared/model/annualplan.model';
import MonthplanService from '@/entities/monthplan/monthplan.service';
import { type IMonthplan } from '@/shared/model/monthplan.model';
import ProjectchargeService from '@/entities/projectcharge/projectcharge.service';
import { type IProjectcharge } from '@/shared/model/projectcharge.model';
import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type ICycleplan, Cycleplan } from '@/shared/model/cycleplan.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { Cycleplanstatus } from '@/shared/model/enumerations/cycleplanstatus.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CycleplanUpdate',
  setup() {
    const cycleplanService = inject('cycleplanService', () => new CycleplanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const cycleplan: Ref<ICycleplan> = ref(new Cycleplan());

    const documentService = inject('documentService', () => new DocumentService());

    const documents: Ref<IDocument[]> = ref([]);

    const annualplanService = inject('annualplanService', () => new AnnualplanService());

    const annualplans: Ref<IAnnualplan[]> = ref([]);

    const monthplanService = inject('monthplanService', () => new MonthplanService());

    const monthplans: Ref<IMonthplan[]> = ref([]);

    const projectchargeService = inject('projectchargeService', () => new ProjectchargeService());

    const projectcharges: Ref<IProjectcharge[]> = ref([]);

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const cycleplanstatusValues: Ref<string[]> = ref(Object.keys(Cycleplanstatus));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveCycleplan = async cycleplanId => {
      try {
        const res = await cycleplanService().find(cycleplanId);
        cycleplan.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.cycleplanId) {
      retrieveCycleplan(route.params.cycleplanId);
    }

    const initRelationships = () => {
      documentService()
        .retrieve()
        .then(res => {
          documents.value = res.data;
        });
      annualplanService()
        .retrieve()
        .then(res => {
          annualplans.value = res.data;
        });
      monthplanService()
        .retrieve()
        .then(res => {
          monthplans.value = res.data;
        });
      projectchargeService()
        .retrieve()
        .then(res => {
          projectcharges.value = res.data;
        });
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
      cycleplanname: {},
      secretlevel: {},
      starttime: {},
      endtime: {},
      actualstarttime: {},
      actualendtime: {},
      responsiblename: {},
      status: {},
      auditStatus: {},
      document: {},
      annualplan: {},
      monthplan: {},
      projectcharge: {},
      responsibleid: {},
      auditorid: {},
    };
    const v$ = useVuelidate(validationRules, cycleplan as any);
    v$.value.$validate();

    return {
      cycleplanService,
      alertService,
      cycleplan,
      previousState,
      secretlevelValues,
      cycleplanstatusValues,
      auditStatusValues,
      isSaving,
      currentLanguage,
      documents,
      annualplans,
      monthplans,
      projectcharges,
      officers,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.cycleplan.id) {
        this.cycleplanService()
          .update(this.cycleplan)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.cycleplan.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.cycleplanService()
          .create(this.cycleplan)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.cycleplan.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
