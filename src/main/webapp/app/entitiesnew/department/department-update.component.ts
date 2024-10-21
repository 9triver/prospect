import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import DepartmentService from './department.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import ProjectpbsService from '@/entities/projectpbs/projectpbs.service';
import { type IProjectpbs } from '@/shared/model/projectpbs.model';
import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import WorkbagService from '@/entities/workbag/workbag.service';
import { type IWorkbag } from '@/shared/model/workbag.model';
import { type IDepartment, Department } from '@/shared/model/department.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'DepartmentUpdate',
  setup() {
    const departmentService = inject('departmentService', () => new DepartmentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const department: Ref<IDepartment> = ref(new Department());

    const departments: Ref<IDepartment[]> = ref([]);

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);

    const projectpbsService = inject('projectpbsService', () => new ProjectpbsService());

    const projectpbs: Ref<IProjectpbs[]> = ref([]);

    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());

    const projectwbs: Ref<IProjectwbs[]> = ref([]);

    const workbagService = inject('workbagService', () => new WorkbagService());

    const workbags: Ref<IWorkbag[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveDepartment = async departmentId => {
      try {
        const res = await departmentService().find(departmentId);
        department.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.departmentId) {
      retrieveDepartment(route.params.departmentId);
    }

    const initRelationships = () => {
      departmentService()
        .retrieve()
        .then(res => {
          departments.value = res.data;
        });
      officersService()
        .retrieve()
        .then(res => {
          officers.value = res.data;
        });
      projectpbsService()
        .retrieve()
        .then(res => {
          projectpbs.value = res.data;
        });
      projectwbsService()
        .retrieve()
        .then(res => {
          projectwbs.value = res.data;
        });
      workbagService()
        .retrieve()
        .then(res => {
          workbags.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      name: {},
      description: {},
      role: {},
      officersnum: {},
      superior: {},
      officers: {},
      pbs: {},
      wbs: {},
      workbags: {},
    };
    const v$ = useVuelidate(validationRules, department as any);
    v$.value.$validate();

    return {
      departmentService,
      alertService,
      department,
      previousState,
      isSaving,
      currentLanguage,
      departments,
      officers,
      projectpbs,
      projectwbs,
      workbags,
      v$,
      t$,
    };
  },
  created(): void {
    this.department.officers = [];
    this.department.pbs = [];
    this.department.wbs = [];
    this.department.workbags = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.department.id) {
        this.departmentService()
          .update(this.department)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.department.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.departmentService()
          .create(this.department)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.department.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },

    getSelected(selectedVals, option, pkField = 'id'): any {
      if (selectedVals) {
        return selectedVals.find(value => option[pkField] === value[pkField]) ?? option;
      }
      return option;
    },
  },
});
