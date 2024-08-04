import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';
import { ElMessageBox } from 'element-plus';

import ProjectpbsService from './projectpbs.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import DepartmentService from '@/entities/department/department.service';
import { type IDepartment } from '@/shared/model/department.model';
import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import ProjectService from '@/entities/project/project.service';
import { type IProject } from '@/shared/model/project.model';
import { type IProjectpbs, Projectpbs } from '@/shared/model/projectpbs.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { ProjectStatus } from '@/shared/model/enumerations/project-status.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';
import MyContentComponent from '@/entities/projectwbs/projectwbsSelect.vue'; 
import { Search } from '@element-plus/icons-vue'

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectpbsUpdate',
  components: {
    MyContentComponent
  },
  setup() {
    const projectpbsService = inject('projectpbsService', () => new ProjectpbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const projectpbs: Ref<IProjectpbs> = ref(new Projectpbs());

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);

    const departmentService = inject('departmentService', () => new DepartmentService());

    const departments: Ref<IDepartment[]> = ref([]);

    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());

    const projectwbs: Ref<IProjectwbs[]> = ref([]);

    const projectService = inject('projectService', () => new ProjectService());

    const projects: Ref<IProject[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const projectStatusValues: Ref<string[]> = ref(Object.keys(ProjectStatus));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveProjectpbs = async projectpbsId => {
      try {
        const res = await projectpbsService().find(projectpbsId);
        // 解析后端返回的日期数据为 JavaScript Date 对象
        res.starttime = new Date(res.starttime);
        res.endtime = new Date(res.endtime);
        projectpbs.value = res;
        alert(JSON.stringify(projectpbs.value));
        timeRange.value = [
          projectpbs.value.starttime,projectpbs.value.endtime
        ]
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.projectpbsId) {
      retrieveProjectpbs(route.params.projectpbsId);
    }

    if (route.params?.parentId) {
      projectpbs.value.parentpbsid = route.params.parentId;
      projectpbs.value.parentid = route.params.parentId;
    }

    const initRelationships = () => {
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
      projectwbsService()
        .retrieve()
        .then(res => {
          projectwbs.value = res.data;
        });
      projectService()
        .retrieve()
        .then(res => {
          projects.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      pbsname: {},
      parentpbsid: {},
      secretlevel: {},
      starttime: {},
      endtime: {},
      productlevel: {},
      ifkey: {},
      ifimporttant: {},
      description: {},
      progress: {},
      type: {},
      priorty: {},
      status: {},
      auditStatus: {},
      technicaldirector: {},
      administrativedirector: {},
      knowingpeople: {},
      auditorid: {},
      responsibledepartment: {},
      relevantdepartment: {},
      projectwbs: {},
      projects: {},
    };
    const v$ = useVuelidate(validationRules, projectpbs as any);
    v$.value.$validate();

    const timeRange = ref()
  
    //弹出框
    const dialogVisible = ref(false);

    const handleClose = (done: () => void) => {
      ElMessageBox.confirm('确定要关闭吗？')
        .then(() => {
          done()
        })
        .catch(() => {
          // catch error
        })
    };

    const selectedWbsId = ref(''); // 或者可以使用数组来存储多个选择项
    const handleWbsSelection = (wbsId: string) => {
      selectedWbsId.value = wbsId;
      dialogVisible.value = false; // 关闭弹出框
    };

    return {
      projectpbsService,
      alertService,
      projectpbs,
      previousState,
      secretlevelValues,
      projectStatusValues,
      auditStatusValues,
      isSaving,
      currentLanguage,
      officers,
      departments,
      projectwbs,
      projects,
      v$,
      t$,
      dialogVisible,
      selectedWbsId,
      handleWbsSelection,
      handleClose,
      Search,
      timeRange
    };
  },
  created(): void {
    this.projectpbs.projectwbs = [];
    this.projectpbs.projects = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if(this.projectpbs.parentid){
        alert(JSON.stringify(this.projectpbs));
        this.projectpbsService()
          .create(this.projectpbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.projectpbs.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }else if (this.projectpbs.id) {
        this.projectpbsService()
          .update(this.projectpbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.projectpbs.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.projectpbsService()
          .create(this.projectpbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.projectpbs.created', { param: param.id }).toString());
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
  watch:{
    timeRange(newValue){
      this.projectpbs.starttime = newValue[0]
      this.projectpbs.endtime = newValue[1]
    }
  }
});
