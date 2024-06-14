import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import AnnualplanService from './annualplan.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import DocumentService from '@/entities/document/document.service';
import { type IDocument } from '@/shared/model/document.model';
import MonthplanService from '@/entities/monthplan/monthplan.service';
import { type IMonthplan } from '@/shared/model/monthplan.model';
import ProjectchargeService from '@/entities/projectcharge/projectcharge.service';
import { type IProjectcharge } from '@/shared/model/projectcharge.model';
import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IAnnualplan, Annualplan } from '@/shared/model/annualplan.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { Annualplanstatus } from '@/shared/model/enumerations/annualplanstatus.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'AnnualplanUpdate',
  setup() {
    const annualplanService = inject('annualplanService', () => new AnnualplanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const annualplan: Ref<IAnnualplan> = ref(new Annualplan());

    const documentService = inject('documentService', () => new DocumentService());

    const documents: Ref<IDocument[]> = ref([]);

    const monthplanService = inject('monthplanService', () => new MonthplanService());

    const monthplans: Ref<IMonthplan[]> = ref([]);

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

    const retrieveAnnualplan = async annualplanId => {
      try {
        const res = await annualplanService().find(annualplanId);
        annualplan.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.annualplanId) {
      retrieveAnnualplan(route.params.annualplanId);
    }

    const initRelationships = () => {
      documentService()
        .retrieve()
        .then(res => {
          documents.value = res.data;
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
      annualplanid: {
        integer: validations.integer(t$('entity.validation.number').toString()),
      },
      annualplanname: {},
      year: {},
      secretlevel: {},
      creatorname: {},
      status: {},
      auditStatus: {},
      document: {},
      monthplan: {},
      projectcharge: {},
      creatorid: {},
      auditorid: {},
    };
    const v$ = useVuelidate(validationRules, annualplan as any);
    v$.value.$validate();

    return {
      annualplanService,
      alertService,
      annualplan,
      previousState,
      secretlevelValues,
      annualplanstatusValues,
      auditStatusValues,
      isSaving,
      currentLanguage,
      documents,
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
      if (this.annualplan.id) {
        this.annualplanService()
          .update(this.annualplan)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster3App.annualplan.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.annualplanService()
          .create(this.annualplan)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster3App.annualplan.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
