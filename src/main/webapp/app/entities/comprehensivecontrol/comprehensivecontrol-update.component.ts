import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ComprehensivecontrolService from './comprehensivecontrol.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProgressplanService from '@/entities/progressplan/progressplan.service';
import { type IProgressplan } from '@/shared/model/progressplan.model';
import ProjectService from '@/entities/project/project.service';
import { type IProject } from '@/shared/model/project.model';
import FundsmanagementService from '@/entities/fundsmanagement/fundsmanagement.service';
import { type IFundsmanagement } from '@/shared/model/fundsmanagement.model';
import TotalbudgetService from '@/entities/totalbudget/totalbudget.service';
import { type ITotalbudget } from '@/shared/model/totalbudget.model';
import UnitbudgetService from '@/entities/unitbudget/unitbudget.service';
import { type IUnitbudget } from '@/shared/model/unitbudget.model';
import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import DepartmentService from '@/entities/department/department.service';
import { type IDepartment } from '@/shared/model/department.model';
import { type IComprehensivecontrol, Comprehensivecontrol } from '@/shared/model/comprehensivecontrol.model';
import { ProjectStatus } from '@/shared/model/enumerations/project-status.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ComprehensivecontrolUpdate',
  setup() {
    const comprehensivecontrolService = inject('comprehensivecontrolService', () => new ComprehensivecontrolService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const comprehensivecontrol: Ref<IComprehensivecontrol> = ref(new Comprehensivecontrol());

    const progressplanService = inject('progressplanService', () => new ProgressplanService());

    const progressplans: Ref<IProgressplan[]> = ref([]);

    const projectService = inject('projectService', () => new ProjectService());

    const projects: Ref<IProject[]> = ref([]);

    const fundsmanagementService = inject('fundsmanagementService', () => new FundsmanagementService());

    const fundsmanagements: Ref<IFundsmanagement[]> = ref([]);

    const totalbudgetService = inject('totalbudgetService', () => new TotalbudgetService());

    const totalbudgets: Ref<ITotalbudget[]> = ref([]);

    const unitbudgetService = inject('unitbudgetService', () => new UnitbudgetService());

    const unitbudgets: Ref<IUnitbudget[]> = ref([]);

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);

    const departmentService = inject('departmentService', () => new DepartmentService());

    const departments: Ref<IDepartment[]> = ref([]);
    const projectStatusValues: Ref<string[]> = ref(Object.keys(ProjectStatus));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveComprehensivecontrol = async comprehensivecontrolId => {
      try {
        const res = await comprehensivecontrolService().find(comprehensivecontrolId);
        comprehensivecontrol.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.comprehensivecontrolId) {
      retrieveComprehensivecontrol(route.params.comprehensivecontrolId);
    }

    const initRelationships = () => {
      progressplanService()
        .retrieve()
        .then(res => {
          progressplans.value = res.data;
        });
      projectService()
        .retrieve()
        .then(res => {
          projects.value = res.data;
        });
      fundsmanagementService()
        .retrieve()
        .then(res => {
          fundsmanagements.value = res.data;
        });
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
      officersService()
        .retrieve()
        .then(res => {
          officers.value = res.data;
        });
      departmentService()
        .retrieve()
        .then(res => {
          departments.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      description: {},
      number: {},
      type: {},
      starttime: {},
      endtime: {},
      actualstarttime: {},
      actualendtime: {},
      result: {},
      acceptancetype: {},
      status: {},
      auditStatus: {},
      responsiblename: {},
      progress: {},
      project: {},
      funds: {},
      totalbudget: {},
      unitbudget: {},
      responsibleid: {},
      auditorid: {},
      decument: {},
      coordinationdepartment: {},
    };
    const v$ = useVuelidate(validationRules, comprehensivecontrol as any);
    v$.value.$validate();

    return {
      comprehensivecontrolService,
      alertService,
      comprehensivecontrol,
      previousState,
      projectStatusValues,
      auditStatusValues,
      isSaving,
      currentLanguage,
      progressplans,
      projects,
      fundsmanagements,
      totalbudgets,
      unitbudgets,
      officers,
      departments,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.comprehensivecontrol.id) {
        this.comprehensivecontrolService()
          .update(this.comprehensivecontrol)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.comprehensivecontrol.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.comprehensivecontrolService()
          .create(this.comprehensivecontrol)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.comprehensivecontrol.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
