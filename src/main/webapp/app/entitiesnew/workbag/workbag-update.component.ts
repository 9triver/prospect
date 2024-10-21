
import { computed, defineComponent, inject, ref, type Ref, watch } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import WorkbagService from './workbag.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import HrManagementService from '@/entitiesnew/hr-management/hr-management.service';
import { type IHrManagement } from '@/shared/model/hr-management.model';
import DepartmentService from '@/entitiesnew/department/department.service';
import { type IDepartment } from '@/shared/model/department.model';
import ProjectdeliverablesService from '@/entitiesnew/projectdeliverables/projectdeliverables.service';
import { type IProjectdeliverables } from '@/shared/model/projectdeliverables.model';
import ProjectwbsService from '@/entitiesnew/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import WorkService from '@/entitiesnew/work/work.service';
import { type IWork } from '@/shared/model/work.model';
import { type IWorkbag, Workbag } from '@/shared/model/workbag.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';
import { ElTreeSelect } from 'element-plus';
import type { symlink } from 'fs';

export default defineComponent({
  compatConfig: { MODE: 3 },
  components: { ElTreeSelect },
  name: 'WorkbagUpdate',
  setup() {
    const workbagService = inject('workbagService', () => new WorkbagService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const workbag: Ref<IWorkbag> = ref(new Workbag());

    const hrManagementService = inject('hrManagementService', () => new HrManagementService());

    const hrManagements: Ref<IHrManagement[]> = ref([]);

    const departmentService = inject('departmentService', () => new DepartmentService());

    const departments: Ref<IDepartment[]> = ref([]);

    const projectdeliverablesService = inject('projectdeliverablesService', () => new ProjectdeliverablesService());

    const projectdeliverables: Ref<IProjectdeliverables[]> = ref([]);

    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());

    const projectwbs: Ref<IProjectwbs[]> = ref([]);
    const projectwbsTree: Ref<IProjectwbs[]> = ref([]);

    const workService = inject('workService', () => new WorkService());

    const works: Ref<IWork[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveWorkbag = async workbagId => {
      try {
        const res = await workbagService().find(workbagId);
        workbag.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.workbagId) {
      retrieveWorkbag(route.params.workbagId);
    }

    const initRelationships = () => {
      hrManagementService()
        .retrieve()
        .then(res => {
          hrManagements.value = res.data;
        });
      departmentService()
        .retrieve()
        .then(res => {
          departments.value = res.data;
        });
      projectdeliverablesService()
        .retrieve()
        .then(res => {
          projectdeliverables.value = res.data;
        });
      projectwbsService()
        .retrieve()
        .then(res => {
          projectwbs.value = res.data;
        });
      projectwbsService()
        .retrieveTree()
        .then(res => {
          projectwbsTree.value = res.data;
        });
      workService()
        .retrieve()
        .then(res => {
          works.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      name: {},
      pbsid: {},
      workbagtype: {},
      supplier: {},
      iskeyimportant: {},
      keypbsname: {},
      importantpbsname: {},
      secretlevel: {},
      description: {},
      starttime: {},
      endtime: {},
      estimatedpurchasingtime: {},
      progress: {},
      issafetywork: {},
      remark: {},
      auditStatus: {},
      responsibleperson: {},
      projectmanager: {},
      knowingpeople: {},
      auditorid: {},
      responsibledepartment: {},
      department: {},
      projectdeliverables: {},
      relevantdepartments: {},
      wbsids: {},
      works: {},
    };
    const v$ = useVuelidate(validationRules, workbag as any);
    v$.value.$validate();

    
    //wbs选择框
    const treeProps = {
      children: 'children',
      label: 'wbsname',
      key: 'id',
    };
    // 存储选中的 ID
    const selectedWbsIds = ref([]); 
    // 存储选中的整个实体数据
    const selectedWbsEntities = ref([]); 

    const handleChange = (value) => {
      const wbsids = selectedWbsIds.value;
      console.log("wbsids::",wbsids)
      // 清空当前选中的实体数据
      selectedWbsEntities.value = [];
      
      console.log("全部的wbs数据为projectwbs:",JSON.stringify(projectwbs.value));
      if(projectwbs.value === undefined || projectwbs.value === null || projectwbs.value.length === 0){
      }
      
      // 迭代选中的 ID，找到对应的实体
      wbsids.forEach(id => {
        const entity = projectwbs.value.find(item => item.id === id);
        if (entity) {
          selectedWbsEntities.value.push(entity); // 将整个实体添加到选中数据中
        }
      });
      workbag.value.wbsids = selectedWbsEntities.value;
      console.log("选中的WBS实体：", JSON.stringify(workbag.value.wbsids));
    };


    //接收wbs组合创建的参数
    if (route.params?.wbs) {
      console.log("!!!!!!!!!!!路由带入的wbs!!!!!!!!!!!!!!!!",route.params.wbs);
      const routewbs = route.params.wbs;
      
      try {
        // 将 JSON 字符串转换为对象
        const parsedData = JSON.parse(routewbs);
        // 存储 projectwbsid 的字符串
        const wbsids = ref<string>('');
        // 将对象转换为 Map
        const projectWbsMap = ref(new Map());
        // 将对象转换为 Map 并构建 wbsids 字符串
        for (const item of parsedData) {
          projectWbsMap.value.set(item.id, item); // 使用任务 ID 作为键
          wbsids.value += item.id + ','; // 添加到字符串中
        }
        // 移除最后一个逗号
        if (wbsids.value.endsWith(',')) {
          wbsids.value = wbsids.value.slice(0, -1);
        }
        console.log("wbsids:", wbsids.value.split(','));

        selectedWbsIds.value = wbsids.value.split(',');
        console.log("selectedWbsIds:", selectedWbsIds.value);

        watch(projectwbs, (newVal) => {
          if (newVal.length > 0 && selectedWbsIds.value.length > 0) {
            handleChange(selectedWbsIds.value);
          }
        });
        // handleChange(selectedWbsIds.value);
      } catch (error) {
        console.error("解析 JSON 数据失败:", error);
      }
    }



    return {
      workbagService,
      alertService,
      workbag,
      previousState,
      secretlevelValues,
      auditStatusValues,
      isSaving,
      currentLanguage,
      hrManagements,
      departments,
      projectdeliverables,
      projectwbsTree,
      projectwbs,
      works,
      v$,
      t$,
      treeProps,
      selectedWbsIds,
      handleChange,
    };
  },
  created(): void {
    this.workbag.projectdeliverables = [];
    this.workbag.relevantdepartments = [];
    // this.workbag.wbsids = [];
    this.workbag.works = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.workbag.id) {
        this.workbagService()
          .update(this.workbag)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.workbag.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        alert('save');
        console.log("保存的数据：", this.workbag.value); // 确保查看 workbag 的值
        if (this.workbag.wbsids === 0) {
          alert("未选择任何WBS");
          console.error("未选择任何WBS");
        } else {
          this.workbagService()
            .create(this.workbag)
            .then(param => {
              this.isSaving = false;
              this.previousState();
              this.alertService.showSuccess(this.t$('jy1App.workbag.created', { param: param.id }).toString());
            })
            .catch(error => {
              this.isSaving = false;
              this.alertService.showHttpError(error.response);
            });
        }
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
