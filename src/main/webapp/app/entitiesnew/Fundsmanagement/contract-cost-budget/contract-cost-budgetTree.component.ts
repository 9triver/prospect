import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import ContractCostBudgetService from './contract-cost-budget.service';
import { type IContractCostBudget } from '@/shared/model/contract-cost-budget.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ContractCostBudgetTree',
  setup() {
    const { t: t$ } = useI18n();
    const contractCostBudgetService = inject('contractCostBudgetService', () => new ContractCostBudgetService());
    const alertService = inject('alertService', () => useAlertService(), true);
    

    const contractCostBudgets: Ref<IContractCostBudget[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveContractCostBudgets = async () => {
      isFetching.value = true;
      try {
        const res = await contractCostBudgetService().retrieve();
        // 转换扁平数据为树状结构
        if(res.data){ 
          contractCostBudgets.value = res.data;
          //拼接树状结构
          const treeData = convertToTree(contractCostBudgets.value);
          contractCostBudgets.value = treeData;

        } else {
          // 如果响应没有数据，可以做出适当的处理
          console.error('Retrieve contractCostBudgets: Response data is empty');
        }
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveContractCostBudgets();
    };

    onMounted(async () => {
      await retrieveContractCostBudgets();
    });

    // 转换函数：将扁平数据转换为树状结构
    const convertToTree = (flatData: IContractCostBudget[]) => {
      // 使用一个Map来存储每个节点的引用
      const map = new Map<number, IContractCostBudget>();
      flatData.forEach(item => {
        map.set(item.id, { ...item, children: [] });
      });
      // 构建树形结构
      const tree: IContractCostBudget[] = [];
      flatData.forEach(item => {
        const node = map.get(item.id);
        if (item.parentid && map.has(item.parentid)) {
          const parent = map.get(item.parentid);
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
      contractCostBudgets,
      handleSyncList,
      isFetching,
      retrieveContractCostBudgets,
      clear,
      t$,
    };
  },
});
