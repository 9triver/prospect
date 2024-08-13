import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ProgressPlanService from './progress-plan.service';
import { type IProgressPlan } from '@/shared/model/progress-plan.model';
import { useAlertService } from '@/shared/alert/alert.service';
//人员
import OfficersService from '../officers/officers.service';
//部门
import DepartmentService from '../department/department.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProgressPlan',
  setup() {
    const { t: t$ } = useI18n();
    const progressPlanService = inject('progressPlanService', () => new ProgressPlanService());
    const alertService = inject('alertService', () => useAlertService(), true);
    
    const officersService = inject('officersService', () => new OfficersService());
    const departmentService = inject('departmentService', () => new DepartmentService());

    const progressPlans: Ref<IProgressPlan[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveProgressPlans = async () => {
      isFetching.value = true;
      try {
        const res = await progressPlanService().retrieve();
        // alert(JSON.stringify(res));
        if(res.data){
          progressPlans.value = res.data ;
          // 查询关联表补充细节
          const resData = details(progressPlans.value);     
          //转换扁平数据为树状结构，拼接树状结构
          const treeData = convertToTree(resData.value);
          progressPlans.value = treeData;
        } else {
          // 如果响应没有数据，可以做出适当的处理
          console.error('Retrieve Projectpbs: Response data is empty');
        }
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveProgressPlans();
    };

    onMounted(async () => {
      await retrieveProgressPlans();
    });


    const details = (flatData: IProgressPlan[]) => {
      // 遍历项目列表，根据每个项目的 人员id 获取并赋值 人员name，WBS也赋值name
      progressPlans.value.forEach(async progressPlan => {
        // alert("----查询关联表名称----")
        // 计划负责人——检查 progressPlan.responsibleperson.id 是否存在且不为空
        if (progressPlan.responsibleperson && progressPlan.responsibleperson.id) {
          try {
            const officersId = progressPlan.responsibleperson.id as number; // 使用as语法
            const res = await officersService().find(officersId);
            // alert(JSON.stringify(res));
            // alert(`名称是 ：${res.name}`);
            if (res && res.name) {
              progressPlan.responsibleperson.name = res.name;
            }
          } catch (error) {
            alert(`项目技术负责人获取name时异常: ${progressPlan.id}` + JSON.stringify(error));
            console.error(`Error fetching progressPlan details for id ${progressPlan.id}:`, error);
          }
        }
        // 协作人——检查 progressPlan.cooperatingperson.id 是否存在且不为空
        if (progressPlan.cooperatingperson && progressPlan.cooperatingperson.id) {
          try {
            const officersId = progressPlan.cooperatingperson.id as number; // 使用as语法
            const res = await officersService().find(officersId);
            // alert(JSON.stringify(res));
            // alert(`名称是 ：${res.name}`);
            if (res && res.name) {
              progressPlan.cooperatingperson.name = res.name;
            }
          } catch (error) {
            alert(`项目技术负责人获取name时异常: ${progressPlan.id}` + JSON.stringify(error));
            console.error(`Error fetching progressPlan details for id ${progressPlan.id}:`, error);
          }
        }
        // 项目审核人——检查 progressPlan.auditorid.id 是否存在且不为空
        if (progressPlan.auditorid && progressPlan.auditorid.id) {
          try {
            const officersId = progressPlan.auditorid.id as number; // 使用as语法
            const res = await officersService().find(officersId);
            // alert(`名称是 ：${res.name}`);
            if (res && res.name) {
              progressPlan.auditorid.name = res.name;
            }
          } catch (error) {
            alert(`项目审核人获取name时异常: ${progressPlan.id}` + JSON.stringify(error));
            console.error(`Error fetching progressPlan details for id ${progressPlan.id}:`, error);
          }
        }
        // 负责部门——检查 progressPlan.responsibledepartment.id 是否存在且不为空
        if (progressPlan.responsibledepartment && progressPlan.responsibledepartment.id) {
          try {
            const departmentId = progressPlan.responsibledepartment.id;
            const res = await departmentService().find(departmentId);
            //alert(JSON.stringify(res));
            // alert(`名称是 ：${res.departmentname}`);
            if (res && res.name) {
              progressPlan.responsibledepartment.name = res.name;
            }
          } catch (error) {
            alert(`部门获取name时异常: ${progressPlan.id}` + JSON.stringify(error));
            console.error(`Error fetching progressPlan details for id ${progressPlan.id}:`, error);
          }
        }
        // 协作部门——检查 progressPlan.cooperatingdepartment.id 是否存在且不为空
        if (progressPlan.cooperatingdepartment && progressPlan.cooperatingdepartment.id) {
          try {
            const departmentId = progressPlan.cooperatingdepartment.id;
            const res = await departmentService().find(departmentId);
            //alert(JSON.stringify(res));
            // alert(`名称是 ：${res.departmentname}`);
            if (res && res.name) {
              progressPlan.cooperatingdepartment.name = res.name;
            }
          } catch (error) {
            alert(`部门获取name时异常: ${progressPlan.id}` + JSON.stringify(error));
            console.error(`Error fetching progressPlan details for id ${progressPlan.id}:`, error);
          }
        }
        // alert(JSON.stringify(progressPlanpbs.value));
      });
      return progressPlans;
    }
    // 转换函数：将扁平数据转换为树状结构
    const convertToTree = (flatData: IProgressPlan[]) => {
      // 使用一个Map来存储每个节点的引用
      const map = new Map<String, IProgressPlan>();
      flatData.forEach(item => {
        map.set(item.id, { ...item, children: [] });
      });

      // 构建树形结构
      const tree: IProgressPlan[] = [];
      flatData.forEach(item => {
        const node = map.get(item.id);
        if (item.belongplanid && map.has(item.belongplanid)) {
          const parent = map.get(item.belongplanid);
          if (parent) {
            parent.children.push(node!);
          } else {
            tree.push(node!); // 如果没有找到父节点，将其作为顶层节点
          }
        } else {
          tree.push(node!); // 没有父节点的节点作为顶层节点
        }
      });

      return tree;
    };


    //条件查询
    const form = ref({
      id: '',
      planname: '',
      progress: '',
      secretlevel: '',
      plantype: ''
    })   
    const onSubmit = async () => {
      // isFetching.value = true;
      try {
        //整数调整 
        const res = await progressPlanService().query(form.value);
        progressPlans.value = res.data;
        alert(JSON.stringify(progressPlans.value))
        // 查询关联表补充细节
        const resData = details(progressPlans.value);          
        //转换扁平数据为树状结构，拼接树状结构
        const treeData = convertToTree(resData.value);
        progressPlans.value = treeData;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        // isFetching.value = false;
      }
    };


    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IProgressPlan) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeProgressPlan = async () => {
      try {
        await progressPlanService().delete(removeId.value);
        const message = t$('jy1App.progressPlan.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveProgressPlans();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      progressPlans,
      handleSyncList,
      isFetching,
      retrieveProgressPlans,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeProgressPlan,
      t$,
      form,
      onSubmit,
    };
  },
});
