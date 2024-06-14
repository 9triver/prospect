import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ProgressmanagementService from './progressmanagement.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import DepartmentService from '@/entities/department/department.service';
import { type IDepartment } from '@/shared/model/department.model';
import PlanreturnsService from '@/entities/planreturns/planreturns.service';
import { type IPlanreturns } from '@/shared/model/planreturns.model';
import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IProgressmanagement, Progressmanagement } from '@/shared/model/progressmanagement.model';
import { Progresstype } from '@/shared/model/enumerations/progresstype.model';
import { Progressstatus } from '@/shared/model/enumerations/progressstatus.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProgressmanagementUpdate',
  setup() {
    const progressmanagementService = inject('progressmanagementService', () => new ProgressmanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const progressmanagement: Ref<IProgressmanagement> = ref(new Progressmanagement());

    const departmentService = inject('departmentService', () => new DepartmentService());

    const departments: Ref<IDepartment[]> = ref([]);

    const planreturnsService = inject('planreturnsService', () => new PlanreturnsService());

    const planreturns: Ref<IPlanreturns[]> = ref([]);

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const progresstypeValues: Ref<string[]> = ref(Object.keys(Progresstype));
    const progressstatusValues: Ref<string[]> = ref(Object.keys(Progressstatus));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveProgressmanagement = async progressmanagementId => {
      try {
        const res = await progressmanagementService().find(progressmanagementId);
        progressmanagement.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.progressmanagementId) {
      retrieveProgressmanagement(route.params.progressmanagementId);
    }

    const initRelationships = () => {
      departmentService()
        .retrieve()
        .then(res => {
          departments.value = res.data;
        });
      planreturnsService()
        .retrieve()
        .then(res => {
          planreturns.value = res.data;
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
      progressid: {
        integer: validations.integer(t$('entity.validation.number').toString()),
      },
      progressname: {},
      progresstype: {},
      workfocus: {},
      createtime: {},
      creatorname: {},
      responsiblename: {},
      status: {},
      baselineid: {},
      auditStatus: {},
      department: {},
      planreturns: {},
      responsibleid: {},
      creatorid: {},
      auditorid: {},
    };
    const v$ = useVuelidate(validationRules, progressmanagement as any);
    v$.value.$validate();

    return {
      progressmanagementService,
      alertService,
      progressmanagement,
      previousState,
      progresstypeValues,
      progressstatusValues,
      auditStatusValues,
      isSaving,
      currentLanguage,
      departments,
      planreturns,
      officers,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.progressmanagement.id) {
        this.progressmanagementService()
          .update(this.progressmanagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster3App.progressmanagement.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.progressmanagementService()
          .create(this.progressmanagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster3App.progressmanagement.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
