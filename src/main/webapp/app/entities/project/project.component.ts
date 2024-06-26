import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ProjectService from './project.service';
import { type IProject } from '@/shared/model/project.model';
import { useAlertService } from '@/shared/alert/alert.service';

//人员
import OfficersService from '../officers/officers.service';
import { type IOfficers } from '@/shared/model/project.model';
//项目定密
import ProjectSecrecyService from '../project-secrecy/project-secrecy.service';
//WBS
import ProjectwbsService from '../projectwbs/projectwbs.service';
//甘特图
import { gantt } from 'dhtmlx-gantt';
import 'dhtmlx-gantt/codebase/dhtmlxgantt.css';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Project',
  setup() {
    const { t: t$ } = useI18n();
    const projectService = inject('projectService', () => new ProjectService());
    const officersService = inject('officersService', () => new OfficersService());
    const projectSecrecyService = inject('projectSecrecyService', () => new ProjectSecrecyService());
    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());

    const alertService = inject('alertService', () => useAlertService(), true);

    const projects: Ref<IProject[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const tasks = ref([
      { id: 1, name: '任务A', start: '2024-06-01', end: '2024-06-10', progress: 50 },
      { id: 2, name: '任务B', start: '2024-06-05', end: '2024-06-15', progress: 0 },
      // 更多任务...
    ]);

    const retrieveProjects = async () => {
      isFetching.value = true;
      try {
        const res = await projectService().retrieve();
        projects.value = res.data;

        // 遍历项目列表，根据每个项目的 人员id 获取并赋值 人员name，WBS也赋值name
        projects.value.forEach(async project => {
          // 项目负责人——检查 project.responsibleid.id 是否存在且不为空
          if (project.responsibleid && project.responsibleid.id) {
            try {
              const officersId = project.responsibleid.id as number; // 使用as语法
              const res = await officersService().find(officersId);
              //alert(JSON.stringify(res));
              //alert(`名称是 ：${res.officersname}`);
              if (res && res.officersname) {
                project.responsiblename = res.officersname;
              }
            } catch (error) {
              alert(`项目负责人获取name时异常: ${project.id}` + JSON.stringify(error));
              console.error(`Error fetching project details for id ${project.id}:`, error);
            }
          }
          // 假设 project.responsibleid.id 是一个逗号分隔的字符串，例如 "1,2,3"
          // if (project.responsibleid && project.responsibleid.id) {
          //   const responsibleIds = project.responsibleid.id.split(','); // 将逗号分隔的字符串拆分成数组
          //   let responsiblenames = []; // 用于存储负责人名称的数组
          //   for (const idStr of responsibleIds) {
          //     const officersId = parseInt(idStr.trim(), 10); // 将字符串转换为整数类型
          //     try {
          //       const res = await officersService().find(officersId);
          //       if (res && res.officersname) {
          //         responsiblenames.push(res.officersname); // 将负责人名称添加到数组中
          //       }
          //     } catch (error) {
          //       alert(`项目负责人获取name时异常: ${project.id}` + JSON.stringify(error));
          //       console.error(`Error fetching project details for id ${project.id}:`, error);
          //     }
          //    // 将数组中的负责人名称用逗号隔开串联成一个字符串
          //    project.responsiblename = responsiblenames.join(', ');
          //   }
          // }
          // 项目审核人——检查 project.auditorid.id 是否存在且不为空
          if (project.auditorid && project.auditorid.id) {
            try {
              const officersId = project.auditorid.id as number; // 使用as语法
              const res = await officersService().find(officersId);
              if (res && res.officersname) {
                project.auditorname = res.officersname;
              }
            } catch (error) {
              alert(`项目审核人获取name时异常: ${project.id}` + JSON.stringify(error));
              console.error(`Error fetching project details for id ${project.id}:`, error);
            }
          }
          // 项目定密——检查 project.projectwbs.id 是否存在且不为空
          if (project.projectSecrecy && project.projectSecrecy.id) {
            try {
              const secrecyId = project.projectSecrecy.id as number; // 使用as语法
              const res = await projectSecrecyService().find(secrecyId);
              //alert(JSON.stringify(res));
              //alert(`名称是 ：${res.officersname}`);
              if (res && res.description) {
                project.projectSecrecyname = res.description;
              }
            } catch (error) {
              alert(`项目定密获取name时异常: ${project.id}` + JSON.stringify(error));
              console.error(`Error fetching project details for id ${project.id}:`, error);
            }
          }
          // 项目WBS——检查 project.projectwbs.id 是否存在且不为空
          if (project.projectwbs && project.projectwbs.id) {
            try {
              const wbsId = project.projectwbs.id as number; // 使用as语法
              const res = await projectwbsService().find(wbsId);
              //alert(JSON.stringify(res));
              //alert(`名称是 ：${res.officersname}`);
              if (res && res.projectwbsname) {
                project.projectwbsname = res.projectwbsname;
              }
            } catch (error) {
              alert(`项目WBS获取name时异常: ${project.id}` + JSON.stringify(error));
              console.error(`Error fetching project details for id ${project.id}:`, error);
            }
          }
        });
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveProjects();
    };

    const officers: Ref<IOfficers[]> = ref([]);
    const isOfferFetching = ref(false);
    const retrieveOfficerss = async () => {
      isOfferFetching.value = true;
      try {
        const res = await officersService().retrieve();
        officers.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isOfferFetching.value = false;
      }
    };
    const handleOfficersList = () => {
      retrieveOfficerss();
    };

    //钩子函数,onMounted 会在组件挂载后执行指定的回调函数
    onMounted(async () => {
      await retrieveProjects();
      await retrieveOfficerss();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IProject) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeProject = async () => {
      try {
        await projectService().delete(removeId.value);
        const message = t$('jHipster2App.project.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveProjects();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    //查找用户名称
    // 定义一个响应式变量用来存储负责人名称
    const personInChargeName = ref('');

    // 查询负责人名称的方法
    const findOfficerName = async (officerId: number) => {
      try {
        const officer = officers.find(o => o.officersid === officerId);
        const data = await response.json();
        personInChargeName.value = data.name; // 假设返回的数据中有负责人的名称
      } catch (error) {
        console.error('Error fetching person in charge name:', error);
        // 可以在这里处理错误逻辑，比如给出默认值或者提示用户
      }
    };

    return {
      projects,
      handleSyncList,
      isFetching,
      retrieveProjects,
      officers,
      handleOfficersList,
      isOfferFetching,
      retrieveOfficerss,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeProject,
      tasks,
      t$,
    };
  },
});
