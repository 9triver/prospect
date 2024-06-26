import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import IntegratedmanagementWbsService from './integratedmanagement-wbs.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import PlanstrategyService from '@/entities/planstrategy/planstrategy.service';
import { type IPlanstrategy } from '@/shared/model/planstrategy.model';
import ComprehensivecontrolService from '@/entities/comprehensivecontrol/comprehensivecontrol.service';
import { type IComprehensivecontrol } from '@/shared/model/comprehensivecontrol.model';
import DocumentService from '@/entities/document/document.service';
import { type IDocument } from '@/shared/model/document.model';
import ComprehensiveledgerService from '@/entities/comprehensiveledger/comprehensiveledger.service';
import { type IComprehensiveledger } from '@/shared/model/comprehensiveledger.model';
import CycleplanService from '@/entities/cycleplan/cycleplan.service';
import { type ICycleplan } from '@/shared/model/cycleplan.model';
import AnnualplanService from '@/entities/annualplan/annualplan.service';
import { type IAnnualplan } from '@/shared/model/annualplan.model';
import MonthplanService from '@/entities/monthplan/monthplan.service';
import { type IMonthplan } from '@/shared/model/monthplan.model';
import PlanreturnsService from '@/entities/planreturns/planreturns.service';
import { type IPlanreturns } from '@/shared/model/planreturns.model';
import PlanmonitorService from '@/entities/planmonitor/planmonitor.service';
import { type IPlanmonitor } from '@/shared/model/planmonitor.model';
import PlanexecuteService from '@/entities/planexecute/planexecute.service';
import { type IPlanexecute } from '@/shared/model/planexecute.model';
import ProjectchargeService from '@/entities/projectcharge/projectcharge.service';
import { type IProjectcharge } from '@/shared/model/projectcharge.model';
import { type IIntegratedmanagementWbs, IntegratedmanagementWbs } from '@/shared/model/integratedmanagement-wbs.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'IntegratedmanagementWbsUpdate',
  setup() {
    const integratedmanagementWbsService = inject('integratedmanagementWbsService', () => new IntegratedmanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const integratedmanagementWbs: Ref<IIntegratedmanagementWbs> = ref(new IntegratedmanagementWbs());

    const planstrategyService = inject('planstrategyService', () => new PlanstrategyService());

    const planstrategies: Ref<IPlanstrategy[]> = ref([]);

    const comprehensivecontrolService = inject('comprehensivecontrolService', () => new ComprehensivecontrolService());

    const comprehensivecontrols: Ref<IComprehensivecontrol[]> = ref([]);

    const documentService = inject('documentService', () => new DocumentService());

    const documents: Ref<IDocument[]> = ref([]);

    const comprehensiveledgerService = inject('comprehensiveledgerService', () => new ComprehensiveledgerService());

    const comprehensiveledgers: Ref<IComprehensiveledger[]> = ref([]);

    const cycleplanService = inject('cycleplanService', () => new CycleplanService());

    const cycleplans: Ref<ICycleplan[]> = ref([]);

    const annualplanService = inject('annualplanService', () => new AnnualplanService());

    const annualplans: Ref<IAnnualplan[]> = ref([]);

    const monthplanService = inject('monthplanService', () => new MonthplanService());

    const monthplans: Ref<IMonthplan[]> = ref([]);

    const planreturnsService = inject('planreturnsService', () => new PlanreturnsService());

    const planreturns: Ref<IPlanreturns[]> = ref([]);

    const planmonitorService = inject('planmonitorService', () => new PlanmonitorService());

    const planmonitors: Ref<IPlanmonitor[]> = ref([]);

    const planexecuteService = inject('planexecuteService', () => new PlanexecuteService());

    const planexecutes: Ref<IPlanexecute[]> = ref([]);

    const projectchargeService = inject('projectchargeService', () => new ProjectchargeService());

    const projectcharges: Ref<IProjectcharge[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveIntegratedmanagementWbs = async integratedmanagementWbsId => {
      try {
        const res = await integratedmanagementWbsService().find(integratedmanagementWbsId);
        integratedmanagementWbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.integratedmanagementWbsId) {
      retrieveIntegratedmanagementWbs(route.params.integratedmanagementWbsId);
    }

    const initRelationships = () => {
      planstrategyService()
        .retrieve()
        .then(res => {
          planstrategies.value = res.data;
        });
      comprehensivecontrolService()
        .retrieve()
        .then(res => {
          comprehensivecontrols.value = res.data;
        });
      documentService()
        .retrieve()
        .then(res => {
          documents.value = res.data;
        });
      comprehensiveledgerService()
        .retrieve()
        .then(res => {
          comprehensiveledgers.value = res.data;
        });
      cycleplanService()
        .retrieve()
        .then(res => {
          cycleplans.value = res.data;
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
      planreturnsService()
        .retrieve()
        .then(res => {
          planreturns.value = res.data;
        });
      planmonitorService()
        .retrieve()
        .then(res => {
          planmonitors.value = res.data;
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
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      wbsspare1: {},
      wbsspare2: {},
      wbsspare3: {},
      wbsspare4: {},
      wbsspare5: {},
      planstrategy: {},
      comprehensivecontrol: {},
      document: {},
      comprehensiveledger: {},
      cycleplan: {},
      annualplan: {},
      monthplan: {},
      planreturns: {},
      planmonitor: {},
      planexecute: {},
      projectcharge: {},
    };
    const v$ = useVuelidate(validationRules, integratedmanagementWbs as any);
    v$.value.$validate();

    return {
      integratedmanagementWbsService,
      alertService,
      integratedmanagementWbs,
      previousState,
      isSaving,
      currentLanguage,
      planstrategies,
      comprehensivecontrols,
      documents,
      comprehensiveledgers,
      cycleplans,
      annualplans,
      monthplans,
      planreturns,
      planmonitors,
      planexecutes,
      projectcharges,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.integratedmanagementWbs.id) {
        this.integratedmanagementWbsService()
          .update(this.integratedmanagementWbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.integratedmanagementWbs.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.integratedmanagementWbsService()
          .create(this.integratedmanagementWbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.integratedmanagementWbs.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
