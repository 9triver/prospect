import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import MonthplanService from './monthplan.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import DocumentService from '@/entities/document/document.service';
import { type IDocument } from '@/shared/model/document.model';
import PlanexecuteService from '@/entities/planexecute/planexecute.service';
import { type IPlanexecute } from '@/shared/model/planexecute.model';
import ProjectchargeService from '@/entities/projectcharge/projectcharge.service';
import { type IProjectcharge } from '@/shared/model/projectcharge.model';
import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IMonthplan, Monthplan } from '@/shared/model/monthplan.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { Annualplanstatus } from '@/shared/model/enumerations/annualplanstatus.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MonthplanUpdate',
  setup() {
    const monthplanService = inject('monthplanService', () => new MonthplanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const monthplan: Ref<IMonthplan> = ref(new Monthplan());

    const documentService = inject('documentService', () => new DocumentService());

    const documents: Ref<IDocument[]> = ref([]);

    const planexecuteService = inject('planexecuteService', () => new PlanexecuteService());

    const planexecutes: Ref<IPlanexecute[]> = ref([]);

    const projectchargeService = inject('projectchargeService', () => new ProjectchargeService());

    const projectcharges: Ref<IProjectcharge[]> = ref([]);

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const annualplanstatusValues: Ref<string[]> = ref(Object.keys(Annualplanstatus));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveMonthplan = async monthplanId => {
      try {
        const res = await monthplanService().find(monthplanId);
        monthplan.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.monthplanId) {
      retrieveMonthplan(route.params.monthplanId);
    }

    const initRelationships = () => {
      documentService()
        .retrieve()
        .then(res => {
          documents.value = res.data;
        });
      planexecuteService()
        .retrieve()
        .then(res => {
          planexecutes.value = res.data;
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
      monthplanid: {
        integer: validations.integer(t$('entity.validation.number').toString()),
      },
      monthplanname: {},
      month: {},
      secretlevel: {},
      creatorname: {},
      status: {},
      auditStatus: {},
      document: {},
      planreturns: {},
      projectcharge: {},
      creatorid: {},
      auditorid: {},
    };
    const v$ = useVuelidate(validationRules, monthplan as any);
    v$.value.$validate();

    return {
      monthplanService,
      alertService,
      monthplan,
      previousState,
      secretlevelValues,
      annualplanstatusValues,
      auditStatusValues,
      isSaving,
      currentLanguage,
      documents,
      planexecutes,
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
      if (this.monthplan.id) {
        this.monthplanService()
          .update(this.monthplan)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster3App.monthplan.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.monthplanService()
          .create(this.monthplan)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster3App.monthplan.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
