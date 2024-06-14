import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ProjectService from './project.service';
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
import TechnicalConditionService from '@/entities/technical-condition/technical-condition.service';
import { type ITechnicalCondition } from '@/shared/model/technical-condition.model';
import ContractualfundsService from '@/entities/contractualfunds/contractualfunds.service';
import { type IContractualfunds } from '@/shared/model/contractualfunds.model';
import OutsourcingmPurchaseExecuteService from '@/entities/outsourcingm-purchase-execute/outsourcingm-purchase-execute.service';
import { type IOutsourcingmPurchaseExecute } from '@/shared/model/outsourcingm-purchase-execute.model';
import ResourcemanagementService from '@/entities/resourcemanagement/resourcemanagement.service';
import { type IResourcemanagement } from '@/shared/model/resourcemanagement.model';
import RiskmanagementService from '@/entities/riskmanagement/riskmanagement.service';
import { type IRiskmanagement } from '@/shared/model/riskmanagement.model';
import DocumentService from '@/entities/document/document.service';
import { type IDocument } from '@/shared/model/document.model';
import SafetycheckService from '@/entities/safetycheck/safetycheck.service';
import { type ISafetycheck } from '@/shared/model/safetycheck.model';
import DepartmentService from '@/entities/department/department.service';
import { type IDepartment } from '@/shared/model/department.model';
import EvaluationCriteriaService from '@/entities/evaluation-criteria/evaluation-criteria.service';
import { type IEvaluationCriteria } from '@/shared/model/evaluation-criteria.model';
import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import ProjectSecrecyService from '@/entities/project-secrecy/project-secrecy.service';
import { type IProjectSecrecy } from '@/shared/model/project-secrecy.model';
import { type IProject, Project } from '@/shared/model/project.model';
import { ProjectStatus } from '@/shared/model/enumerations/project-status.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectUpdate',
  setup() {
    const projectService = inject('projectService', () => new ProjectService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const project: Ref<IProject> = ref(new Project());

    const cycleplanService = inject('cycleplanService', () => new CycleplanService());

    const cycleplans: Ref<ICycleplan[]> = ref([]);

    const progressmanagementService = inject('progressmanagementService', () => new ProgressmanagementService());

    const progressmanagements: Ref<IProgressmanagement[]> = ref([]);

    const qualitymanagementService = inject('qualitymanagementService', () => new QualitymanagementService());

    const qualitymanagements: Ref<IQualitymanagement[]> = ref([]);

    const fundsmanagementService = inject('fundsmanagementService', () => new FundsmanagementService());

    const fundsmanagements: Ref<IFundsmanagement[]> = ref([]);

    const technicalConditionService = inject('technicalConditionService', () => new TechnicalConditionService());

    const technicalConditions: Ref<ITechnicalCondition[]> = ref([]);

    const contractualfundsService = inject('contractualfundsService', () => new ContractualfundsService());

    const contractualfunds: Ref<IContractualfunds[]> = ref([]);

    const outsourcingmPurchaseExecuteService = inject('outsourcingmPurchaseExecuteService', () => new OutsourcingmPurchaseExecuteService());

    const outsourcingmPurchaseExecutes: Ref<IOutsourcingmPurchaseExecute[]> = ref([]);

    const resourcemanagementService = inject('resourcemanagementService', () => new ResourcemanagementService());

    const resourcemanagements: Ref<IResourcemanagement[]> = ref([]);

    const riskmanagementService = inject('riskmanagementService', () => new RiskmanagementService());

    const riskmanagements: Ref<IRiskmanagement[]> = ref([]);

    const documentService = inject('documentService', () => new DocumentService());

    const documents: Ref<IDocument[]> = ref([]);

    const safetycheckService = inject('safetycheckService', () => new SafetycheckService());

    const safetychecks: Ref<ISafetycheck[]> = ref([]);

    const departmentService = inject('departmentService', () => new DepartmentService());

    const departments: Ref<IDepartment[]> = ref([]);

    const evaluationCriteriaService = inject('evaluationCriteriaService', () => new EvaluationCriteriaService());

    const evaluationCriteria: Ref<IEvaluationCriteria[]> = ref([]);

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);

    const projectSecrecyService = inject('projectSecrecyService', () => new ProjectSecrecyService());

    const projectSecrecies: Ref<IProjectSecrecy[]> = ref([]);
    const projectStatusValues: Ref<string[]> = ref(Object.keys(ProjectStatus));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveProject = async projectId => {
      try {
        const res = await projectService().find(projectId);
        project.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.projectId) {
      retrieveProject(route.params.projectId);
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
      technicalConditionService()
        .retrieve()
        .then(res => {
          technicalConditions.value = res.data;
        });
      contractualfundsService()
        .retrieve()
        .then(res => {
          contractualfunds.value = res.data;
        });
      outsourcingmPurchaseExecuteService()
        .retrieve()
        .then(res => {
          outsourcingmPurchaseExecutes.value = res.data;
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
      officersService()
        .retrieve()
        .then(res => {
          officers.value = res.data;
        });
      projectSecrecyService()
        .retrieve()
        .then(res => {
          projectSecrecies.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      projectid: {
        integer: validations.integer(t$('entity.validation.number').toString()),
      },
      projectname: {},
      description: {},
      number: {},
      projecttype: {},
      responsiblename: {},
      duedate: {},
      priorty: {},
      progressid: {},
      returnsid: {},
      qualityid: {},
      fundsid: {},
      status: {},
      auditStatus: {},
      cycleplan: {},
      progressmanagement: {},
      qualitymanagement: {},
      fundsmanagement: {},
      technicalCondition: {},
      contractualfunds: {},
      outsourcingmPurchaseExecute: {},
      resourcemanagement: {},
      riskmanagement: {},
      document: {},
      safetycheck: {},
      department: {},
      evaluationCriteria: {},
      responsibleid: {},
      auditorid: {},
      projectSecrecy: {},
    };
    const v$ = useVuelidate(validationRules, project as any);
    v$.value.$validate();

    return {
      projectService,
      alertService,
      project,
      previousState,
      projectStatusValues,
      auditStatusValues,
      isSaving,
      currentLanguage,
      cycleplans,
      progressmanagements,
      qualitymanagements,
      fundsmanagements,
      technicalConditions,
      contractualfunds,
      outsourcingmPurchaseExecutes,
      resourcemanagements,
      riskmanagements,
      documents,
      safetychecks,
      departments,
      evaluationCriteria,
      officers,
      projectSecrecies,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.project.id) {
        this.projectService()
          .update(this.project)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster3App.project.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.projectService()
          .create(this.project)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster3App.project.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
