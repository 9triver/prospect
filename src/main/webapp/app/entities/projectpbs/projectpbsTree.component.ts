import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { Check,  Delete,  Edit,  Message,  Search,  Star,} from '@element-plus/icons-vue'

import ProjectpbsService from './projectpbs.service';
import { type IProjectpbs } from '@/shared/model/projectpbs.model';
import { useAlertService } from '@/shared/alert/alert.service';
//人员
import OfficersService from '../officers/officers.service';
//部门
import DepartmentService from '../department/department.service';
import type { FormInstance } from 'element-plus'
export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Projectpbs',
  setup() {
    const { t: t$ } = useI18n();
    const projectpbsService = inject('projectpbsService', () => new ProjectpbsService());
    const alertService = inject('alertService', () => useAlertService(), true);
    
    const officersService = inject('officersService', () => new OfficersService());
    const departmentService = inject('departmentService', () => new DepartmentService());

    const projectpbs: Ref<IProjectpbs[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveProjectpbss = async () => {
      isFetching.value = true;
      try {
        const res = await projectpbsService().retrieve();
        // alert(JSON.stringify(res));
        if(res.data){
          projectpbs.value = res.data ;
          // 查询关联表补充细节
          const resData = details(projectpbs.value);     
          //转换扁平数据为树状结构，拼接树状结构
          const treeData = convertToTree(resData.value);
          projectpbs.value = treeData;

        } else {
          // 如果响应没有数据，可以做出适当的处理
          console.error('Retrieve Projectpbs: Response data is empty');
        }
      } catch (err) {
        // 捕获和处理 HTTP 请求错误
        console.error('Error retrieving Projectpbs:', err);
        alertService.showHttpError(err.response);
      } finally {
        setTimeout(()=>{
          isFetching.value = false;
        },1000)
        
        
      }
    };

    const queryFormRef = ref<FormInstance>()

    const handleSyncList = (formRef:FormInstance|undefined) => {
      // 清空字段
      formRef?.resetFields()
      retrieveProjectpbss();
    };

    onMounted(async () => {
      await retrieveProjectpbss();
    });

    const details = (flatData: IProjectpbs[]) => {
      // 遍历项目列表，根据每个项目的 人员id 获取并赋值 人员name，WBS也赋值name
      projectpbs.value.forEach(async project => {
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
            const departmentId = project.department.id as number; // 使用as语法
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
        // alert(JSON.stringify(projectpbs.value));
      });
      return projectpbs;
    }
    // 转换函数：将扁平数据转换为树状结构
    const convertToTree = (flatData: IProjectpbs[]) => {
      // 使用一个Map来存储每个节点的引用
      const map = new Map<number, IProjectpbs>();
      flatData.forEach(item => {
        map.set(item.id, { ...item, children: [] });
      });

      // 构建树形结构
      const tree: IProjectpbs[] = [];
      flatData.forEach(item => {
        const node = map.get(item.id);
        if (item.parentpbsid && map.has(item.parentpbsid)) {
          const parent = map.get(item.parentpbsid);
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
      pbsname: '',
      parentpbsid: '',
      secretlevel: '',
      starttime: '',
      endtime: '',
      productlevel:'',
      ifkey:'',
      ifimporttant:'',
      description:'',
      progress:'',
      type:'',
      priorty:'',
      status:'',
      auditStatus:'',
      technicaldirectorname:''
    })   
    const onSubmit = async () => {
      isFetching.value = true;
      try {
        //整数调整
        form.value.productlevel = parseInt(form.value.productlevel as string, 10);
        form.value.ifkey = parseInt(form.value.ifkey as string, 10);
        form.value.ifimporttant = parseInt(form.value.ifimporttant as string, 10);
        form.value.type = parseInt(form.value.type as string, 10);
        form.value.progress = parseInt(form.value.progress as string, 10);
        form.value.priorty = parseInt(form.value.priorty as string, 10);
        const res = await projectpbsService().query(form.value);
        projectpbs.value = res.data;
        alert(JSON.stringify(projectpbs.value))
        // 查询关联表补充细节
        const resData = details(projectpbs.value);          
        //转换扁平数据为树状结构，拼接树状结构
        const treeData = convertToTree(resData.value);
        projectpbs.value = treeData;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };


    

    return {
      projectpbs,
      handleSyncList,
      isFetching,
      retrieveProjectpbss,
      clear,
      t$,
      form,
      onSubmit,
      Edit,
      queryFormRef
    };
  },
});