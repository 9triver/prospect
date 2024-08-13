import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import FundsEstimationService from './funds-estimation.service';
import { type IFundsEstimation } from '@/shared/model/funds-estimation.model';
import { useAlertService } from '@/shared/alert/alert.service';
//人员
import OfficersService from '../officers/officers.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'FundsEstimationTree',
  setup() {
    const { t: t$ } = useI18n();
    const fundsEstimationService = inject('fundsEstimationService', () => new FundsEstimationService());
    const alertService = inject('alertService', () => useAlertService(), true);
    
    const officersService = inject('officersService', () => new OfficersService());

    const fundsEstimations: Ref<IFundsEstimation[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveFundsEstimations = async () => {
      isFetching.value = true;
      try {
        const res = await fundsEstimationService().retrieve();
        // 转换扁平数据为树状结构
        if(res.data){
          fundsEstimations.value = res.data;
          // 查询关联表补充细节
          const resData = details(fundsEstimations.value);     
          //拼接树状结构
          const treeData = convertToTree(resData.value);
          fundsEstimations.value = treeData;
        } else {
          // 如果响应没有数据，可以做出适当的处理
          console.error('Retrieve fundsEstimations: Response data is empty');
        }
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveFundsEstimations();
    };

    onMounted(async () => {
      await retrieveFundsEstimations();
    });


    const details = (flatData: IFundsEstimation[]) => {
      // 遍历项目列表，根据每个项目的 人员id 获取并赋值 人员name，WBS也赋值name
      fundsEstimations.value.forEach(async fundsEstimation => {
        // alert("----查询关联表名称----")
        // 项目审核人——检查 fundsEstimation.auditorid.id 是否存在且不为空
        if (fundsEstimation.responsibleperson && fundsEstimation.responsibleperson.id) {
          try {
            const officersId = fundsEstimation.responsibleperson.id as number; // 使用as语法
            const res = await officersService().find(officersId);
            // alert(`名称是 ：${res.name}`);
            if (res && res.name) {
              fundsEstimation.responsibleperson.name = res.name;
            }
          } catch (error) {
            alert(`项目审核人获取name时异常: ${fundsEstimation.id}` + JSON.stringify(error));
            console.error(`Error fetching fundsEstimation details for id ${fundsEstimation.id}:`, error);
          }
        }
        // 项目审核人——检查 fundsEstimation.auditorid.id 是否存在且不为空
        if (fundsEstimation.auditorid && fundsEstimation.auditorid.id) {
          try {
            const officersId = fundsEstimation.auditorid.id as number; // 使用as语法
            const res = await officersService().find(officersId);
            // alert(`名称是 ：${res.name}`);
            if (res && res.name) {
              fundsEstimation.auditorid.name = res.name;
            }
          } catch (error) {
            alert(`项目审核人获取name时异常: ${fundsEstimation.id}` + JSON.stringify(error));
            console.error(`Error fetching fundsEstimation details for id ${fundsEstimation.id}:`, error);
          }
        }
        // alert(JSON.stringify(fundsEstimations.value));
      });
      return fundsEstimations;
    }
    // 转换函数：将扁平数据转换为树状结构
    const convertToTree = (flatData: IFundsEstimation[]) => {
      // 使用一个Map来存储每个节点的引用
      const map = new Map<number, IFundsEstimation>();
      flatData.forEach(item => {
        map.set(item.id, { ...item, children: [] });
      });
      // 构建树形结构
      const tree: IFundsEstimation[] = [];
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


    return {
      fundsEstimations,
      handleSyncList,
      isFetching,
      retrieveFundsEstimations,
      clear,
      t$,
    };
  },
});
