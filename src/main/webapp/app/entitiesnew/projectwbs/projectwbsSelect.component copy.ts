
import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useRouter } from 'vue-router';
import { useI18n } from 'vue-i18n';

import ProjectwbsService from './projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { useAlertService } from '@/shared/alert/alert.service';
import { ElTable } from 'element-plus';
// import { json } from 'stream/consumers';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectwbsSelect',
  setup() {
    const { t: t$ } = useI18n();
    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());
    const alertService = inject('alertService', () => useAlertService(), true);
    const router = useRouter();
    const projectwbs: Ref<IProjectwbs[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveProjectwbss = async () => {
      isFetching.value = true;
      try {
        const res = await projectwbsService().retrieve();
        // 转换扁平数据为树状结构
        if(res.data){
          projectwbs.value = res.data ;
          // 遍历项目列表，根据每个项目的 人员id 获取并赋值 人员name，WBS也赋值name
          projectwbs.value.forEach(async project => {
            // alert("----查询关联表名称----")
            // 项目负责人——检查 project.responsibleid.id 是否存在且不为空
            if (project.responsibleid && project.responsibleid.id) {
              try {
                const officersId = project.responsibleid.id as number; // 使用as语法
                const res = await officersService().find(officersId);
                //alert(JSON.stringify(res));
                // alert(`名称是 ：${res.officersname}`);
                if (res && res.officersname) {
                  project.responsibleid.name = res.officersname;
                }
              } catch (error) {
                // alert(`项目负责人获取name时异常: ${project.id}` + JSON.stringify(error));
                console.error(`Error fetching project details for id ${project.id}:`, error);
              }
            }
            // 项目审核人——检查 project.auditorid.id 是否存在且不为空
            if (project.auditorid && project.auditorid.id) {
              try {
                const officersId = project.auditorid.id as number; // 使用as语法
                const res = await officersService().find(officersId);
                // alert(`名称是 ：${res.officersname}`);
                if (res && res.officersname) {
                  project.auditorid.name = res.officersname;
                }
              } catch (error) {
                // alert(`项目审核人获取name时异常: ${project.id}` + JSON.stringify(error));
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
                // alert(`部门获取name时异常: ${project.id}` + JSON.stringify(error));
                console.error(`Error fetching project details for id ${project.id}:`, error);
              }
            }
            // alert(JSON.stringify(projectwbs.value));
          });

          //拼接树状结构
          const treeData = convertToTree(projectwbs.value);
          projectwbs.value = treeData;
        } else {
          // 如果响应没有数据，可以做出适当的处理
          console.error('Retrieve Projectwbs: Response data is empty');
        }
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveProjectwbss();
    };

    onMounted(async () => {
      await retrieveProjectwbss();
    });


    // 转换函数：将扁平数据转换为树状结构
    const convertToTree = (flatData: IProjectwbs[]) => {
      // 使用一个Map来存储每个节点的引用
      const map = new Map<number, IProjectwbs>(); 
      flatData.forEach(item => {
        map.set(item.id, { ...item, children: [] });
      });

      // 构建树形结构
      const tree: IProjectwbs[] = [];
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

    const handleNodeClick = (node: IProjectwbs) => {
      console.log('Node clicked:', node);
      // 处理节点点击事件的逻辑
    };
    

    //多选
    const multipleTableRef = ref<InstanceType<typeof ElTable>>();
    const toggleSelection = (rows?: projectwbs[]) => {
      if (multipleTableRef.value && multipleTableRef.value.clearSelection) {
        if (rows) {
          rows.forEach((row) => {
            multipleTableRef.value!.toggleRowSelection(row, undefined);
          });
        } else {
          multipleTableRef.value!.clearSelection();
        }
      } else {
        console.warn('ElTable component is not yet initialized or does not have clearSelection method.');
      }
    };

    return {      
      projectwbs,
      handleSyncList,
      isFetching,
      retrieveProjectwbss,
      handleNodeClick,
      multipleTableRef,
      toggleSelection, 
      clear,
      t$,
    };
  },
});
