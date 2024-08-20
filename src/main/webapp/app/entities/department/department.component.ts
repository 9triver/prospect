import { computed, defineComponent, inject, onMounted, ref, watch, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import DepartmentService from './department.service';
import { type IDepartment,type IDepartmentWithChildren } from '@/shared/model/department.model';
import { useAlertService } from '@/shared/alert/alert.service';
import rightContent from './custom/right-content/right-content.vue'
import type { ElTree } from 'element-plus';

interface Tree {
  [key: string]: any
}

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Department',
  setup() {
    const { t: t$ } = useI18n();
    const departmentService = inject('departmentService', () => new DepartmentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const departments: Ref<IDepartment[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveDepartments = async () => {
      isFetching.value = true;
      try {
        const res = await departmentService().retrieve();
        departments.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveDepartments();
    };

    onMounted(async () => {
      await retrieveDepartments();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IDepartment) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeDepartment = async () => {
      try {
        await departmentService().delete(removeId.value);
        const message = t$('jy1App.department.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveDepartments();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    const createTree = (data:IDepartment[])=>{
      const tree: any[] = [];
      type DepartmentMap = { [key: string]: IDepartmentWithChildren };
      const nodesById:DepartmentMap = {};   
      let _data = JSON.parse(JSON.stringify(data))

      // 将所有节点添加到nodesById中，便于后续查询
      _data.forEach(node => {
        if(node.id){
          nodesById[node.id] = node;
        }
      });
    
      // 遍历所有节点
      _data.forEach(node => {
        if (!node.superior||!node.superior.id) {
          // 如果没有父节点，则认为是根节点
          tree.push(node);
        } else {
          // 查找父节点，并将当前节点添加为其子节点
          const parentNode = nodesById[node.superior.id];

          if (parentNode) {
            if (!parentNode.children) {
              parentNode.children = [];
            }
            parentNode.children.push(node);
          } else {
            console.warn(`Parent not found for node with id ${node.id}`);
          }
        }
      });
    
      return tree;
    }

    const treeData = computed(()=>{
      let tree = createTree(departments.value)
      curSelectTreeNodeData.value = tree;
      return tree
    })

    // 当前选择树节点对应的数据
    const curSelectTreeNodeData = ref<IDepartmentWithChildren[]>()
    // 选择树节点之后做的操作
    const handleTreeSelect = (data:IDepartmentWithChildren)=>{
      curSelectTreeNodeData.value = [data]
    }
    // 过滤组织机构输入框的值
    const filterInputValue = ref("")
    // 拿到树组件的引用
    const treeRef = ref<InstanceType<typeof ElTree>>()
    // 定义树组件过滤方法参数列表参数值的类型
    // 定义树组件的过滤方法
    const filterNode = (value: string, data: Tree) => {

      if (!value) return true
      return data.name.includes(value)
    }
    // 定义一个监听函数 去监听过滤组织机构输入框的值，值发生变化之后通过引用调用树组件的过滤方法
    watch(filterInputValue,(nextValue)=>{
      treeRef.value!.filter(nextValue)
    })

    // 定义折叠状态
    const collapse = ref(false)


    return {
      collapse,
      filterInputValue,
      filterNode,
      treeRef,
      curSelectTreeNodeData,
      handleTreeSelect,
      departments,
      treeData,
      handleSyncList,
      isFetching,
      retrieveDepartments,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeDepartment,
      t$
    };
  },
  components:{
    rightContent
  }
});
