import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import FundsmanagementService from './fundsmanagement.service';
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
import { type IFundsmanagement, Fundsmanagement } from '@/shared/model/fundsmanagement.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'FundsmanagementUpdate',
  setup() {
    const fundsmanagementService = inject('fundsmanagementService', () => new FundsmanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const fundsmanagement: Ref<IFundsmanagement> = ref(new Fundsmanagement());

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

    const retrieveFundsmanagement = async fundsmanagementId => {
      try {
        const res = await fundsmanagementService().find(fundsmanagementId);
        fundsmanagement.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.fundsmanagementId) {
      retrieveFundsmanagement(route.params.fundsmanagementId);
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
      fundsid: {
        integer: validations.integer(t$('entity.validation.number').toString()),
      },
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
    const v$ = useVuelidate(validationRules, fundsmanagement as any);
    v$.value.$validate();

    return {
      fundsmanagementService,
      alertService,
      fundsmanagement,
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
      if (this.fundsmanagement.id) {
        this.fundsmanagementService()
          .update(this.fundsmanagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster3App.fundsmanagement.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.fundsmanagementService()
          .create(this.fundsmanagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster3App.fundsmanagement.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
