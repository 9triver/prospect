import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import AuditedbudgetService from './auditedbudget.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import TotalbudgetService from '@/entities/totalbudget/totalbudget.service';
import { type ITotalbudget } from '@/shared/model/totalbudget.model';
import UnitbudgetService from '@/entities/unitbudget/unitbudget.service';
import { type IUnitbudget } from '@/shared/model/unitbudget.model';
import DocumentService from '@/entities/document/document.service';
import { type IDocument } from '@/shared/model/document.model';
import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IAuditedbudget, Auditedbudget } from '@/shared/model/auditedbudget.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'AuditedbudgetUpdate',
  setup() {
    const auditedbudgetService = inject('auditedbudgetService', () => new AuditedbudgetService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const auditedbudget: Ref<IAuditedbudget> = ref(new Auditedbudget());

    const totalbudgetService = inject('totalbudgetService', () => new TotalbudgetService());

    const totalbudgets: Ref<ITotalbudget[]> = ref([]);

    const unitbudgetService = inject('unitbudgetService', () => new UnitbudgetService());

    const unitbudgets: Ref<IUnitbudget[]> = ref([]);

    const documentService = inject('documentService', () => new DocumentService());

    const documents: Ref<IDocument[]> = ref([]);

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveAuditedbudget = async auditedbudgetId => {
      try {
        const res = await auditedbudgetService().find(auditedbudgetId);
        auditedbudget.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.auditedbudgetId) {
      retrieveAuditedbudget(route.params.auditedbudgetId);
    }

    const initRelationships = () => {
      totalbudgetService()
        .retrieve()
        .then(res => {
          totalbudgets.value = res.data;
        });
      unitbudgetService()
        .retrieve()
        .then(res => {
          unitbudgets.value = res.data;
        });
      documentService()
        .retrieve()
        .then(res => {
          documents.value = res.data;
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
      createtime: {},
      creatorname: {},
      secretlevel: {},
      year: {},
      budgit: {},
      dapartmentid: {},
      draftapproval: {},
      totalbudgetid: {},
      unitbudgetid: {},
      documentid: {},
      maintainerid: {},
      auditStatus: {},
      totalbudget: {},
      unitbudget: {},
      document: {},
      creatorid: {},
      auditorid: {},
    };
    const v$ = useVuelidate(validationRules, auditedbudget as any);
    v$.value.$validate();

    return {
      auditedbudgetService,
      alertService,
      auditedbudget,
      previousState,
      secretlevelValues,
      auditStatusValues,
      isSaving,
      currentLanguage,
      totalbudgets,
      unitbudgets,
      documents,
      officers,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.auditedbudget.id) {
        this.auditedbudgetService()
          .update(this.auditedbudget)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.auditedbudget.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.auditedbudgetService()
          .create(this.auditedbudget)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.auditedbudget.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
