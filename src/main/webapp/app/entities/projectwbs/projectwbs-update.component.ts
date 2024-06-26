import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ProjectwbsService from './projectwbs.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import CycleplanService from '@/entities/cycleplan/cycleplan.service';
import { type ICycleplan } from '@/shared/model/cycleplan.model';
import ProgressmanagementService from '@/entities/progressmanagement/progressmanagement.service';
import { type IProgressmanagement } from '@/shared/model/progressmanagement.model';
import QualitymanagementService from '@/entities/qualitymanagement/qualitymanagement.service';
import { type IQualitymanagement } from '@/shared/model/qualitymanagement.model';
import FundsmanagementService from '@/entities/fundsmanagement/fundsmanagement.service';
import { type IFundsmanagement } from '@/shared/model/fundsmanagement.model';
import TechnicalmanagementService from '@/entities/technicalmanagement/technicalmanagement.service';
import { type ITechnicalmanagement } from '@/shared/model/technicalmanagement.model';
import ContractualfundsService from '@/entities/contractualfunds/contractualfunds.service';
import { type IContractualfunds } from '@/shared/model/contractualfunds.model';
import OutsourcingmanagementService from '@/entities/outsourcingmanagement/outsourcingmanagement.service';
import { type IOutsourcingmanagement } from '@/shared/model/outsourcingmanagement.model';
import ResourcemanagementService from '@/entities/resourcemanagement/resourcemanagement.service';
import { type IResourcemanagement } from '@/shared/model/resourcemanagement.model';
import RiskmanagementService from '@/entities/riskmanagement/riskmanagement.service';
import { type IRiskmanagement } from '@/shared/model/riskmanagement.model';
import SecuritymanagementService from '@/entities/securitymanagement/securitymanagement.service';
import { type ISecuritymanagement } from '@/shared/model/securitymanagement.model';
import DocumentService from '@/entities/document/document.service';
import { type IDocument } from '@/shared/model/document.model';
import SafetycheckService from '@/entities/safetycheck/safetycheck.service';
import { type ISafetycheck } from '@/shared/model/safetycheck.model';
import DepartmentService from '@/entities/department/department.service';
import { type IDepartment } from '@/shared/model/department.model';
import EvaluationCriteriaService from '@/entities/evaluation-criteria/evaluation-criteria.service';
import { type IEvaluationCriteria } from '@/shared/model/evaluation-criteria.model';
import { type IProjectwbs, Projectwbs } from '@/shared/model/projectwbs.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectwbsUpdate',
  setup() {
    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const projectwbs: Ref<IProjectwbs> = ref(new Projectwbs());

    const cycleplanService = inject('cycleplanService', () => new CycleplanService());

    const cycleplans: Ref<ICycleplan[]> = ref([]);

    const progressmanagementService = inject('progressmanagementService', () => new ProgressmanagementService());

    const progressmanagements: Ref<IProgressmanagement[]> = ref([]);

    const qualitymanagementService = inject('qualitymanagementService', () => new QualitymanagementService());

    const qualitymanagements: Ref<IQualitymanagement[]> = ref([]);

    const fundsmanagementService = inject('fundsmanagementService', () => new FundsmanagementService());

    const fundsmanagements: Ref<IFundsmanagement[]> = ref([]);

    const technicalmanagementService = inject('technicalmanagementService', () => new TechnicalmanagementService());

    const technicalmanagements: Ref<ITechnicalmanagement[]> = ref([]);

    const contractualfundsService = inject('contractualfundsService', () => new ContractualfundsService());

    const contractualfunds: Ref<IContractualfunds[]> = ref([]);

    const outsourcingmanagementService = inject('outsourcingmanagementService', () => new OutsourcingmanagementService());

    const outsourcingmanagements: Ref<IOutsourcingmanagement[]> = ref([]);

    const resourcemanagementService = inject('resourcemanagementService', () => new ResourcemanagementService());

    const resourcemanagements: Ref<IResourcemanagement[]> = ref([]);

    const riskmanagementService = inject('riskmanagementService', () => new RiskmanagementService());

    const riskmanagements: Ref<IRiskmanagement[]> = ref([]);

    const securitymanagementService = inject('securitymanagementService', () => new SecuritymanagementService());

    const securitymanagements: Ref<ISecuritymanagement[]> = ref([]);

    const documentService = inject('documentService', () => new DocumentService());

    const documents: Ref<IDocument[]> = ref([]);

    const safetycheckService = inject('safetycheckService', () => new SafetycheckService());

    const safetychecks: Ref<ISafetycheck[]> = ref([]);

    const departmentService = inject('departmentService', () => new DepartmentService());

    const departments: Ref<IDepartment[]> = ref([]);

    const evaluationCriteriaService = inject('evaluationCriteriaService', () => new EvaluationCriteriaService());

    const evaluationCriteria: Ref<IEvaluationCriteria[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveProjectwbs = async projectwbsId => {
      try {
        const res = await projectwbsService().find(projectwbsId);
        projectwbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.projectwbsId) {
      retrieveProjectwbs(route.params.projectwbsId);
    }

    const initRelationships = () => {
      cycleplanService()
        .retrieve()
        .then(res => {
          cycleplans.value = res.data;
        });
      progressmanagementService()
        .retrieve()
        .then(res => {
          progressmanagements.value = res.data;
        });
      qualitymanagementService()
        .retrieve()
        .then(res => {
          qualitymanagements.value = res.data;
        });
      fundsmanagementService()
        .retrieve()
        .then(res => {
          fundsmanagements.value = res.data;
        });
      technicalmanagementService()
        .retrieve()
        .then(res => {
          technicalmanagements.value = res.data;
        });
      contractualfundsService()
        .retrieve()
        .then(res => {
          contractualfunds.value = res.data;
        });
      outsourcingmanagementService()
        .retrieve()
        .then(res => {
          outsourcingmanagements.value = res.data;
        });
      resourcemanagementService()
        .retrieve()
        .then(res => {
          resourcemanagements.value = res.data;
        });
      riskmanagementService()
        .retrieve()
        .then(res => {
          riskmanagements.value = res.data;
        });
      securitymanagementService()
        .retrieve()
        .then(res => {
          securitymanagements.value = res.data;
        });
      documentService()
        .retrieve()
        .then(res => {
          documents.value = res.data;
        });
      safetycheckService()
        .retrieve()
        .then(res => {
          safetychecks.value = res.data;
        });
      departmentService()
        .retrieve()
        .then(res => {
          departments.value = res.data;
        });
      evaluationCriteriaService()
        .retrieve()
        .then(res => {
          evaluationCriteria.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      projectwbsname: {},
      wbsspare1: {},
      wbsspare2: {},
      wbsspare3: {},
      wbsspare4: {},
      wbsspare5: {},
      cycleplan: {},
      progressmanagement: {},
      qualitymanagement: {},
      fundsmanagement: {},
      technicalmanagement: {},
      contractualfunds: {},
      outsourcingmanagement: {},
      resourcemanagement: {},
      riskmanagement: {},
      securitymanagement: {},
      document: {},
      safetycheck: {},
      department: {},
      evaluationCriteria: {},
    };
    const v$ = useVuelidate(validationRules, projectwbs as any);
    v$.value.$validate();

    return {
      projectwbsService,
      alertService,
      projectwbs,
      previousState,
      isSaving,
      currentLanguage,
      cycleplans,
      progressmanagements,
      qualitymanagements,
      fundsmanagements,
      technicalmanagements,
      contractualfunds,
      outsourcingmanagements,
      resourcemanagements,
      riskmanagements,
      securitymanagements,
      documents,
      safetychecks,
      departments,
      evaluationCriteria,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.projectwbs.id) {
        this.projectwbsService()
          .update(this.projectwbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.projectwbs.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.projectwbsService()
          .create(this.projectwbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.projectwbs.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
