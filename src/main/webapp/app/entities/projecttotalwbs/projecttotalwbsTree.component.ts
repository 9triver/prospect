import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ProjectTotalwbsService from './projecttotalwbs.service';
import { type IProjectTotalwbs } from '@/shared/model/projecttotalwbs.model';
import { useAlertService } from '@/shared/alert/alert.service';
//人员
import OfficersService from '../officers/officers.service';
//部门
import DepartmentService from '../department/department.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectTotalwbsTree',
  setup() {
    const { t: t$ } = useI18n();
    const projectTotalwbsService = inject('projectTotalwbsService', () => new ProjectTotalwbsService());
    const alertService = inject('alertService', () => useAlertService(), true);
    
    const officersService = inject('officersService', () => new OfficersService());
    const departmentService = inject('departmentService', () => new DepartmentService());

    const projecttotalwbs: Ref<IProjectTotalwbs[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveProjecttotalwbss = async () => {
      isFetching.value = true;
      try {
        const res = await projectTotalwbsService().retrieve();
        // 转换扁平数据为树状结构
        if(res.data){
          projecttotalwbs.value = res.data ;
          // 查询关联表补充细节
          const resData = details(projecttotalwbs.value);     
          //拼接树状结构
          const treeData = convertToTree(resData.value);
          projecttotalwbs.value = treeData;
        } else {
          // 如果响应没有数据，可以做出适当的处理
          console.error('Retrieve Projecttotalwbs: Response data is empty');
        }
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveProjecttotalwbss();
    };

    onMounted(async () => {
      await retrieveProjecttotalwbss();
    });



    const details = (flatData: IProjectTotalwbs[]) => {
      // 遍历项目列表，根据每个项目的 人员id 获取并赋值 人员name，WBS也赋值name
      projecttotalwbs.value.forEach(async project => {
        // alert("----查询关联表名称----")
        // 项目技术负责人——检查 project.responsibleperson.id 是否存在且不为空
        if (project.technicaldirector && project.technicaldirector.id) {
          try {
            const officersId = project.technicaldirector.id as number; // 使用as语法
            const res = await officersService().find(officersId);
            // alert(JSON.stringify(res));
            // alert(`名称是 ：${res.name}`);
            if (res && res.name) {
              project.technicaldirector.name = res.name;
            }
          } catch (error) {
            alert(`项目技术负责人获取name时异常: ${project.id}` + JSON.stringify(error));
            console.error(`Error fetching project details for id ${project.id}:`, error);
          }
        }
        // 项目审核人——检查 project.auditorid.id 是否存在且不为空
        if (project.auditorid && project.auditorid.id) {
          try {
            const officersId = project.auditorid.id as number; // 使用as语法
            const res = await officersService().find(officersId);
            // alert(`名称是 ：${res.name}`);
            if (res && res.name) {
              project.auditorid.name = res.name;
            }
          } catch (error) {
            alert(`项目审核人获取name时异常: ${project.id}` + JSON.stringify(error));
            console.error(`Error fetching project details for id ${project.id}:`, error);
          }
        }
        // 项目定密——检查 project.department.id 是否存在且不为空
        if (project.department && project.department.id) {
          try {
            const departmentId = project.department.id ; // 使用as语法
            const res = await departmentService().find(departmentId);
            //alert(JSON.stringify(res));
            // alert(`名称是 ：${res.departmentname}`);
            if (res && res.departmentname) {
              project.department.name = res.departmentname;
            }
          } catch (error) {
            alert(`部门获取name时异常: ${project.id}` + JSON.stringify(error));
            console.error(`Error fetching project details for id ${project.id}:`, error);
          }
        }
        // alert(JSON.stringify(projecttotalwbs.value));
      });
      return projecttotalwbs;
    }
    // 转换函数：将扁平数据转换为树状结构
    const convertToTree = (flatData: IProjectTotalwbs[]) => {
      // 使用一个Map来存储每个节点的引用
      const map = new Map<number, IProjectTotalwbs>();
      flatData.forEach(item => {
        map.set(item.id, { ...item, children: [] });
      });
      // 构建树形结构
      const tree: IProjectTotalwbs[] = [];
      flatData.forEach(item => {
        const node = map.get(item.id);
        if (item.parentwbsid && map.has(item.parentwbsid)) {
          const parent = map.get(item.parentwbsid);
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

    const handleNodeClick = (node: IProjectTotalwbs) => {
      console.log('Node clicked:', node);
      // 处理节点点击事件的逻辑
    };

    //条件查询
    const form = ref({
      id: '',
      wbsname: '',
      parentwbsid: '',
      pbsid: '',
	    description: '',
      belongfront: '',
      starttime: '',
      endtime: '',
      progress: '',
    	type: '',
    	priorty: '',
    	secretlevel: '',
    	deliverables: '',
    	status: '',
    	auditStatus: ''
    })   
    const onSubmit = async () => {
      isFetching.value = true;
      try {
        //整数调整
        form.value.type = parseInt(form.value.type as string, 10);
        form.value.progress = parseInt(form.value.progress as string, 10);
        form.value.priorty = parseInt(form.value.priorty as string, 10);
        // const res = await projectTotalwbsService().query(form.value);
        const res = await projectTotalwbsService().retrieve();
        projecttotalwbs.value = res.data;
        alert(JSON.stringify(projecttotalwbs.value))
        // 查询关联表补充细节
        const resData = details(projecttotalwbs.value);          
        //转换扁平数据为树状结构，拼接树状结构
        const treeData = convertToTree(resData.value);
        projecttotalwbs.value = treeData;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    return {
      projecttotalwbs,
      handleSyncList,
      isFetching,
      retrieveProjecttotalwbss,
      clear,
      t$,
      handleNodeClick,
      onSubmit,
      form
    };
  },
});
